import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_8
{
	public static LinkedList<String[]> data;
	public static Map<String, Integer> registers;

	public static void main ( String args[] )
	{
		data = new LinkedList<String[]>();
		registers = new HashMap<String, Integer>();
		int largestEver = 0;
		load();

	for (String[] s : data) {
		if (calcIf(s[4], s[5], Integer.parseInt(s[6]))) {
			if (s[1].equals("inc"))
				registers.put(s[0], registers.get(s[0]) + Integer.parseInt(s[2]));
			else
				registers.put(s[0], registers.get(s[0]) - Integer.parseInt(s[2]));

			if (registers.get(s[0]) > largestEver)
				largestEver = registers.get(s[0]);
		}
	}

	Integer largest = null;
	for (Map.Entry<String, Integer> entry : registers.entrySet()) {
	    System.out.println(entry.getKey() + " " + entry.getValue());
	    if (largest == null)
	    	largest = entry.getValue();
	    else {
	    	if (entry.getValue() > largest)
	    		largest = entry.getValue();
	    }
	}

	System.out.println("Largest Val: " + largest);
	System.out.println("Largest Val ever: " + largestEver);

	}

	private static boolean calcIf(String reg, String comp, int val)
	{
		boolean rtn	= false;

		switch (comp) {
			case ">":
				if (registers.get(reg) > val)
					rtn = true;
			break;
			case "<":
				if (registers.get(reg) < val)
					rtn = true;
			break;
			case ">=":
				if (registers.get(reg) >= val)
					rtn = true;
			break;
			case "<=":
				if (registers.get(reg) <= val)
					rtn = true;
			break;
			case "==":
				if (registers.get(reg) == val)
					rtn = true;
			break;
			case "!=":
				if (registers.get(reg) != val)
					rtn = true;
			break;

			default:
				throw new IllegalArgumentException("Invalid comparator: " + comp);
		}
		return rtn;
	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input8.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split("\\s+");
				data.add(tempArray);
				if (registers.containsKey(tempArray[0]) == false)
					registers.put(tempArray[0], 0);
				if (registers.containsKey(tempArray[4]) == false)
					registers.put(tempArray[4], 0);
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