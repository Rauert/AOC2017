import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_2
{
	public static void main ( String args[] )
	{
		int min, max, sum, rows, columns;
		double a, b;
		sum = max = min = 0;
		rows = columns = 16;
		
		/*int[][] data = new int [][]
{ {409,194,207,470,178,454,235,333,511,103,474,293,525,372,408,428},

int[][] data = new int [][]
{ {4347,3350,196,162,233,4932,4419,3485,4509,4287,4433,4033,207,3682,2193,4223},


//Part 1

	for (int i = 0; i < rows; i++) {
		max = min = data[i][0];
		for (int j = 0; j < columns; j++) {
			//System.out.println(data[i][j]);			
			if (data[i][j] > max)
				max = data[i][j];
			if (data[i][j] < min)
				min = data[i][j];
		}
		sum = sum + (max - min);
	}

	System.out.println(sum);
	//System.out.println(max);
	//System.out.println(min);

//Part 2

	sum = 0;

	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < columns; j++) {
			for (int k = 0; k < columns; k++) {
				a = (double) data[i][j];
				b = (double) data[i][k];
				//System.out.println(data[i][j]);
				if (a > b){
					if (a%b==0){
						//System.out.println(a/b);
						sum = sum + (int) a / (int) b;
					}
				}
			}
		}
	}

	System.out.println(sum);
	}
}