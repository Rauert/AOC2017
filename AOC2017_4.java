import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_4
{
	public static LinkedList<String[]> data;

	public static void main ( String args[] )
	{
		int sum = 0;
		int j;
		boolean found = false;
		data = new LinkedList<String[]>();

		load();

//Part 1

		for (String[] s : data) {
        	for (int i = 0; i < s.length; i++) {
        		j = 0;
        		while (j < s.length && found == false) {
        			if (i != j){
        				if (s[i].equals(s[j]))
        					found = true;
        			}
        			j++;
        		}
        	}
        			if (found == false)
        				sum++;
        			else
        				found = false;
        }
        System.out.println(sum);

//Part 2

		sum = 0;

		for (String[] s : data) {
        	for (int i = 0; i < s.length; i++) {
        		j = 0;
        		while (j < s.length && found == false) {
        			if (i != j){
        				if (anagram(s[i], s[j]) == true)
        					found = true;
        			}
        			j++;
        		}
        	}
        			if (found == false)
        				sum++;
        			else
        				found = false;
        }
        System.out.println(sum);

	}

	private static boolean anagram(String a, String b)
	{
		boolean isAnagram = false;

		if (a.length() == a.length())
		{
			char[] word1 = a.toCharArray();
     		char[] word2 = b.toCharArray();
     		Arrays.sort(word1);
     		Arrays.sort(word2);
    		if (Arrays.equals(word1, word2)) isAnagram = true;
		}

		return isAnagram;
	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input4.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split("\\s+");
				data.add(tempArray);
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