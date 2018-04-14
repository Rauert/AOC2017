import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_18d2
{
	public static LinkedList<String[]> data;
	public static HashMap<String, Long> registers0;
	public static HashMap<String, Long> registers1;
	public static int i0; //Program 0 inst
	public static int i1; //Program 1 inst
	public static int sends;
	public static boolean stalled0;
	public static boolean stalled1;
	public static LinkedList<Long> queue0;
	public static LinkedList<Long> queue1;

	public static void main ( String args[] )
	{
		data = new LinkedList<String[]>();
		registers0 = new HashMap<String, Long>();
		registers1 = new HashMap<String, Long>();
		queue0 = new LinkedList<Long>();
		queue1 = new LinkedList<Long>();
		stalled0 = stalled1 = false;

		load();
		registers1.put("p", (long) 1);

//Part 2
		//int old0, old1;
		i0 = i1 = sends = 0;
		boolean deadlock = false;

		while (deadlock == false && i0 >= 0 && i0 < data.size() && i1 >= 0 && i1 < data.size()) {
			stalled0 = stalled1 = false;
			i0 = runInstr(registers0, queue1, queue0, i0, 0);
			i1 = runInstr(registers1, queue0, queue1, i1, 1);

			if (stalled0 == true && stalled1 == true)
				deadlock = true;
        }
        System.out.println(sends);

	}

	private static int runInstr(HashMap<String, Long> registers, LinkedList<Long> otherQueue, LinkedList<Long> myQueue ,int i, int id)
	{
		String[] s = data.get(i);

//		System.out.println(queue.size());
		for (Map.Entry<String, Long> entry : registers.entrySet())
		    System.out.print(entry.getKey() + ": " + entry.getValue() + "  ");
		System.out.print("\n");

		System.out.print(id + ": " + i + ": " + s[0] + " " + s[1]);
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
						otherQueue.add(registers.get(s[1]));
					else
						otherQueue.add(Long.parseLong(s[1]));
					if (id == 1)
						sends++;
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
					if (myQueue.size() != 0) {
						for (Long l : myQueue) {
            				System.out.print(l + " ");
        				}
        				System.out.print("\n");
						registers.put(s[1], myQueue.poll());
					}
					else {
						i--;
						if (id == 0)
							stalled0 = true;
						else
							stalled1 = true;
					}
					break;
				default:
					throw new IllegalArgumentException("Invalid instruction: " + s[0]);
			}
			i++;
		}
		return i;
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
					if (!registers0.containsKey(tempArray[1]))
						registers0.put(tempArray[1], (long) 0);
						registers1.put(tempArray[1], (long) 0);
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