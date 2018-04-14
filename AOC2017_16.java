import java.lang.*;
import java.util.*;
import java.io.*;
import java.math.*;

public class AOC2017_16
{
	public static String[] data;
	public static char[] programs;

	public static void main ( String args[] )
	{
		programs = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};
		load();

//Day 1

		dance();
		result(1);

//Day 2
//		int[] finalPos = new int[16];
//		int k = 0;
//		for (char c: "abcdefghijklmnop".toCharArray()) {
//			finalPos[0] = findPos(c);
//			//System.out.println(finalPos[0]);
//			k++;
//		}

			//for (int j = 0; j < 16; j++)
			//	swap(j, finalPos[j]);

//		for (int i = 1; i < 1000000000; i++) {
//			dance();
//			if ("abcdefghijklmnop".equals(getResult()))
//				System.out.println("Cycle found: " + (i) + " " + getResult());
//		}


		//find cycle
		int i = 1;
		boolean found = false;
		while (found == false) {
			dance();
			if ("abcdefghijklmnop".equals(getResult())) {
				found = true;
				//System.out.println("Cycle found: " + (i) + " " + getResult());
			}
			else
				i++;
		}

		int count = 1000000000 % (i + 1);
		System.out.println(count);
		for (int j = 0; j < count; j++)
			dance();
		result(2);
	}

	private static void result(int j) {
		//System.out.println(data.length);
		System.out.print("\nPart " + j + ": " + getResult() + "\n");
	}

	private static String getResult() {
		return String.copyValueOf(programs);
	}

	private static void dance() {
				for (String s : data) {
			switch (s.charAt(0)) {
				case 's':
					int count = Integer.parseInt(s.substring(1,s.length()));
					for (int i = 0; i < count; i++){
						char temp = programs[15];
						for (int j = 14; j >= 0; j--)
							programs[j+1] = programs[j];
						programs[0] = temp;
					}
				break;
				case 'x':
				case 'p':
					String[] positionsS = s.substring(1,s.length()).split("/");
					int[] positionsI;

					if (s.charAt(0) == 'x')
						positionsI = new int[] {Integer.parseInt(positionsS[0]), Integer.parseInt(positionsS[1])};
					else {
						char[] positionsC = new char[] {positionsS[0].charAt(0), positionsS[1].charAt(0)};											positionsI = new int[] {findPos(positionsC[0]), findPos(positionsC[1])};
					}
					swap(positionsI[0], positionsI[1]);
				break;
				default:
					throw new IllegalArgumentException("Invalid comparator: " + data);
			}
		}
	}
	private static void swap(int a, int b) {
		char temp = programs[a];
		programs[a] = programs[b];
		programs[b] = temp;
	}

	private static int findPos(char c) {
		int i = 0;
		boolean found = false;
		while (found == false){
			if (programs[i] == c)
				found = true;
			else
				i++;
		}
		return i;
	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input16.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			data = line.split(",");
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