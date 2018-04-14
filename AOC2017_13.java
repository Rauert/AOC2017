import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_13
{
	public static LinkedList<Integer> data; //Length
	public static LinkedList<Integer> dataPos; //Current position of scanner.
	public static LinkedList<Character> dataDir; //Direction of scanner - up or down.
	public static int severity;

	public static void main ( String args[] )
	{
		data = new LinkedList<Integer>();
		load();

//Part 1
		traverse(0);
		System.out.println("Part 1: Severity = " + severity);

//Part 2

		for (int i = 0; i < data.size(); i++) {
			
		}

		int i = 50000;
		while (severity > 0) {
			reset();
			traverse(i);
			System.out.println(i + " = " + severity);
			i++;
			i++;
		}
		System.out.println("Part 2: Delay = " + (i - 1));
		
	}

	private static void traverse(int offset) {
		int pos = 0 - offset;
		//int sum = 0;
		int currRange;
		int currPos;
		char currDir;

		while (pos < data.size()) {
			if (pos >= 0) {
				if (dataPos.get(pos) == 0) {
					severity = severity + (pos * data.get(pos));
					//sum++;
				}
			}
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) > 1) {
					currRange = data.get(i);
					currPos = dataPos.get(i);
					currDir = dataDir.get(i);
					if (currPos == 0) {
						if (currDir == 'U') {
							dataPos.set(i, currPos + 1);
							dataDir.set(i, 'D');
						}
						else
							dataPos.set(i, currPos + 1);
					}
					else if (currPos == currRange-1) {
						if (currDir == 'D') {
							dataPos.set(i, currPos - 1);
							dataDir.set(i, 'U');
						}
						else
							dataPos.set(i, currPos - 1);
					}
					else if (currDir == 'D')
						dataPos.set(i, currPos + 1);
					else
						dataPos.set(i, currPos - 1);
					//System.out.println(i + ": " + dataPos.get(i) + " / " + (currRange - 1));
				}
			}
			
			pos++;
			
		}
		//System.out.println(sum);
	}

	private static void reset()
	{
		severity = 0;
		dataPos = new LinkedList<Integer>();
		dataDir = new LinkedList<Character>();

		for (int i = 0; i < data.size(); i++) {
			dataPos.add(0);
			dataDir.add('D');
		}
	}

	private static void load()
	{
		int currDepth = 0;
		int inDepth = 0;
		try {
			FileReader fr = new FileReader("input13.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split(":\\s+");
				inDepth = Integer.parseInt(tempArray[0]);
				for (; currDepth < inDepth; currDepth++)
					data.add(0);
				data.add(Integer.parseInt(tempArray[1]));
				currDepth = inDepth;
				currDepth++;
			}
			reset();
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