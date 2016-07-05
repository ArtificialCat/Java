import java.net.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Test_crawl {
    public static void main(String[] args) throws Exception{
    	
    	BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Crawled Page\\keyword100.txt"));
    	String Line=null;
    	int keyword_rank=0;
    	
    	while((Line=br.readLine()) != null)
    	{
    		keyword_rank++;
    		String content = Line;
        	content = URLEncoder.encode(content,"utf-8");
        	int content_cnt = 10;
        	String url_name= "http://openapi.naver.com/search?key=c8fc5d87b292571cb3d248bfc0bd45ca&query="+content+"&display="+String.valueOf(content_cnt)+"&start=1&target=blog&sort=sim";
        	URL url = new URL(url_name);
            
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
           
            	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
                
                String inputLine;
                String[] input;
                int cnt;
                
                
                while ((inputLine = in.readLine()) != null)
                {
                	
                	inputLine = inputLine.replaceAll("<link>", "\t");
                	inputLine = inputLine.replaceAll("</link>", "\t");
                	
                	input = inputLine.split("\t");
                	cnt = input.length;
        			BufferedWriter wt= new BufferedWriter(new FileWriter("C:\\Users\\user\\Desktop\\Crawled Page\\crawledURL"+String.valueOf(keyword_rank)+".txt"));
                	
        			String inputLine2=null;
                	for(int i=2; i<cnt; i++)
                	{
                		if(input[i].contains("http://") && !input[i].contains("<")) 
                		{            			
                			inputLine2=input[i];
                			wt.write(inputLine2+"\r\n");
                			System.out.println(input[i]);
                			
                		}
                	}
                	wt.close();
                }
    	}
    }
}

    
           
        
    
