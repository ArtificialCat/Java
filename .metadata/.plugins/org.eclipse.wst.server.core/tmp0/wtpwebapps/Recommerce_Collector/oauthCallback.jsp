<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="oauth.signpost.OAuthConsumer"%>
<%@ page import="oauth.signpost.OAuthProvider"%>
<%@ page import="oauth.signpost.basic.DefaultOAuthConsumer"%>
<%@ page import="oauth.signpost.basic.DefaultOAuthProvider"%>
<%@ include file="/oauthHeader.jsp"%>

<%
	String oauth_token = request.getParameter("oauth_token");
	String oauth_verifier = request.getParameter("oauth_verifier");

	OAuthConsumer consumer = new DefaultOAuthConsumer(ConsumerKey,
			ConsumerSecret);
	String request_token = (String) session
			.getAttribute("request_token");
	String request_token_secret = (String) session
			.getAttribute("request_token_secret");
	consumer.setTokenWithSecret(request_token, request_token_secret);
	OAuthProvider provider = new DefaultOAuthProvider(requestTokenUrl,
			accessTokenUrl, authorizeUrl);

	provider.setOAuth10a(true);
	provider.retrieveAccessToken(consumer, oauth_verifier);
	String access_token = consumer.getToken();
	String access_token_secret = consumer.getTokenSecret();
	session.setAttribute("access_token", access_token);
	session.setAttribute("access_token_secret", access_token_secret);
%>

<html>
<head>
<title>OAuth callback</title>
</head>
<body>
	<h2>API 호출 URL 및 파리미터 입력</h2>
	<form method="GET" action="oauthApiCall.jsp">
		api_url : <input type="text" name="apiUrl"
			value="http://openapi.naver.com/cafe/getArticleList.xml" size="60" />
		<br /> club_id : <input type="text" name="clubid" value=10050146 />
		<br /> menu_id : <input type="text" name="search.menuid" value=339 />
		<br /> page : <input type="text" name="search.page" value=1 /> <br />
		perPage : <input type="text" name="search.perPage" value=15 /> <br />
		<input type="submit" value="API 호출" />
	</form>
</body>
</html>