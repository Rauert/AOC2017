import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_20
{
	public static LinkedList<XYZ> pos;
	public static LinkedList<XYZ> vel;
	public static LinkedList<XYZ> acc;

	public static void main ( String args[] )
	{
		
		load();

//Part 1

		simulate(false);
		int closest = findClosest();
		System.out.println(closest + " " + calcDist(pos.get(closest)));

//Part 2
//Inefficient!! Switch to an int array??

		load();
		detectColl();
		simulate(true);
	}

	private static void simulate(boolean p2)
	{
		for (int j = 0; j < 10000; j++) {
			for (int i = 0; i < pos.size(); i++) {
				XYZ p, v, a;
				p = pos.get(i);
				v = vel.get(i);
				a = acc.get(i);

				v.x += a.x;
				v.y += a.y;
				v.z += a.z;
				p.x += v.x;
				p.y += v.y;
				p.z += v.z;
			}
			if (p2) {
				detectColl();
				System.out.println(j + " " + pos.size());
			}
		}
	}

	private static void detectColl()
	{
		LinkedList<Integer> rmv = new LinkedList<Integer>();
		for (int j = 0; j < pos.size(); j++) {
			for (int i = 0; i < pos.size(); i++) {
				if (j != i && pos.get(j).equals(pos.get(i))) {
					if (!rmv.contains(j))
						rmv.add(j);
					if (!rmv.contains(i))
						rmv.add(i);
				}
			}
		}
		//Integer[] rmvA = rmv.toArray();
		//Arrays.sort(rmvA);
		Collections.sort(rmv);
		//System.out.println(rmv);
		for (int i = rmv.size() - 1; i >= 0; i--) {
			int del = (int) rmv.get(i);
			pos.remove(del);
			vel.remove(del);
			acc.remove(del);
		}

	}

	private static int findClosest()
	{
		int min = 0;
		for (int i = 1; i < pos.size(); i++) {
			XYZ a, b;
			a = pos.get(min);
			b = pos.get(i);
			if (calcDist(b) < calcDist(a))
				min = i;
		}
		return min;
	}

	private static long calcDist(XYZ p)
	{
		return (Math.abs(p.x) + Math.abs(p.y) + Math.abs(p.z));
	}

	private static void load()
	{
		pos = new LinkedList<XYZ>();
		vel = new LinkedList<XYZ>();
		acc = new LinkedList<XYZ>();

		try {
			FileReader fr = new FileReader("input20.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split(", ");
				String tempXYZ[] = tempArray[0].substring(3, tempArray[0].length() - 1).split(",");
				pos.add(new XYZ(Long.parseLong(tempXYZ[0]), Long.parseLong(tempXYZ[1]), Long.parseLong(tempXYZ[2])));
				tempXYZ = tempArray[1].substring(3, tempArray[1].length() - 1).split(",");
				vel.add(new XYZ(Long.parseLong(tempXYZ[0]), Long.parseLong(tempXYZ[1]), Long.parseLong(tempXYZ[2])));
				tempXYZ = tempArray[2].substring(3, tempArray[2].length() - 1).split(",");
				acc.add(new XYZ(Long.parseLong(tempXYZ[0]), Long.parseLong(tempXYZ[1]), Long.parseLong(tempXYZ[2])));
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

public static class XYZ {

    long x;
    long y;
    long z;

    public XYZ(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

	public boolean equals(XYZ point) {
		if (this.x == point.x && this.y == point.y && this.z == point.z)
			return true;
		else
			return false;
	}

}

}