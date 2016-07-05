<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="oauth.signpost.OAuthConsumer"%>
<%@ page import="oauth.signpost.OAuthProvider"%>
<%@ page import="oauth.signpost.basic.DefaultOAuthConsumer"%>
<%@ page import="oauth.signpost.basic.DefaultOAuthProvider"%>
<%@ include file="/oauthHeader.jsp"%>

<%
	OAuthConsumer consumer = new DefaultOAuthConsumer(ConsumerKey,
			ConsumerSecret);
	OAuthProvider provider = new DefaultOAuthProvider(requestTokenUrl,
			accessTokenUrl, authorizeUrl);
	String oauthUrl = provider.retrieveRequestToken(consumer,
			CallbackUrl);
	String request_token = consumer.getToken();
	String request_token_secret = consumer.getTokenSecret();
	session.setAttribute("request_token", request_token);
	session.setAttribute("request_token_secret", request_token_secret);

	response.sendRedirect(oauthUrl);
%>