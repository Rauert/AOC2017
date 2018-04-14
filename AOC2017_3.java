import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.Point;
import java.math.*;

public class AOC2017_3
{
	public static Map<Point, Double> sums;
	public static int finalX, finalY, inputNum, num;
	public static boolean found;
	public static Double finalSum;

	public static void main ( String args[] )
	{
		int  x, y, halfMN, magicNum;
		y = finalX = finalY = halfMN = 0;
		finalSum = 0.0;
		x = 1;
		magicNum = 2;
		num = 2;
		found = false;
		//inputNum=30;
		inputNum = 1024;
		//inputNum = 347991;

		sums = new HashMap<Point, Double>();
		sums.put(new Point(0, 0), 1.0);
		sums.put(new Point(x, y), 1.0);
		System.out.println(sums.size());
		System.out.println(sums.containsKey(new Point(0, 0)));
		System.out.println(sums.containsKey(new Point(x, y)));

//Part 1/2


	while (found == false){
		halfMN = (magicNum)/2;
		for (int i = 0; i < halfMN; i++) {
			y++;
			num++;
			calcFound(x, y);
		}
//System.out.println("next");
		for (int i = 0; i < magicNum; i++) {
			x--;
			num++;
			calcFound(x, y);
		}
//System.out.println("next");
		for (int i = 0; i < magicNum; i++) {
			y--;
			num++;
			calcFound(x, y);
		}
//System.out.println("next");
magicNum++;
		for (int i = 0; i < magicNum; i++) {
			x++;
			num++;
			calcFound(x, y);
		}
//System.out.println("next");
		//halfMN++;
		for (int i = 0; i < halfMN; i++) {
			y++;
			num++;
			calcFound(x, y);		
		}
//System.out.println("fin");
		magicNum++;
	}



	System.out.println(finalX + ", " + finalY);
	System.out.println(Math.abs(0 - finalX) + Math.abs(0 - finalY));
	System.out.println(finalSum);
	System.out.printf("dexp: %f\n", finalSum);




	}

	private static void calcFound(int inX, int inY)
	{
		Double sum = calcSum(inX, inY);
		System.out.println(num + ": " + inX + ", " + inY + " Sum: " + sum);
		System.out.println(sums.size());	
		sums.put(new Point(inX, inY), sum);
		if (num == inputNum){
			found = true;
			finalX = inX;
			finalY = inY;
			if (sums.containsKey(new Point(inX, inY)))
				finalSum = sums.get(new Point(inX, inY));
		}
	}

	private static Double calcSum(int inX, int inY)
	{
		Double sum = 0.0;

		if (sums.containsKey(new Point(inX-1, inY+1)))
			sum = sum + sums.get(new Point(inX-1, inY+1));
		if (sums.containsKey(new Point(inX, inY+1)))
			sum = sum + sums.get(new Point(inX, inY+1));
		if (sums.containsKey(new Point(inX+1, inY+1)))
			sum = sum + sums.get(new Point(inX+1, inY+1));
		if (sums.containsKey(new Point(inX+1, inY)))
			sum = sum + sums.get(new Point(inX+1, inY));
		if (sums.containsKey(new Point(inX+1, inY-1)))
			sum = sum + sums.get(new Point(inX+1, inY-1));
		if (sums.containsKey(new Point(inX, inY-1)))
			sum = sum + sums.get(new Point(inX, inY-1));
		if (sums.containsKey(new Point(inX-1, inY-1)))
			sum = sum + sums.get(new Point(inX-1, inY-1));
		if (sums.containsKey(new Point(inX-1, inY)))
			sum = sum + sums.get(new Point(inX-1, inY));

		return sum;
	}
}