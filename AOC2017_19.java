import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_19
{
	public static LinkedList<char[]> data;

	public static void main ( String args[] )
	{
		int x, y, sum;
		x = y = 0;
		sum = 1;
		String path = "";
		boolean end = false;
		char dir = 'D'; //Direction
		data = new LinkedList<char[]>();

		load();

		//Find start position
		int k = 0;
		char[] ca = data.get(0);
		while (k < ca.length) {
			if (ca[k] == '|') {
				x = k;
				k = data.get(0).length;
			}
			else
				k++;
		}

//Part 1 - 2

		while (end == false) {
			char c = data.get(y)[x];
			if (c == '+') { //Direction change
				if (data.get(y + 1)[x] != ' ' && dir != 'U') {
					y++;
					dir = 'D';
				}
				else if (data.get(y - 1)[x] != ' ' && dir != 'D') {
					y--;
					dir = 'U';
				}
				else if (data.get(y)[x + 1] != ' ' && dir != 'L') {
					x++;
					dir = 'R';
				}
				else if (data.get(y)[x - 1] != ' ' && dir != 'R') {
					x--;
					dir = 'L';
				}
				sum++;
			}
			else if (c == ' ') { //End
				end = true;
				sum--;
			}
			else {
				if (Character.isLetter(c))
					path += c;
				switch (dir) {
					case 'D':
						y++;
					break;
					case 'U':
						y--;
					break;
					case 'R':
						x++;
					break;
					case 'L':
						x--;
					break;
				}
				sum++;
			}
			
		}

		System.out.println("Part 1: " + path);
		System.out.println("Part 2: " + sum);
	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input19.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				data.add(line.toCharArray());
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