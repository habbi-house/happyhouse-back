package com.ssafy.web.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.web.util.ApiKey;

import java.util.*;

@Service
public class KakaoLogin {
	
	@Autowired
	ApiKey apiKey;
	
	public Map<String, String> getKaKaoAccessToken(String code){
		Map<String, String> tokens = new HashMap<>();
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + apiKey.getKey());
            sb.append("&redirect_uri=http://localhost:8080/kakao");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            tokens.put("accessToken", access_Token);
            tokens.put("refreshToken", refresh_Token);
            
            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return tokens;
    }
	
	public Map<String, String> createKakaoUser(String accessToken) {
		Map<String, String> userInfo = new HashMap<String, String>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            String nickname = "";
            try {
            	nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
            }catch(Exception e) {            	
            	nickname = "?????????";
            }
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            } else {
            	email = "anonymous@happyhouse.com";
            }

            br.close();
            
            userInfo.put("name", nickname);
            userInfo.put("email", email);
            
            return userInfo;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}