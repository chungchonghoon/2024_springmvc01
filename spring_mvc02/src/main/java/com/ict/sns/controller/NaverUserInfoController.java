package com.ict.sns.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.sns.vo.KakaoUserResponse;
import com.ict.sns.vo.KakaoVO;
import com.ict.sns.vo.NaverUserResponse;
import com.ict.sns.vo.NaverVO;

@RestController
public class NaverUserInfoController {
		@RequestMapping(value="/naverUserInfo", produces = "application/json; charset=UTF-8")
		@ResponseBody
		public String naverUserInfo(HttpServletRequest request) {
			// 세션에 저장된 kavo 안에 access_token을 이용해서 사용자 정보 가져오기
			NaverVO navo = (NaverVO) request.getSession().getAttribute("navo");
			String access_token = navo.getAccess_token();
			
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			
			HttpURLConnection conn = null;
			BufferedReader br = null;
			
			try {
				URL url = new URL(apiURL);
				conn = (HttpURLConnection) url.openConnection();
				
				// POST
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				
				// 헤더 요청
				conn.setRequestProperty("Authorization", "Bearer " + access_token);
				/*
				 * for (Entry<String, String> k : headers.entrySet()) {
				 * conn.setRequestProperty(k.getKey(), k.getValue()); }
				 */
				
				
				// 응답코드 확인
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				if(responseCode == HttpURLConnection.HTTP_OK) {
						br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						
						String line = "";
						StringBuffer sb = new StringBuffer();
						while((line = br.readLine()) != null) {
								sb.append(line);
						}
						System.out.println(sb.toString());
						Gson gson = new Gson();
						NaverUserResponse naverUser = gson.fromJson(sb.toString(), NaverUserResponse.class);
						
						// 네이버는 로그아웃이 없다. 그래서 로그아웃시 세션을 초기화 하자.
						// 세션만 초기화 하면 id는 고정값이 된다.
						
//						System.out.println("id : " + naverUser.getResponse().getId());
//						System.out.println("name : " + naverUser.getResponse().getName());
						
						// naverUser 정보를 세션에 넣으면 다른 컨트롤러나 jsp에서 호출 할 수 있다.
						// id를 가지고 사용자 DB에 검색해서 id가 있으면 사용자 정보를 더 가져올 수 있다.
						// id 가지고 사용자 DB에 검색해서 id가 없으면 DB에 등록한다.
						return sb.toString();
				}
			} catch (Exception e) {
				System.out.println(e);
			}finally {
				try {
					br.close();
					conn.disconnect();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
					return null;
			}
					
}