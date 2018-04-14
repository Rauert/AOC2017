import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_6
{
	public static int[] data;
	public static LinkedList<String> history;

	public static void main ( String args[] )
	{
		int payload, sum, max, j, totCycles;
		sum = payload = max = j = totCycles = 0;
		boolean found = false;
		//boolean found2 = false;
		//String chkState = "";

//		data = new int[] {0,2,7,0};
		data = new int[] {5,1,10,0,1,7,13,14,3,12,8,10,7,12,0,6};
		history = new LinkedList<String>();

//Part 1

	while(found == false)
	{
		max = 0;
		for (int i = 1; i < data.length; i++) {
			if (data[i] > data[max])
				max = i;
		}
		//System.out.println("Max: " + max + ": " + data[max]);
		payload = data[max];
		data[max] = 0;

		for (int i = 0; i < payload; i++) {
			max++;
			data[max % data.length]++;			
		}

		sum++;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.length; i++) {
			sb.append(data[i]);
			sb.append("-");
		}

		String state = sb.toString();
		for (int i = 0; i < history.size(); i++) {
			if (state.equals(history.get(i))) {
				found = true;
				totCycles = i;
			}
		}
		System.out.println(state);
		history.add(state);
	}

	//System.out.println(totCycles);
	System.out.println(sum);

//Part 2

	System.out.println(history.size() - 1 - totCycles);
	}

}