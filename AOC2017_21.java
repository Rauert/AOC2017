import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_21
{
	private static int[][] grid;
	private static LinkedList<int[][]> rules2i; //2x2 rules - input. Size 2.
	private static LinkedList<int[][]> rules2o; //2x2 rules - output. Size 3.
	private static LinkedList<int[][]> rules3i; //Size 3.
	private static LinkedList<int[][]> rules3o; //Size 4.

	public static void main ( String args[] )
	{
		int size = 3;
		grid = new int[][] {{0,1,0},{0,0,1},{1,1,1}};
		rules2i = new LinkedList<int[][]>();
		rules2o = new LinkedList<int[][]>();
		rules3i = new LinkedList<int[][]>();
		rules3o = new LinkedList<int[][]>();

//Part 1 & 2

		load();
		//System.out.println("Part 1: " + rules2i.size() + " " + rules2o.size() + " " + rules3i.size() + " " + rules3o.size());
		//printMatrix(grid);

	//	for (int i = 0; i < rules2i.size(); i++) {
	//		printMatrix(rules2i.get(i));
	//		printMatrix(rules2o.get(i));
	//	}
	//	for (int i = 0; i < rules3i.size(); i++) {
	//		printMatrix(rules3i.get(i));
	//		printMatrix(rules3o.get(i));
	//	}

		//printMatrix(grid);

		for (int l = 0; l < 18; l++) {
			int newSize, div;
			LinkedList<int[][]> rulesI, rulesO;
			if (size % 2 == 0) {
				div = 2;
				newSize = size / 2 * 3;
				rulesI = rules2i;
				rulesO = rules2o;
			}
			else {
				div = 3;
				newSize = size / 3 * 4;
				rulesI = rules3i;
				rulesO = rules3o;
			}

			LinkedList<int[][]> oldList, newList;
			oldList = splitGrid(div, size);
			newList = new LinkedList<int[][]>();
			for (int k = 0; k < oldList.size(); k++)
				newList.add(findRule(oldList.get(k), rulesI, rulesO));
			grid = joinMatrixes(newList, newSize);
			size = newSize;

			System.out.println(l + ": " + countMatrix(grid));
			//printMatrix(grid);
		}
	}

	private static LinkedList<int[][]> splitGrid(int div, int size)
	{
		LinkedList<int[][]> list = new LinkedList<int[][]>();
		int x, y, squares;
		squares = (size / div) * (size / div);
		x = y = 0;

		for (int i = 0; i < squares; i++) {
			int[][] m = new int[div][div];
			for (int col = 0; col < div; col++) {
				for (int row = 0; row < div; row++) {
					m[col][row] = grid[y][x];
					x++;
				}
				y++;
				x -= div;
			}
			if (x + div < size) {
				x += div;
				y -= div;
			}
			else
				x = 0;

			list.add(m);
		}
		return list;
	}

	private static int[][] findRule(int[][] m, LinkedList<int[][]> rulesI, LinkedList<int[][]> rulesO)
	{
		LinkedList<int[][]> list = new LinkedList<int[][]>(); //Size = 8
		list.add(m);
		list.add(mirror(m));
		list.add(rotate(m));
		list.add(mirror(list.get(2)));
		list.add(rotate(list.get(2)));
		list.add(mirror(list.get(4)));
		list.add(rotate(list.get(4)));
		list.add(mirror(list.get(6)));

		boolean found = false;
		int i = 0;
		while (found == false && i < rulesI.size()) {
			for (int j = 0; j < list.size(); j++) {
				if (Arrays.deepEquals(list.get(j), rulesI.get(i)))
					found = true;
			}
			if (found == false) i++;
		}
		if (found == false) System.out.println("RULE NOT FOUND!!");

		//printMatrix(rulesI.get(i));
		//System.out.println(" => ");
		//printMatrix(rulesO.get(i));

		return rulesO.get(i);
	}

	private static int[][] mirror(int[][] m)
	{
		int len = m.length;
		int[][] rtn = new int[len][len];
		for (int y = 0; y < len; y++) {
			rtn[y][0] = m[y][len - 1];
			rtn[y][len - 1] = m[y][0];
			if (len == 3) rtn[y][1] = m[y][1];
		}
		return rtn;
	}

	private static int[][] rotate(int[][] m)
	{
		int len = m.length;
		int rx = 0;
		int[][] rtn = new int[len][len];
		for (int x = 0; x < len; x++) {
			for (int y = len-1; y >= 0; y--) {
				rtn[x][rx] = m[y][x];
				rx++;
			}
			rx = 0;
		}
		return rtn;
	}

	private static int[][] joinMatrixes(LinkedList<int[][]> list, int size)
	{
		int[][] newGrid = new int[size][size];
		int x, y;
		x = y = 0;
		int len = list.get(0).length;
		for (int i = 0; i < list.size(); i++) {
			int[][] m = list.get(i);
			for (int col = 0; col < len; col++) {
				for (int row = 0; row < len; row++) {
					newGrid[y][x] = m[col][row];
					x++;
				}
				y++;
				x -= len;
			}
			if (x + len < size) {
				x += len;
				y -= len;
			}
			else
				x = 0;
		}
		return newGrid;
	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input21.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split(" => ");
				if (tempArray[0].length() == 5) { //2x2 rule
					//char[] in = tempArray[0].toCharArray();
					rules2i.add(importRule(tempArray[0], 2));
					rules2o.add(importRule(tempArray[1], 3));
				}
				else {  //3x3 rule
					rules3i.add(importRule(tempArray[0], 3));
					rules3o.add(importRule(tempArray[1], 4));
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

	private static int[][] importRule(String s, int size)
	{
		int[][] rtn = new int[size][size];
		int row = 0;
		int col = 0;
		//System.out.println(s);
		for(char c : s.toCharArray()) {
			switch (c) {
				case '.':
					rtn[col][row] = 0;
					row++;
				break;
				case '#':
					rtn[col][row] = 1;
					row++;
				break;
				case '/':
					row = 0;
					col++;
				break;
				default:
					throw new IllegalArgumentException("Invalid comparator: " + c);
			}

		}
		return rtn;
	}

	private static void printMatrix(int[][] m)
	{
		int size = m.length;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(m[y][x] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	private static int countMatrix(int[][] m)
	{
		int size = m.length;
		int sum = 0;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (m[y][x] == 1)
					sum++;
			}
		}
		return sum;
	}
}