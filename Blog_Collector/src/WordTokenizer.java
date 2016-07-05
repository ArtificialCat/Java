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

						//ó���� Ư������ �����ϴ� �κ�
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
						Spec_letter.add("��");
						Spec_letter.add("��");
						Spec_letter.add("+");
						Spec_letter.add("-");
						Spec_letter.add("��");


						Iterator<String> iterator=Spec_letter.iterator();
						while(iterator.hasNext())
						{
							String temp_word = iterator.next();
							if(word.contains(temp_word))
							{
								word=word.replace(temp_word,""); 											//Ư�����ڸ� �������ִ� ����
								
										
							}
						}
						
			
						//���� ó���ϴ� �κ�: "��","��","��","��","��","��","��","��"
						if(word.endsWith("��")||word.endsWith("��")||word.endsWith("��")||word.endsWith("��")||word.endsWith("��")||word.endsWith("��")||word.endsWith("��"))
						{
							int word_length=word.length();
							word = word.substring(0, word_length-1);
						}
						//2�� ���� ���� ó���κ�
						else if(word.endsWith("����")||word.endsWith("�̴�")||word.endsWith("�ϴ�")||word.endsWith("����")||word.endsWith("����")||word.endsWith("����")||word.endsWith("����"))
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
