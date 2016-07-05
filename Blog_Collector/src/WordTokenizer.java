import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.*;

public class WordTokenizer {
	
	public static void main(String args[])
	{
		
		for(int i=1; i<=100; i++)
		{
			for(int PageCount=1; PageCount<=10; PageCount++)
			{
				BufferedReader br;
				try 
				{
					br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Crawled Page\\crawled"+String.valueOf(i)+"keyword_Page"+String.valueOf(PageCount)+".txt"));
				} 
				
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					continue;
				}
				String line;
				
				try 
				{
					line = br.readLine();
					StringTokenizer tokenizer=new StringTokenizer(line);
					FileWriter fw=new FileWriter("C:\\Users\\user\\Desktop\\Crawled Page\\Word_set_keyword"+String.valueOf(i)+"blog("+String.valueOf(PageCount)+").txt");
					while(tokenizer.hasMoreTokens())
					{
						String word=tokenizer.nextToken();

						//처리할 특수문자 지정하는 부분
						ArrayList<String> Spec_letter = new ArrayList<String>();
						Spec_letter.add("?");
						Spec_letter.add(",");
						Spec_letter.add(".");
						Spec_letter.add("/");
						Spec_letter.add("!");
						Spec_letter.add(";");
						Spec_letter.add("<");
						Spec_letter.add(">");
						Spec_letter.add(":");
						Spec_letter.add("~");
						Spec_letter.add("*");
						Spec_letter.add("^^");
						Spec_letter.add("ㅎ");
						Spec_letter.add("ㅋ");
						Spec_letter.add("+");
						Spec_letter.add("-");
						Spec_letter.add("ㅠ");


						Iterator<String> iterator=Spec_letter.iterator();
						while(iterator.hasNext())
						{
							String temp_word = iterator.next();
							if(word.contains(temp_word))
							{
								word=word.replace(temp_word,""); 											//특수문자를 제거해주는 구문
								
										
							}
						}
						
			
						//조사 처리하는 부분: "은","는","이","가","다","와","에","서"
						if(word.endsWith("은")||word.endsWith("는")||word.endsWith("이")||word.endsWith("가")||word.endsWith("다")||word.endsWith("와")||word.endsWith("에"))
						{
							int word_length=word.length();
							word = word.substring(0, word_length-1);
						}
						//2개 음절 조사 처리부분
						else if(word.endsWith("에서")||word.endsWith("이다")||word.endsWith("니다")||word.endsWith("에요")||word.endsWith("데요")||word.endsWith("보니")||word.endsWith("으로"))
						{
							int word_length=word.length();
							
							word = word.substring(0, word_length-2);
						}						
						System.out.println(word);	
						fw.write(word+"\r\n");							
						
						
					}
					
					fw.close();
				} 
				
				
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}

			}
		}
		
	}
}
