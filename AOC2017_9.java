import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_9
{
	public static void main ( String args[] )
	{
		int groups = 0;
		int score = 0;
		int i = 0;
		int sum = 0;
		int garbSum = 0;
		String data = load();
		//data = "{{<a!>},{<a!>},{<a!>},{<ab>}}";

//Part 1

		while (i < data.length()) {
			switch (data.charAt(i)){
				case '{':
					score++;
					groups++;
				break;
				case '}':
					sum = sum + score;
					score--;
				break;
				case '<':
					i++;
					while (data.charAt(i) != '>'){
						switch (data.charAt(i)){
							case '!':
								i++;
								i++;
							break;
							default:
								i++;
								garbSum++;
						}
					}
				break;
				default:
					//Do nothing.
			}
			i++;
        }

		System.out.println("Num Groups: " + groups);
		System.out.println("Final Score: " + score);
		System.out.println("Total Score: " + sum);
		System.out.println("Garbage Count: " + garbSum);

//Part 2



	}

	private static String load()
	{
		String line = "";
		try {
			FileReader fr = new FileReader("input9.txt");
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			fr.close();
		}
		catch(FileNotFoundException fN) {
			fN.printStackTrace();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return line;
	}
}