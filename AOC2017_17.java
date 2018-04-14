import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_17
{
	public static int[] lock;

	public static void main ( String args[] )
	{
		lock = new int[2019];
		lock[0] = 0;
		int steps = 3;
		steps = 316;
		int pos = 0;
		int size = 1;

//Part 1

		for (int i = 1; i < 2018; i++) {
			pos = (pos + steps) % size;
			size++;
			pos++;
			//int count = pos + 1;
			for (int j = size - 1; j >= pos; j--)
				lock[j] = lock[j-1];
			lock[pos] = i;

			//System.out.print(pos + ": ");
			//for (int j = 0; j < size; j++)
			//	System.out.print(lock[j] + " ");
			//System.out.print("\n");
		}
		System.out.println("Part 1: " + lock[pos-1] + " " + lock[pos] + " " + lock[pos+1]);
		//System.out.println("Part 1 " + sum);

//Part 2

	//lock = new int[50000002];
		//lock[0] = 0;
		pos = 0;
		size = 1;
		int last = 0;

		for (int i = 1; i < 50000001; i++) {
			pos = (pos + steps) % size;
			pos++;
				size++;

			if (pos == 1) {
				System.out.println(i);
			//int count = pos + 1;
			//for (int j = size - 1; j >= pos; j--)
			//	lock[j] = lock[j-1];
			last = i;
			}
						//System.out.println((double) i / 50000000.0);
		
			//System.out.print(pos + ": ");
			//for (int j = 0; j < size; j++)
			//	System.out.print(lock[j] + " ");
			//System.out.print("\n");
		}
		System.out.println("Part 2: " + last);

	}
}