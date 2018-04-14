import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_18
{
	public static LinkedList<String[]> data;
	public static HashMap<String, Long> registers;
	//public static HashMap<String, Long> registers1;

	public static void main ( String args[] )
	{
		long last = 0;
		boolean found = false;
		data = new LinkedList<String[]>();
		registers = new HashMap<String, Long>();

		load();
		System.out.println(data.size());
		System.out.println(registers.size());

//Part 1
		int i = 0;
		while (found == false && i >= 0 && i < data.size()) {
        	String[] s = data.get(i);

			for (Map.Entry<String, Long> entry : registers.entrySet())
			    System.out.print(entry.getKey() + ": " + entry.getValue() + "  ");
			System.out.print("\n");

			System.out.print(i + ": " + s[0] + " " + s[1]);
			if (s.length == 3)
				System.out.print(" " + s[2]);
			System.out.print("\n");

			if (s[0].equals("jgz")) {
				long x, y;
				if (s[1].matches("[a-z]+"))
					x = registers.get(s[1]);
				else
					x = Long.parseLong(s[1]);
				if (s[2].matches("[a-z]+"))
					y = registers.get(s[2]);
				else
					y = Long.parseLong(s[2]);
				if (x > 0)
					i += y;
				else
					i++;
			}
			else {
				switch (s[0]) {
					case "snd":
						if (s[1].matches("[a-z]+"))
							last = registers.get(s[1]);
						else
							last = Long.parseLong(s[1]);
						break;
					case "set":
						if (s[2].matches("[a-z]+"))
							registers.put(s[1], registers.get(s[2]));
						else
							registers.put(s[1], Long.parseLong(s[2]));
						break;
					case "add":
						if (s[2].matches("[a-z]+"))
							registers.put(s[1], registers.get(s[1]) + registers.get(s[2]));
						else
							registers.put(s[1], registers.get(s[1]) + Long.parseLong(s[2]));
						break;
					case "mul":
						if (s[2].matches("[a-z]+"))
							registers.put(s[1], registers.get(s[1]) * registers.get(s[2]));
						else
							registers.put(s[1], registers.get(s[1]) * Long.parseLong(s[2]));
						break;
					case "mod":
						if (s[2].matches("[a-z]+"))
							registers.put(s[1], registers.get(s[1]) % registers.get(s[2]));
						else
							registers.put(s[1], registers.get(s[1]) % Long.parseLong(s[2]));
						break;
					case "rcv":
						long x;
						if (s[1].matches("[a-z]+"))
							x = registers.get(s[1]);
						else
							x = Long.parseLong(s[1]);
						if (x != 0)
							found = true;
						break;
					default:
						throw new IllegalArgumentException("Invalid instruction: " + s[0]);
				}
				i++;
			}
        }
        System.out.println(last);

//Part 2


	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input18.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split("\\s+");
				data.add(tempArray);
				if (tempArray[1].matches("[a-z]+")) {
					if (!registers.containsKey(tempArray[1]))
						registers.put(tempArray[1], (long) 0);
				}
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