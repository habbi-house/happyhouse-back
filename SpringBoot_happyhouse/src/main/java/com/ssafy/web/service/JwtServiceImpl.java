package com.ssafy.web.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.web.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service("jwtService")
public class JwtServiceImpl implements JwtService{
 
    private static final String SALT =  "kkekkuk";
     
    @Override
    public <T> String create(String key, T data, String subject){
    	Date now = new Date();
        String jwt = Jwts.builder()
                         .setHeaderParam("typ", "JWT")
                         .setIssuedAt(now)
                         .setSubject(subject)
                         .setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000 * 2))
//                         .setExpiration(new Date(System.currentTimeMillis() + 5000))
                         .claim(key, data)
                         .signWith(SignatureAlgorithm.HS256, this.generateKey())
                         .compact();
        return jwt;
    }   
 
    @Override
    public String createRefreshToken() {
        Date now = new Date();
        String jwt = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000 * 20))
//                .setExpiration(new Date(System.currentTimeMillis() + 5000))
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }
    
    private byte[] generateKey(){
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
         
        return key;
    }

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("Authorization").split(" ")[1];
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (ExpiredJwtException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
		return value;
	}
	
	@Override
	public Map<String, Object> getMember(String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (ExpiredJwtException e) {
			return (Map<String, Object>)e.getClaims().get("user");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get("user");
		return value;
	}
	
	@Override
	public long getMemberNo() {
        Integer memberNo = Integer.parseInt((String)this.get("user").get("userNo"));
		return new Long(memberNo);
	}

	@Override
	public String getMemberEmail() {
		return (String)this.get("user").get("email");
	}
	
	@Override
	public boolean isUsable(String jwt) {
		try{
			Jws<Claims> claims = Jwts.parser()
					  .setSigningKey(this.generateKey())
					  .parseClaimsJws(jwt);
		} catch(ExpiredJwtException e) {
			// ???????????? ??????
			System.out.println(e.getMessage());
			throw e;
		} catch(UnsupportedJwtException e) {
			// ????????? ???????????? ?????? JWT
			System.out.println(e.getMessage());
			throw e;
		} catch(MalformedJwtException e) {
			// JWT??? ???????????? ???????????? ????????? ??????
			System.out.println(e.getMessage());
			throw e;
		} catch(SignatureException e) {
			// ?????? ????????? ???????????? ?????? ??????
			System.out.println(e.getMessage());
			throw e;
		} catch(IllegalArgumentException e) {
			// claims??? ???????????? ??????
			System.out.println(e.getMessage());
			throw e;
		} catch(Exception e) {
			throw e;
		}
		
		return true;
	}

}