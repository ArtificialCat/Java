
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="oauth.signpost.OAuthConsumer"%>
<%@ page import="oauth.signpost.OAuthProvider"%>
<%@ page import="oauth.signpost.http.HttpParameters"%>
<%@ page import="oauth.signpost.basic.DefaultOAuthConsumer"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.io.*,java.net.*"%>
<%@ include file="/oauthHeader.jsp"%>

<%
	System.setProperty("debug", "true");
	String apiUrl = request.getParameter("apiUrl");

	String strHtml = "";
	String strLine = "";
	int SearchPage = Integer.parseInt(request
			.getParameter("search.page"))+1202;

for(int tmp = 1203; tmp <= 10000; tmp++ )
{
	File fout = new File(
			"/Users/KimHongTae/Downloads/xml_folder2/150502_카페_메타정보_"+tmp+".xml");
	FileOutputStream fos = new FileOutputStream(fout);
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,
			"UTF8"));


		try {
			String parameters = "search.clubid="
					+ request.getParameter("clubid")
					+ "&search.menuid="
					+ request.getParameter("search.menuid")
					+ "&search.page=" + Integer.toString(SearchPage)
					+ "&search.perPage="
					+ request.getParameter("search.perPage");

			String access_token = (String) session
					.getAttribute("access_token");
			String access_token_secret = (String) session
					.getAttribute("access_token_secret");

			OAuthConsumer consumer = new DefaultOAuthConsumer(
					ConsumerKey, ConsumerSecret);
			consumer.setTokenWithSecret(access_token,
					access_token_secret);
			HttpParameters additionalParameters = new HttpParameters();
			additionalParameters.put("realm", apiUrl);
			consumer.setAdditionalParameters(additionalParameters);

			HttpURLConnection httpRequest = null;
			String resultValue = null;

			URL url = new URL(apiUrl + "?" + parameters);
			httpRequest = (HttpURLConnection) url.openConnection();
			httpRequest.setRequestProperty("Content-type",
					"text/xml; charset=UTF-8");
			consumer.sign(httpRequest);
			httpRequest.connect();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpRequest.getInputStream(),
							"UTF-8"));
			while ((strLine = br.readLine()) != null) {
				strHtml += strLine;
				bw.write(strLine);
			}

			br.close();

			SearchPage++;

			if (httpRequest != null) {
				httpRequest.disconnect();
			}
		} catch (Exception e3) {
			continue;
		}

	

	String resultValue = strHtml.toString();
	bw.close();
}
%>

