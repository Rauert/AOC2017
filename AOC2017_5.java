import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_5
{
	public static LinkedList<Integer> data;

	public static void main ( String args[] )
	{
		int sum = 0;
		int i = 0;
		int j = 0;
		//boolean found = false;
		data = new LinkedList<Integer>();

		load();

//Part 1

		while (i < data.size() && i >= 0) {
        	j = i + data.get(i);
        	data.set(i, data.get(i) + 1);
        	i = j;
        	sum++;
        	//System.out.println(i + " " + data.get(i));
        	
        }

		System.out.println(sum);

//Part 2

		sum = i = j = 0;
		data = new LinkedList<Integer>();

		load();

		while (i < data.size() && i >= 0) {
        	j = i + data.get(i);
        	if (data.get(i) > 2)
	        	data.set(i, data.get(i) - 1);
	        else
	        	data.set(i, data.get(i) + 1);
        	i = j;
        	sum++;
        	//System.out.println(i + " " + data.get(i));
        	
        }

		System.out.println(sum);


	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input5.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				data.add(Integer.parseInt(line));
			}
			fr.close();
		}
		catch(FileNotFoundException fN) {
			fN.printStackTrace();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}