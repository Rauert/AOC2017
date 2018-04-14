//import java.lang.Math;

public class AOC2017_15
{
  public static void main(String[] args)
  {
      long sum, factorA, factorB, divide, prevA, prevB, nextA, nextB;

		factorA = 16807;
		factorB = 48271;
		sum = 0;
		divide = 2147483647;
		//prevA = 65;
		//prevB = 8921;
		prevA = 699;
		prevB = 124;

//Day1

    	for (int i = 0; i < 40000000; i++) {
      		nextA = (prevA * factorA) % divide;
      		nextB = (prevB * factorB) % divide;

       		//System.out.println(Long.toBinaryString(nextA));
    		//System.out.println(Long.toBinaryString(nextB));
			sum += equalPairs(Long.toBinaryString(nextA), Long.toBinaryString(nextB));

    		prevA = nextA;
    		prevB = nextB;
  		}
    System.out.println("Part 1: " + sum);

//Day2

	//prevA = 65;
	//prevB = 8921;
	prevA = 699;
	prevB = 124;
	sum = 0;

	for (int i = 0; i < 5000000; i++) {
      		nextA = (prevA * factorA) % divide;
      		nextB = (prevB * factorB) % divide;

			while (nextA % 4 != 0) {
				prevA = nextA;
				nextA = (prevA * factorA) % divide;
			}
			while (nextB % 8 != 0) {
				prevB = nextB;
				nextB = (prevB * factorB) % divide;
			}
       		//System.out.println(Long.toBinaryString(nextA));
    		//System.out.println(Long.toBinaryString(nextB));
			sum += equalPairs(Long.toBinaryString(nextA), Long.toBinaryString(nextB));

    		prevA = nextA;
    		prevB = nextB;
  		}
    System.out.println("Part 2: " + sum);

  }

  private static int equalPairs(String a, String b) {
  	int rtn = 0;

    while (a.length() < 16)
      a = "0" + a;

     while (b.length() < 16)
      b = "0" + b;

    //System.out.println(a);
    //System.out.println(b);
    
    a = a.substring(a.length()-16, a.length());
    b = b.substring(b.length()-16, b.length());

	//System.out.println(a);
    //System.out.println(b);

    if (a.equals(b))
      rtn++;
    return rtn;
  }
}