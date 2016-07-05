import java.io.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class html_Parser {

	public static void main(String args[]) throws IOException
	{
		for(int i=1; i<=100; i++)
		{
			BufferedReader br= new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Crawled Page\\crawledURL"+String.valueOf(i)+".txt"));
			String line=null;
			int PageCount = 0;
			while((line=br.readLine()) != null)
			{
				PageCount++;
				System.out.println(line);
				Document doc=Jsoup.connect(line).get();
				String title=doc.title();
				
				System.out.println(title);
				Elements links=doc.getElementsByAttribute("src");
				
				
				for(Element e:links)
				{
					System.out.println(e.attributes().get("src"));
					String link_in=e.attributes().get("src");
					
					try
					{
						if(link_in.contains("/PostView"))  ////////네이버 블로그인 경우에 여기에서 처리
							{
								Document doc_in=Jsoup.connect("http://blog.naver.com" + link_in).get();
								String title_in=doc_in.title();
								System.out.println(title_in);
								
								Elements descript =doc_in.select("div.post-view");
				    			BufferedWriter wt= new BufferedWriter(new FileWriter("C:\\Users\\user\\Desktop\\Crawled Page\\crawled"+String.valueOf(i)+"keyword_Page"+String.valueOf(PageCount)+".txt"));
							
								for(Element el:descript)
								{
									System.out.println(el.text());
									String text = el.text();
									wt.write(text+ "\r\n");
								}
								wt.close();
							}
							
							else if(link_in.contains("/_blog/"))  ////// 다음 블로그인 경우 여기에서 처리
							{
								try
								{
									Document doc_in=Jsoup.connect("http://blog.daum.net" + link_in).get();
									String title_in=doc_in.title();
									System.out.println(title_in);
									
									Elements descript =doc_in.select("div.menuBody");
									BufferedWriter wt= new BufferedWriter(new FileWriter("C:\\Users\\user\\Desktop\\Crawled Page\\crawled"+String.valueOf(i)+"keyword_Page"+String.valueOf(PageCount)+".txt"));
								
									for(Element el:descript)
									{
										System.out.println(el.text());
										String text = el.text();
										wt.write(text+ "\r\n");
									}
									wt.close();
								}
								
								catch(Exception el)
								{
									continue;
								}
							}
					}
					
					catch(Exception els)
					{
						System.out.println("삭제되었거나, 네이버/다음 블로그가 아닙니다");
					}	
					
				}	
			}
		}	
	}
}










