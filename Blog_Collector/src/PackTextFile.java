import java.io.*;

public class PackTextFile {
	
	public static void main(String args[])
	{	
		for(int i=1; i<=98; i++)
		{
			FileWriter fw;
			try 
			{
				fw = (new FileWriter("C:\\Users\\user\\Desktop\\Crawled Page\\Ű���庰 ������ġ\\keyword"+String.valueOf(i)+".txt"));
				
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				System.out.println("FileWriting�� ���ϰ� ����"+String.valueOf(i));
				continue;
			}
			for(int j=1; j<=10; j++)
			{
				
				try 
				{
					BufferedReader br;
					br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Crawled Page\\tokenizing�� ������ WordSet\\Word_set_keyword"+String.valueOf(i)+"blog("+String.valueOf(j)+").txt"));
					String line=null;
					while((line=br.readLine()) != null)
					{
						fw.write(line+"\r\n");
					}
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					System.out.println("Filereading�� ���ϰ� ����"+String.valueOf(i)+","+String.valueOf(j));
					continue;
				}
			} 	
		}
	}		
}
	

