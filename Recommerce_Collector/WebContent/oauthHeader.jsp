<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String ConsumerKey = "xVwUtBuC5l2NnMVHc58O";
	String ConsumerSecret = "LIjJJmKJIc";
	String CallbackUrl = "http://localhost:8080/Recommerce_Collector/oauthCallback.jsp";

	String requestTokenUrl = "https://nid.naver.com/naver.oauth?mode=req_req_token";
	String accessTokenUrl = "https://nid.naver.com/naver.oauth?mode=req_acc_token";
	String authorizeUrl = "https://nid.naver.com/naver.oauth?mode=auth_req_token";
%>