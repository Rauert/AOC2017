import java.lang.*;
import java.util.*;
import java.io.*;
import java.math.*;

public class AOC2017_11
{
	public static String[] data;

	public static void main ( String args[] )
	{
		int x, y, max;
		x = y = max = 0;
		load();

		for (String s : data) {
			switch (s) {
				case "s":
					y--;
				break;
				case "n":
					y++;
				break;
				case "nw":
					x--;
					if (Math.abs(x)%2 == 0)
						y++;
				break;
				case "ne":
					x++;
					if (Math.abs(x)%2 == 0)
						y++;
				break;
				case "sw":
					x--;
					if (Math.abs(x)%2 == 1)
						y--;
				break;
				case "se":
					x++;
					if (Math.abs(x)%2 == 1)
						y--;
				break;

				default:
					throw new IllegalArgumentException("Invalid comparator: " + data);
			}
			
			if (Math.abs(x) > max)
				max = Math.abs(x);
			if (Math.abs(y) > max)
				max = Math.abs(y);
		}

		if (Math.abs(0 - x) > Math.abs(0 - y))
			System.out.println("Part 1: " + Math.abs(0 - x));
		else
			System.out.println("Part 1: " + Math.abs(0 - y));

		System.out.println("Part 2: " + max);

	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input11.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			data = line.split(",|\\s+");
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