import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_10
{
	public static void main ( String args[] )
	{
		int[] stringLst = new int [256];
		//int[] stringLst = new int [5];
		for (int i = 0; i < stringLst.length; i++)
			stringLst[i] = i;

		int pos = 0;
		int skip = 0;
		String dataStr = "102,255,99,252,200,24,219,57,103,2,226,254,1,0,69,216";
		//dataStr = "1,2,3";
		int[] data = new int [] {102,255,99,252,200,24,219,57,103,2,226,254,1,0,69,216};
		//int[] data = new int [] {3, 4, 1, 5};

//Part 1

	for (int i: data) {
		if (i <= stringLst.length){
				
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
		else
			System.out.println("Invalid input: " + i);
	}

	System.out.println("Answer: " + (stringLst[0] * stringLst[1]));

//Part 2

	for (int i = 0; i < stringLst.length; i++)
			stringLst[i] = i;
	pos = skip = 0;

	data = new int[dataStr.length()+5];
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
	System.out.println("Hex String: " + hex);
	}
}