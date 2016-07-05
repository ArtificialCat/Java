import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


public class PoBoc {
		
	public static int minIndex(double... ds) 				// index of minimum value in similarity value array
	{
	    int idx= -1;
	    double d= Double.POSITIVE_INFINITY;
	    for(int i = 0; i < ds.length; i++)
	        if(ds[i] < d) 
	        {
	            d = ds[i];
	            idx=i;
	        }
	        
	    return idx;
	}
	
	
	public static int maxIndex(double... value)
	{
		int idx=-1;
		
		double d= Double.NEGATIVE_INFINITY;
		for(int i=0; i<value.length; i++)
		
			if(value[i] > d)
			{
				d = value[i];
				idx = i;
			}	
		
		return idx;
	}
	
	public static double[] reverse(double[] array) 
	{
		Arrays.sort(array);
											// reverse the array
		for(int i=0; i<array.length/2; i++) 
		{
		     // swap the elements
		     double temp = array[i];
		     array[i] = array[array.length-(i+1)];
		     array[array.length-(i+1)] = temp;
		}		
		return array;
	} 										//end method reverse
	
	
	
	
	
//	public static HashMap<Integer, List<Integer>> pole(HashMap<Integer, List<Integer>> PoleSet, int[][] sim)
//	{
//				
////		System.out.println(PoleSet);
//		
//		PoleSet.put(PoleSet.size(), conn );
//		
//		
//		
//		
//		Iterator<List<Integer>> iter= PoleSet.newValueIterator();
//		double[] temp=new double[97];
//							
//			for(int p=0; p<97; p++)
//			{
//				temp[p]=0;
//			}
//			while (iter.hasNext())
//			{
//				int indx = (Integer) iter.next();
//				for(int n=0; n<97; n++)
//				{
//					temp[n] += sim[indx][n];
//				}
//			}
//			for(int o=0; o<97; o++)
//			{
//				temp[o]=temp[o]/PoleSet.size();
//			}
//			ArrayList<Integer> min_indx=minIndex(temp);
//			Iterator<Integer> iterate = min_indx.iterator();
//			while(iterate.hasNext())
//			{
//				int index=(Integer) iterate.next();
//				PoleSet.add(index);
//			}
//			System.out.println(PoleSet);
//			System.out.println(PoleSet.size());
//			
//			Iterator<Integer> Print_iter=min_indx.iterator();
//			while(Print_iter.hasNext())
//			{
//				int indexing=(Integer) Print_iter.next();
//				System.out.println(temp[indexing]);
//			}
//		return PoleSet;
//	}
	
	
//	public static double[][] Membership(HashSet<Integer> PoleSet, double[][] sim)
//	{
//		double[][] member = new double[PoleSet.size()][98];
//		Iterator<Integer> iter=PoleSet.iterator();
//		int k=0;
//		while(iter.hasNext())
//		{
//			int p_set = iter.next();
//			member[k][0] = p_set;
//			for(int w=1; w<98; w++)
//			{
//				member[k][w]= sim[p_set][w-1];
//			}
//			k++;
//				
//		}
//		
//		for(int i=0; i<PoleSet.size(); i++)
//		{		
//			for(int j=0; j<98; j++)
//			{
//				System.out.print(member[i][j]);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
//		return member;
//	}
	
	
	public static HashMap<Integer, ArrayList<Integer>> assign(HashSet<Integer> PoleSet, double[][] membership)
	{
		HashMap<Integer, ArrayList<Integer>> assign_map= new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();		
		
		for(int i=0; i<PoleSet.size(); i++)
		{
			double[] temp = new double[97];
			int[] indx_temp= new int[97]; 
			
			for(int j=1; j<98; j++)
			{
				temp[j-1]=membership[i][j];
			}
							
//			List temp_AsList = Arrays.asList(temp);
//			list.add(maxIndex(PoleSet, temp));
			HashMap<Double, Integer> indx_hsh= new HashMap<Double, Integer>();
			
			for(int n=0; n<97; n++)
			{
				indx_hsh.put(temp[n], n);
			}
			
			
			temp = reverse(temp);
			for(int z=0; z<97; z++)
			{
				System.out.print(temp[z]);
				System.out.print(" ");
			}
			System.out.println();
			for(int p=0; p<97; p++)
			{
				indx_temp[p] = indx_hsh.get(temp[p]);
//				System.out.print(indx_temp[p]);
//				System.out.print(" ");
			}
			
//			assign_map.put((int) membership[i][0], indx_arr.containsKey(key));
//			System.out.println(assign_map.get(assign_map));
			
			for(int k=0; k<95; k++)
			{
				if((temp[k]+temp[k+2])/2 <= temp[k+1])
				{
					list.removeAll(list);
					for(int l=0; l<k+1; l++)
					{
						list.add(indx_temp[l]);
					}
				}
			}
			ArrayList<Integer> uniq_list = new ArrayList<Integer>(new HashSet<Integer>(list));
			assign_map.put((int) membership[i][0], uniq_list);	
		}
		
//		System.out.println(assign_map);
		return assign_map;
	}
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * 
	 */
	public static void main(String args[]) throws FileNotFoundException
	{
		BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Crawled Page\\키워드 문서뭉치 테이블\\Similarity Matrix.txt"));
		BufferedReader br2=new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Crawled Page\\키워드 문서뭉치 테이블\\Similarity Graph.txt"));
		
		
		double[][] Sim_Matrix_temp=new double[97][97];
		
		try 
		{

				int[][] Sim_graph_temp = new int[97][97];
				String line2=null;
				
				int i2=0;
				while((line2 = br2.readLine())!= null)
				{
					StringTokenizer tokenizer2 = new StringTokenizer(line2);
					int j2=0;
					while(tokenizer2.hasMoreTokens())
					{
						String value2 = tokenizer2.nextToken();
						Sim_graph_temp[i2][j2] = Integer.parseInt(value2);
//						System.out.print(Sim_Matrix_temp[i][j]+"\t");
						j2++;
					}
					i2++;
				}	
			
			String line=null;
			
			int i=0;
			while((line = br.readLine())!= null)
			{
				StringTokenizer tokenizer=new StringTokenizer(line);
				int j=0;
				while(tokenizer.hasMoreTokens())
				{
					String value = tokenizer.nextToken();
					Sim_Matrix_temp[i][j] = Double.parseDouble(value);
//					System.out.print(Sim_Matrix_temp[i][j]+"\t");
					j++;
				}
				i++;
			}
			
			double[] similarity= new double[97];		//keyword's similarity
			
//			for(int k=0; k<97; k++)
//			{
//				for(int l=0; l<97; l++)
//				{
//					similarity[k] += Sim_Matrix_temp[k][l];	
//				}
//				similarity[k]=(similarity[k]-1)/96;
//			}
			
			for(int k=0; k<97; k++)
			{
				for(int l=0; l<97; l++)
				{
					if(Sim_graph_temp[k][l] == 1)
					{
						similarity[k] += Sim_Matrix_temp[k][l];	
					}
					similarity[k]=(similarity[k]-1)/96;
				}
			}
				
			int Min_sim = minIndex(similarity);
			
			ArrayList<Integer> pole_indx= new ArrayList<Integer>();
			pole_indx.add(Min_sim);
			HashMap<Integer, List<Integer>> PoleSet=new HashMap<Integer, List<Integer>>();
			
			PoleSet.put(1,pole_indx);
//			Iterator<Integer> main_iter=Min_sim.iterator();
//			
//			while(main_iter.hasNext())
//			{
//				int indx_main = (Integer) main_iter.next();
//				PoleSet.add(indx_main);
//			}
						 
			while(pole(PoleSet,Sim_Matrix_temp).size() < 35)
			{
				pole(PoleSet,Sim_Matrix_temp);
			}
			 
			
			double[][] Member_mat = Membership(PoleSet, Sim_Matrix_temp);
			HashMap<Integer, ArrayList<Integer>> assign_hash = assign(PoleSet, Member_mat);
			System.out.println(assign_hash);
			
		} 		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

