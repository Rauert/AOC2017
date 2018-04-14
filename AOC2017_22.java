import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_22
{
	private static int[][] grid;
	private static LinkedList<int[]> data;
	private static int x;
	private static int y;

	public static void main ( String args[] )
	{
		grid = new int[5000][5000];
		data = new LinkedList<int[]>();
		x = 2500;
		y = 2500;
		char dir = 'U';
		int sum = 0;

//Part 1

		load();
		//data = new LinkedList<int[]>();
		//data.add(new int[] {0,0,1});
		//data.add(new int[] {1,0,0});
		//data.add(new int[] {0,0,0});
		joinMatrixes();

		//System.out.println(grid[y-1][x-2] + " " + grid[y-1][x-1] + " " + grid[y-1][x] + " " + grid[y-1][x+1] + " " + grid[y-1][x+2]);
		//System.out.println(grid[y][x-2] + " " + grid[y][x-1] + " " + grid[y][x] + " " + grid[y][x+1] + " " + grid[y][x+2]);
		//System.out.println(grid[y+1][x-2] + " " + grid[y+1][x-1] + " " + grid[y+1][x] + " " + grid[y+1][x+1] + " " + grid[y+1][x+2]);

		for (int l = 0; l < 10000; l++) {
			if (grid[y][x] == 1) {
				dir = turnRight(dir);
				grid[y][x] = 0;
			}
			else {
				dir = turnLeft(dir);
				grid[y][x] = 1;
				sum++;
			}
			advance(dir);
		}
		System.out.println("Part 1: " + sum);

//Part 2

	grid = new int[5000][5000];
	data = new LinkedList<int[]>();
	x = 2500;
	y = 2500;
	dir = 'U';
	sum = 0;
	load();
	//data = new LinkedList<int[]>();
	//data.add(new int[] {0,0,1});
	//data.add(new int[] {1,0,0});
	//data.add(new int[] {0,0,0});
	joinMatrixes();

	for (int l = 0; l < 10000000; l++) {
			if (grid[y][x] == 1) {
				dir = turnRight(dir);
				grid[y][x] = 2; //flagged.
			}
			else if (grid[y][x] == 0){
				dir = turnLeft(dir);
				grid[y][x] = 3; //Weakened.
			}
			else if (grid[y][x] == 2){
				dir = turnBack(dir);
				grid[y][x] = 0;
				//sum++;
			}
			else {
				grid[y][x] = 1;
				sum++;
			}
			advance(dir);
		}
		System.out.println("Part 2: " + sum);
	}

	private static void advance(char inDir) {
		switch (inDir) {
			case 'U':
				y--;
			break;
			case 'D':
				y++;
			break;
			case 'L':
				x--;
			break;
			case 'R':
				x++;
			break;
			default:
				throw new IllegalArgumentException("Invalid comparator: " + inDir);
		}
	}

	private static char turnRight(char inDir) {
		char dir = ' ';
		switch (inDir) {
			case 'U':
				dir = 'R';
			break;
			case 'D':
				dir = 'L';
			break;
			case 'L':
				dir = 'U';
			break;
			case 'R':
				dir = 'D';
			break;
			default:
				throw new IllegalArgumentException("Invalid comparator: " + inDir);
		}
		return dir;
	}

	private static char turnLeft(char inDir) {
		char dir = ' ';
		switch (inDir) {
			case 'U':
				dir = 'L';
			break;
			case 'D':
				dir = 'R';
			break;
			case 'L':
				dir = 'D';
			break;
			case 'R':
				dir = 'U';
			break;
			default:
				throw new IllegalArgumentException("Invalid comparator: " + inDir);
		}
		return dir;
	}

	private static char turnBack(char inDir) {
		char dir = ' ';
		switch (inDir) {
			case 'U':
				dir = 'D';
			break;
			case 'D':
				dir = 'U';
			break;
			case 'L':
				dir = 'R';
			break;
			case 'R':
				dir = 'L';
			break;
			default:
				throw new IllegalArgumentException("Invalid comparator: " + inDir);
		}
		return dir;
	}

	private static void joinMatrixes()
	{
		int x, y;
		int len = data.size();
		x = y = 2500 - (len / 2);
		//System.out.println(len / 2);
		//System.out.println(x);

		for (int col = 0; col < len; col++) {
			for (int row = 0; row < len; row++) {
				grid[y][x] = data.get(col)[row];
				x++;
			}
			y++;
			x -= len;
		}
	}

	private static void load()
	{
		try {
			FileReader fr = new FileReader("input22.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				int[] row = new int[line.length()];
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '#')
						row[i] = 1;
					else
						row[i] = 0;
				}
				data.add(row);
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