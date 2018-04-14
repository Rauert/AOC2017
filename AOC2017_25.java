import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_25
{
	private static int[] tape;
	private static int cursor;
	private static char state;

	public static void main ( String args[] )
	{
		tape = new int[1000000];
		cursor = 500000;
		state = 'A';

//Part 1

		for (int l = 0; l < 12368930; l++) {
			advance();
		}
		System.out.println("Part 1: " + count());

//Part 2

//		System.out.println("Part 2: " + sum);
	}

	private static void advance() {
		switch (state) {
			case 'A':
				if (tape[cursor] == 1) {
					tape[cursor] = 0;
					cursor++;
					state = 'C';
				}
				else {
					tape[cursor] = 1;
					cursor++;
					state = 'B';
				}
			break;
			case 'B':
				if (tape[cursor] == 1) {
					tape[cursor] = 0;
					cursor++;
					state = 'D';
				}
				else {
					tape[cursor] = 0;
					cursor--;
					state = 'A';
				}
			break;
			case 'C':
				if (tape[cursor] == 1) {
					tape[cursor] = 1;
					cursor++;
					state = 'A';
				}
				else {
					tape[cursor] = 1;
					cursor++;
					state = 'D';
				}
			break;
			case 'D':
				if (tape[cursor] == 1) {
					tape[cursor] = 0;
					cursor--;
					state = 'D';
				}
				else {
					tape[cursor] = 1;
					cursor--;
					state = 'E';
				}
			break;
			case 'E':
				if (tape[cursor] == 1) {
					tape[cursor] = 1;
					cursor--;
					state = 'B';
				}
				else {
					tape[cursor] = 1;
					cursor++;
					state = 'F';
				}
			break;
			case 'F':
				if (tape[cursor] == 1) {
					tape[cursor] = 1;
					cursor++;
					state = 'E';
				}
				else {
					tape[cursor] = 1;
					cursor++;
					state = 'A';
				}
			break;
			default:
				throw new IllegalArgumentException("Invalid comparator: " + state);
		}
	}

	private static int count()
	{
		int sum = 0;
		for (int y = 0; y < tape.length; y++) {
			if (tape[y] == 1)
				sum++;
		}
		return sum;
	}
}