import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import oauth.signpost.exception.OAuthCommunicationException;

/*
CAFE API
       consumer key: AhFm1GcInIKrS4iK7ONe
       consumer key secret: jEkQWg53HU
SEARCH API
       API Key: 08e5b36a46e7091cdb20c0011880f8b4
       Dummy API Key :c1b406b32dbbbbeee5f2a36ddc14067f
 */

public class Search_Result_Crawler
{      
	public static void main(String args[]) throws SAXException, OAuthCommunicationException, NullPointerException, IOException       
	{

		String apikey= "08e5b36a46e7091cdb20c0011880f8b4" ;
		String Query= "갤럭시 s3";         // search query
		String[] targets = {"rank","kin" ,"image" ,"doc" ,"book" ,"movie" ,"movieman" ,
				"local","shop" ,"car" ,"encyc" ,"blog" ,"cafe" ,"cafearticle" ,
				"webkr","news" ,"recmd" ,"adult" ,"errata" ,"shortcut"
		};

		int idx ;
		int display_idx =100;

		File fout = new File("/Users/KimHongTae/Downloads/xml_folder/150506_검색결과_test.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF8"));
		
		for(int tmp=0; tmp <1000; tmp++)
		{
			try
			{
				for(idx =0; idx < 1000; idx++)      
				{
					int start_idx = idx +1;
					String url = "http://openapi.naver.com/search?key=" + apikey +"&query=" +
							URLEncoder.encode(Query,"UTF-8")+ "&target="+ targets [13] +"&start=" +
							start_idx+"&display=" +display_idx +"&sort=date" ;

					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(url);


					NodeList rss = doc.getElementsByTagName("rss" );
					NodeList channel = rss.item(0).getChildNodes();
					NodeList _n = channel.item(0).getChildNodes();
					int countList = (int)_n.getLength();
					//                                       String lists[] = new String[countList];

					for (int i =0; i <countList ; i ++)
					{
						if ("item".equals(_n .item(i ).getNodeName()))
						{
							NodeList itemList = _n.item(i ).getChildNodes();
							for (int j = 0; j < itemList.getLength(); j ++)
							{
								if ("title".equals(itemList .item(j ).getNodeName())) {
									try
									{
										System. out.println("Title:" + itemList.item(j ).getFirstChild().getNodeValue());
										bw.write( "Title:" + itemList .item(j ).getFirstChild().getNodeValue());

									}
									catch(NullPointerException e1 )
									{
										continue;     
									}

								}
								if ("link".equals(itemList .item(j ).getNodeName())) {
									try
									{
										System. out.println("Link:" + itemList.item(j ).getFirstChild().getNodeValue());
										bw.write( "\t\t"+"Link:" + itemList.item(j ).getFirstChild().getNodeValue());;
									}
									catch(NullPointerException e2 )
									{
										continue;     
									}
								}
								if ("description".equals(itemList .item(j ).getNodeName())) {

									try
									{
										System. out.println("Description:" + itemList.item(j ).getFirstChild().getNodeValue());      
										bw.write( "\t\t"+"Description:" + itemList.item(j ).getFirstChild().getNodeValue());
									}
									catch(NullPointerException e3 )
									{
										continue;
									}

								}
								if ("cafename".equals(itemList .item(j ).getNodeName())) {
									try
									{
										System. out.println("cafename:" + itemList.item(j ).getFirstChild().getNodeValue());
										bw.write( "\t\t"+"cafename:" + itemList.item(j ).getFirstChild().getNodeValue());
									}
									catch(NullPointerException e4 )
									{
										continue;
									}
								}
								if ("cafeurl".equals(itemList .item(j ).getNodeName())) {
									try
									{
										System. out.println("cafeurl:" + itemList.item(j ).getFirstChild().getNodeValue());
										bw.write( "\t\t"+"cafeurl:" + itemList.item(j ).getFirstChild().getNodeValue());
										bw.newLine();
									}
									catch(NullPointerException e5 )
									{
										continue;
									}
								}
							}
						}

					}
				}

			}

			catch(IOException e )
			{
				System. out.println(e );
				continue;
			}
			catch (ParserConfigurationException ex )
			{
				// TODO Auto-generated catch block
				continue;
			}
			catch(NullPointerException exx )
			{
				System. out.println("NullPointerException" );
				continue;
			}
		}
		
		bw.close();
	}      
}