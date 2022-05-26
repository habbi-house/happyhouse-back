package com.ssafy.web.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.service.JwtService;
import com.ssafy.web.service.KakaoLogin;
import com.ssafy.web.service.UserService;
import com.ssafy.web.vo.TokenVO;
import com.ssafy.web.vo.UserVO;
import com.ssafy.web.vo.WishVO;

import io.jsonwebtoken.JwtException;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:8080")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	KakaoLogin kakaoLogin;

	@Autowired
	JwtService jwtService;

	@GetMapping("/kakao")
	public ResponseEntity<String> kakaoCallback(@RequestParam String code, HttpServletResponse response) {
		// 카카오 서비스를 이용하려면 tokens를 저장할 필요가 있다.
		Map<String, String> tokens = kakaoLogin.getKaKaoAccessToken(code);
		Map<String, String> userInfo = kakaoLogin.createKakaoUser(tokens.get("accessToken"));
		
		String refreshToken = jwtService.createRefreshToken();
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setMaxAge(86400 * 1000);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		TokenVO tokenVO = new TokenVO();
		tokenVO.setEmail(userInfo.get("email"));
		tokenVO.setRefreshToken(refreshToken);
		int tokenIdx = userService.addToken(tokenVO);
		userInfo.put("tokenIdx", Integer.toString(tokenIdx));

		String accessToken = jwtService.create("user", userInfo, "user");
		Cookie accessCookie = new Cookie("accessToken", accessToken);
		accessCookie.setMaxAge((int)System.currentTimeMillis() * 1800 * 1000);
		accessCookie.setSecure(true);
		accessCookie.setHttpOnly(true);
		accessCookie.setPath("/");
		response.addCookie(accessCookie);
		
		String token = jwtService.create("user", userInfo, "user");

		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody UserVO user) {
		int cnt = userService.checkEmail(user);
		
		// TODO: 패턴 검증
		
		// 비밀번호 암호화
		String password = user.getPassword();
		String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		user.setPassword(encryptedPassword);

		if (cnt == 0) {
			userService.createUser(user);
			return new ResponseEntity<String>("회원 가입 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("이미 가입된 회원입니다.", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> map, HttpServletResponse response) {		
		UserVO user = userService.getUser(map);

		if(user != null && BCrypt.checkpw(map.get("password"), user.getPassword())) {
			String refreshToken = jwtService.createRefreshToken();
			Cookie cookie = new Cookie("refreshToken", refreshToken);
			cookie.setMaxAge((int)System.currentTimeMillis() * 86400 * 1000);
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			TokenVO tokenVO = new TokenVO();
			tokenVO.setEmail(user.getEmail());
			tokenVO.setRefreshToken(refreshToken);
			int tokenIdx = userService.addToken(tokenVO);
			
			Map<String, String> userInfo = new HashMap<>();
			userInfo.put("tokenIdx", Integer.toString(tokenIdx));
			userInfo.put("userNo", user.getNo());
			userInfo.put("email", user.getEmail());
			userInfo.put("tokenIdx", Integer.toString(tokenIdx));

			String accessToken = jwtService.create("user", userInfo, "user");
			Cookie accessCookie = new Cookie("accessToken", accessToken);
			accessCookie.setMaxAge((int)System.currentTimeMillis() * 1800 * 1000);
			accessCookie.setSecure(true);
			accessCookie.setHttpOnly(true);
			accessCookie.setPath("/");
			response.addCookie(accessCookie);

			return new ResponseEntity<String>(accessToken, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("아이디와 비밀번호를 확인해주세요.", HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/refresh")
	public ResponseEntity<String> refreshUser(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String accessToken = null;
		String refreshToken = null;
		if(cookies == null) {
			return new ResponseEntity<String>("로그인 해주세요", HttpStatus.ACCEPTED);
		}
		for (Cookie c : cookies) {
			if ("accessToken".equals(c.getName())) {
				accessToken = c.getValue();
			} else if ("refreshToken".equals(c.getName())) {
				refreshToken = c.getValue();
			}
		}
		try {
			if (refreshToken != null && jwtService.isUsable(refreshToken)) {
				accessToken = jwtService.create("user", jwtService.getMember(accessToken), "user");
				// DB에 토큰을 다시 갱신
				Cookie accessCookie = new Cookie("accessToken", accessToken);
				accessCookie.setMaxAge((int)System.currentTimeMillis() * 1800 * 1000);
				accessCookie.setSecure(true);
				accessCookie.setHttpOnly(true);
				accessCookie.setPath("/");
				response.addCookie(accessCookie);
	
				return new ResponseEntity<String>(accessToken, HttpStatus.OK);
			}
		} catch(JwtException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<String>("다시 로그인 해주세요", HttpStatus.I_AM_A_TEAPOT);
	}

	@PostMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestBody int no) {
		if (no == jwtService.getMemberNo()) {
			userService.deleteUser(no);
			return new ResponseEntity<String>("정상적으로 탈퇴되었습니다.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("정상적이지 않은 요청입니다.", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<UserVO> updateUser(@RequestBody UserVO user) {
		if (Long.parseLong(user.getNo()) == jwtService.getMemberNo()) {
			if (user.getPassword().equals("")) {
				String password = userService.getUserByNo(Integer.parseInt(user.getNo())).getPassword();
				user.setPassword(password);
			}
			userService.updateUser(user);
			user.setPassword("");
			// 유저의 정보를 참조하고 있는 테이블도 업데이트해줘야 한다.
			return new ResponseEntity<UserVO>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserVO>(new UserVO(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/{no}")
	public ResponseEntity<UserVO> getUserByNo(@PathVariable("no") int no) {
		UserVO user = new UserVO();
		if (no == jwtService.getMemberNo()) {
			user = userService.getUserByNo(no);
			user.setPassword("");
			return new ResponseEntity<UserVO>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserVO>(user, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		
		String accessToken = null;
		String bearer = request.getHeader("Authorization");
		if(bearer != null && !"".equals(bearer)) {
			accessToken = bearer.split(" ")[1];
		}
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if ("accessToken".equals(c.getName())) {
				accessToken = c.getValue();
			}
		}
		
		if(accessToken != null && !"".equals(accessToken)) {
			userService.deleteToken(Integer.parseInt((String)jwtService.getMember(accessToken).get("tokenIdx")));
		}
		
		Cookie accessCookie = new Cookie("accessToken", null);
		accessCookie.setMaxAge(0);
		accessCookie.setPath("/");
		response.addCookie(accessCookie);
		
		Cookie refreshCookie = new Cookie("refreshToken", null);
		refreshCookie.setMaxAge(0);
		refreshCookie.setPath("/");
		response.addCookie(refreshCookie);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/ping")
	public ResponseEntity<Void> ping(){
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/addwish")
	public ResponseEntity<Void> addwish(@RequestBody WishVO wishVO){
		userService.addWish(wishVO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/deletewish")
	public ResponseEntity<Void> deletewish(@RequestBody WishVO wishVO){
		userService.deleteWish(wishVO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/wishlist")
	public ResponseEntity<List<BigInteger>> deletewish(@RequestBody String email){
		email = email.substring(1, email.length() - 1);
		List<BigInteger> wishlist = userService.getWishlist(email);
		
		return new ResponseEntity<List<BigInteger>>(wishlist, HttpStatus.OK);
	}


}
