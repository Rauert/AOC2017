import java.lang.*;
import java.util.*;
import java.io.*;
//import java.math.BigInteger;

public class AOC2017_14
{
	private static int[] stringLst;
	private static int[][] grid;

	public static void main ( String args[] )
	{
		String input = "jxqlasbh";
		stringLst = new int [256];
		grid = new int[128][128];
		int sum = 0;
		//int[] stringLst = new int [5];
		//for (int i = 0; i < stringLst.length; i++)
		//	stringLst[i] = i;

		//int pos = 0;
		//int skip = 0;
		//String dataStr = "102,255,99,252,200,24,219,57,103,2,226,254,1,0,69,216";
		//int[] data = new int [] {102,255,99,252,200,24,219,57,103,2,226,254,1,0,69,216};

//Part 1

	for (int i = 0; i < 128; i++) {
		sum += count((hex(input + "-" + i)), i);
	}
	//String hexa = hex(input + "-0");
	//count(hexa);
	System.out.println("Part 1: " + sum);


//Part 2

	sum = 0;
//	System.out.print("\n");
//	for (int i = 0; i < 128; i++) {
//		for (int j = 0; j < 128; j++)
//			System.out.print(grid[i][j]);
//		System.out.print("\n");
//	}

	//System.out.println(findNumberConnected(0, 0, grid));
	
	for (int i = 0; i < 128; i++) {
		for (int j = 0; j < 128; j++) {
			if (grid[i][j] == 1) {
				sum++;
				search(i, j);
			}
		}
	}
	System.out.println("Part 2: " + sum);

	for (int i = 0; i < 128; i++) {
		for (int j = 0; j < 128; j++)
			System.out.print(grid[i][j]);
		System.out.print("\n");
	}

	}

	private static void search(int a, int b) {
		
		LinkedList<Integer> queueA = new LinkedList<Integer>();
		LinkedList<Integer> queueB = new LinkedList<Integer>();
		int currA, currB;
		grid[a][b] = 2;

		queueA.add(a);
		queueB.add(b);

		while (queueA.size() != 0)
        {
           currA = queueA.poll();
           currB = queueB.poll();

			//List<Integer> cA = new LinkedList<Integer>();
			//List<Integer> cB = new LinkedList<Integer>();

			//Up
			if (currA > 0) {
				if (grid[currA-1][currB] == 1) {
					grid[currA-1][currB] = 2;
					queueA.add(currA-1);
                    queueB.add(currB);
				}
			}

			//Down
			if (currA < 127) {
				if (grid[currA+1][currB] == 1) {
					grid[currA+1][currB] = 2;
					queueA.add(currA+1);
                    queueB.add(currB);
				}
			}

			//Left
			if (currB > 0) {
				if (grid[currA][currB-1] == 1) {
					grid[currA][currB-1] = 2;
					queueA.add(currA);
                    queueB.add(currB-1);
				}
			}

			//Right
			if (currB < 127) {
				if (grid[currA][currB+1] == 1) {
					grid[currA][currB+1] = 2;
					queueA.add(currA);
                    queueB.add(currB+1);
				}
			}
			//System.out.println(a + " " + b + ": " + queueA.size());

//            for (int i = 0; i < cA.size(); i++)
//            {
//            	int childA = cA.get(i);
//            	int childB = cB.get(i);
//                if (grid[childA][childB] == 1)
//                {
//                    grid[childA][childB] = 2;
//                    queueA.add(childA);
//                    queueB.add(childB);
//                }
//            }
        }

	}

	private static int count(String hex, int rowNum) {
		int sum = 0;
		String binary;
		String row = "";
		binary = "";
		for(int i = 0; i < hex.length(); i++) {
			//sum += Integer.parseInt(hex.charAt(i), 16);
			binary = Integer.toBinaryString(Integer.parseInt(hex.substring(i,i+1), 16));
			if (binary.length() < 4) {
				for (int k = binary.length(); k < 4; k++)
					binary = "0" + binary;
			}
			for(int j = 0; j < binary.length(); j++) {
				if (binary.charAt(j) == '1')
					sum++;
			}
			row = row + binary;
			//System.out.println(binary);
		}
		for (int i = 0; i < row.length(); i++) {
			grid[rowNum][i] = Integer.parseInt(row.substring(i,i+1));
		}
		//System.out.println(row);
		//System.out.println(row.length());
		
		//int num = (Integer.parseInt(hex, 16));
//		BigInteger num = new BigInteger(hex, 16);
//		System.out.println(num.toString(2));
//		System.out.println(num.toString(2).length());
		return sum;
	}

	private static String hex(String dataStr) {
		for (int i = 0; i < stringLst.length; i++)
			stringLst[i] = i;
		int pos = 0;
		int skip = 0;
		int[] data = new int[dataStr.length()+5];

		for (int i = 0; i < dataStr.length(); i++)
			data[i] = (int) dataStr.charAt(i);

		data[data.length-5] = 17;
		data[data.length-4] = 31;
		data[data.length-3] = 73;
		data[data.length-2] = 47;
		data[data.length-1] = 23;
	
		//for (int i = 0; i < data.length; i++)
		//	System.out.print(data[i] + " ");
		//System.out.println("\r");
		//for (int i = 0; i < data.length; i++)
		//	System.out.print(Character.toString((char) data[i]));
		//System.out.println("\r");
	
		for(int l = 0; l < 64; l++) {
			for (int i: data) {
				for(int j = 0; j < i / 2; j++) {
					//System.out.println((pos+j)%stringLst.length);
					//System.out.println((pos + i - j - 1)%stringLst.length);
				    int temp = stringLst[(pos+j)%stringLst.length];
				    stringLst[(pos+j)%stringLst.length] = stringLst[(pos + i - j - 1)%stringLst.length];
				    stringLst[(pos + i - j - 1)%stringLst.length] = temp;
				}
				pos = (pos + i + skip)%stringLst.length;
				skip++;
	
	//			for (int k = 0; k < stringLst.length; k++)
	//				System.out.print(stringLst[k] + " ");
	//			System.out.println("\r");
	//			System.out.println("Position: " + pos);
	//			System.out.println("Skip: " + skip);
	//			System.out.println("\r");
			}
		}
	
		int[] denseHash = new int [16];
		int count = 0;
		for (int i = 0; i < stringLst.length; i = i + 16){
			denseHash[count] = stringLst[i] ^ stringLst[i+1] ^ stringLst[i+2] ^ stringLst[i+3] ^ stringLst[i+4] ^ stringLst[i+5] ^ stringLst[i+6] ^ stringLst[i+7] ^ stringLst[i+8] ^ stringLst[i+9] ^ stringLst[i+10] ^ stringLst[i+11] ^ stringLst[i+12] ^ stringLst[i+13] ^ stringLst[i+14] ^ stringLst[i+15];
			count++;
		}
	
	//	for (int i = 0; i < denseHash.length; i++)
	//		System.out.print(denseHash[i] + " ");
	
		String hex = "";
		String tempHex = "";
		for (int i = 0; i < denseHash.length; i++){
			//System.out.println(Integer.toHexString(denseHash[i]));
			tempHex = Integer.toHexString(denseHash[i]);
			if (tempHex.length() == 1)
				tempHex = "0" + tempHex;
			hex = hex + tempHex;
		}
		return hex;
	}
}