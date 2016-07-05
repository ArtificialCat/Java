import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.*;

import kr.co.shineware.nlp.komoran.core.*;
//import kr.shineware.nlp.komoran.*;
//import kr.co.shineware.util.*;
import kr.co.shineware.util.common.model.Pair;

public class MorphologyAnalysis {
	
	public static void main(String args[]) throws IOException
	{
		MorphologyAnalyzer analyzer = new MorphologyAnalyzer("C:\\Users\\user\\Desktop\\Crawled Page\\datas\\datas\\");
		analyzer.setUserDic("C:\\Users\\user\\Desktop\\Crawled Page\\datas\\datas\\userDic.txt");
		
		BufferedReader br;
		try 
		{
			br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Crawled Page\\키워드 문서뭉치 테이블\\20131111_키워드문서뭉치테이블_unpivot_텍스트.txt"));	
			String line=null;
			FileWriter fw=new FileWriter("C:\\Users\\user\\Desktop\\Crawled Page\\키워드 문서뭉치 테이블\\POS태깅.txt");

			while((line = br.readLine()) != null)
			{
				List<List<Pair<String,String>>> result = analyzer.analyze(line);
	            
				for (List<Pair<String, String>> eojeolResult : result) 
				{
				     for (Pair<String, String> wordMorph : eojeolResult) 
				     {
				          System.out.println(wordMorph.toString());
				          fw.write(wordMorph.toString());	
				     }
				     System.out.println();
				     fw.write("\n");
				}
			}
			fw.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
