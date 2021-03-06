import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_23
{
	public static LinkedList<String[]> data;
	public static HashMap<String, Long> registers;

	public static void main ( String args[] )
	{
		long sum = 0;
		//boolean found = false;
		data = new LinkedList<String[]>();
		registers = new HashMap<String, Long>();
		registers.put("a", 0L);
		registers.put("b", 0L);
		registers.put("c", 0L);
		registers.put("d", 0L);
		registers.put("e", 0L);
		registers.put("f", 0L);
		registers.put("g", 0L);
		registers.put("h", 0L);

		load();
		//System.out.println(data.size());
		//System.out.println(registers.size());

//Part 1
		int i = 0;
		while (i >= 0 && i < data.size()) {
        	String[] s = data.get(i);

			for (Map.Entry<String, Long> entry : registers.entrySet())
			    System.out.print(entry.getKey() + ": " + entry.getValue() + "  ");
			System.out.print("\n");

			System.out.print(i + ": " + s[0] + " " + s[1] + " " + s[2]);
			System.out.print("\n");

			if (s[0].equals("jnz")) {
				long x, y;
				if (s[1].matches("[a-z]+"))
					x = registers.get(s[1]);
				else
					x = Long.parseLong(s[1]);
				if (s[2].matches("[a-z]+"))
					y = registers.get(s[2]);
				else
					y = Long.parseLong(s[2]);
				if (x != 0)
					i += y;
				else
					i++;
			}
			else {
				switch (s[0]) {
					case "set":
						if (s[2].matches("[a-z]+"))
							registers.put(s[1], registers.get(s[2]));
						else
							registers.put(s[1], Long.parseLong(s[2]));
						break;
					case "sub":
						if (s[2].matches("[a-z]+"))
							registers.put(s[1], registers.get(s[1]) - registers.get(s[2]));
						else
							registers.put(s[1], registers.get(s[1]) - Long.parseLong(s[2]));
						break;
					case "mul":
						if (s[2].matches("[a-z]+"))
							registers.put(s[1], registers.get(s[1]) * registers.get(s[2]));
						else
							registers.put(s[1], registers.get(s[1]) * Long.parseLong(s[2]));
						sum++;
						break;
					default:
						throw new IllegalArgumentException("Invalid instruction: " + s[0]);
				}
				i++;
			}
        }
        System.out.println("Part 1: " + sum);

//Part 2


	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input23.txt");
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