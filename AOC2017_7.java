import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_7
{
	public static LinkedList<String[]> childData;
	public static LinkedList<ProgramNode> nodes;

	public static void main ( String args[] )
	{
		ProgramNode head;

		childData = new LinkedList<String[]>();
		nodes = new LinkedList<ProgramNode>();

		load();

		//Connect child nodes forming tree.
		for (String[] s : childData) {
			ProgramNode currNode = getNode(s[0]);
			for (int i = 1; i < s.length; i++) {
				currNode.addChild(getNode(s[i]));
			}
		}

//Part 1

		//Find root node
		head = nodes.get(0);
		while (head.parent != null)
			head = head.parent;

		System.out.println(head.name);


//Part 2

		//Calculate totalWeights for all nodes.

		int currWeight;
		ProgramNode currNode;

		for (int i = 0; i < nodes.size(); i++) {
			currNode = nodes.get(i);
			//if (currNode.children.size() == 0) {
				currWeight = currNode.weight;
				while (currNode.parent != null) {
					currNode = currNode.parent;
					currNode.totWeight = currNode.totWeight + currWeight;
				}
			//}
		}


		//***********************************************************************/
		//Try going forwards and calculating total with a queue. Then reverse it?
		//Can just follow uneven path, recursion.
		//***********************************************************************/




		boolean found = false;
		int sum, balVal, unbalVal, currBalVal, currUnbalVal;
		currNode = head;
		balVal = unbalVal = currBalVal = currUnbalVal = 0;

		int x = 0;

		while (currNode.children.size() != 0) {
			System.out.println(currNode.name);
			for (int i = 0; i < currNode.children.size(); i++) {
				System.out.println(currNode.children.get(i).totWeight);
			}
			System.out.println("\n");
			if (currNode.children.size() > 2) {
				int[] weights = new int[currNode.children.size()];
				for (int i = 0; i < currNode.children.size(); i++) {
					weights[i] = currNode.children.get(i).totWeight;
				}
				int i = 2;
				while ( i < weights.length) {
					if (weights[i-2] == weights[i-1] && weights[i-1] == weights[i])
						i++;
					else {
						if (weights[i-2] == weights[i-1]){
							x = i;
						}
						if (weights[i-1] == weights[i]){
							x = i - 2;
						}
						if (weights[i] == weights[i-2]){
							x = i - 1;
						}
						i = weights.length;
					}
				}
				currNode = currNode.children.get(x);
			}
			else if (currNode.children.size() == 2) {
				if (currNode.children.get(0).totWeight == currNode.children.get(1).totWeight) {
					currNode = currNode.children.get(0);
				}
				else
				{
					if (currNode.children.get(0).totWeight > currNode.children.get(1).totWeight) {
						x = 0;
					}
					else {
						x = 1;
					}
					currNode = currNode.children.get(x);
				}
			}
			else {
				currNode = currNode.children.get(0);
			}
		}


		currNode = head;

		while (found == false) {
			balVal = currBalVal;
			unbalVal = currUnbalVal;
			System.out.println(currNode.name);
			for (int i = 0; i < currNode.children.size(); i++) {
				System.out.println(currNode.children.get(i).name + " " + currNode.children.get(i).totWeight + " " + currNode.children.get(i).weight);
			}
			System.out.println("\n");
			if (currNode.children.size() > 2) {
				sum = 0;
				int[] weights = new int[currNode.children.size()];
				for (int i = 0; i < currNode.children.size(); i++) {
					sum = sum + currNode.children.get(i).totWeight;
					weights[i] = currNode.children.get(i).totWeight;
				}
				if (sum / weights.length == weights[0]) {
					found = true;
					currNode = currNode.parent;
				}
				else {
					int i = 2;
					while ( i < weights.length) {
						if (weights[i-2] == weights[i-1] && weights[i-1] == weights[i])
							i++;
						else {
							if (weights[i-2] == weights[i-1]){
								currBalVal = i - 2;
								currUnbalVal = i;
							}
							if (weights[i-1] == weights[i]){
								currBalVal = i - 1;
								currUnbalVal = i - 2;
							}
							if (weights[i] == weights[i-2]){
								currBalVal = i;
								currUnbalVal = i - 1;
							}
							i = weights.length;
						}
					}
					currNode = currNode.children.get(currUnbalVal);
				}
			}
			else if (currNode.children.size() == 2) {
				if (currNode.children.get(0).totWeight == currNode.children.get(1).totWeight) {
					found = true;
					currNode = currNode.parent;
				}
				else
				{
					if (currNode.children.get(0).totWeight > currNode.children.get(1).totWeight) {
						currBalVal = 1;
						currUnbalVal = 0;
					}
					else {
						currBalVal = 0;
						currUnbalVal = 1;
					}
					currNode = currNode.children.get(currUnbalVal);
				}
			}
			else {
				found = true;
				currNode = currNode.parent;
			}
		}

		System.out.println("Unbalanced Val: " + currNode.children.get(unbalVal).totWeight + " " + currNode.children.get(unbalVal).weight);
		System.out.println("Balanced Val: " + currNode.children.get(balVal).totWeight + " " + currNode.children.get(balVal).weight);
		System.out.println("Total Weight: " + head.totWeight);
		System.out.println("Answer: " + (currNode.children.get(unbalVal).weight + currNode.children.get(balVal).totWeight - currNode.children.get(unbalVal).totWeight));
//	for (int i = 0; i < nodes.size(); i++) {
//		System.out.println(nodes.get(i).name + " " + nodes.get(i).totWeight);
//	}

	//head.print();

//		currNode = getNode("ycpcv");
//		System.out.println(currNode.name);
//		for (int i = 0; i < currNode.children.size(); i++) {
//			System.out.println(currNode.children.get(i).name + " " + currNode.children.get(i).totWeight);
//		}

	}



	private static void load()
	{
		int sum = 0;
		try {
			FileReader fr = new FileReader("input7.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split(" \\(|\\) -> |\\)|, |\\s+");
				nodes.add(new ProgramNode(tempArray[0], Integer.parseInt(tempArray[1])));
				sum = sum + Integer.parseInt(tempArray[1]);
				if (tempArray.length > 2) {
					String newString[] = new String[tempArray.length - 1];
					newString[0] = tempArray[0];
					for (int i = 2; i < tempArray.length; i++)
						newString[i-1] = tempArray[i];
					childData.add(newString);
				}
			}
			fr.close();
			System.out.println(sum);
		}
		catch(FileNotFoundException fN) {
			fN.printStackTrace();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

	private static ProgramNode getNode(String s)
	{
		int i = 0;
		boolean found = false;
		ProgramNode rtnNode = null;
		while (i < nodes.size() && found == false) {
			if (nodes.get(i).equals(s)) {
				rtnNode = nodes.get(i);
				found = true;
			}
			i++;
		}

		return rtnNode;
	}


public static class ProgramNode {

    String name;
    int weight;
    int totWeight;
    ProgramNode parent;
    List<ProgramNode> children;

    public ProgramNode(String name, int weight) {
        this.name = name;
        this.weight = this.totWeight = weight;
        //this.totWeight = 0;
        this.children = new LinkedList<ProgramNode>();
    }

    public void addChild(ProgramNode child) {
        //TreeNode<T> childNode = new TreeNode<T>(child);
        child.parent = this;
        this.children.add(child);
        //updateTotal(child);
    }

	public boolean equals(String s) {
		if (this.name.equals(s))
			return true;
		else
			return false;
	}

	public void print() {
        print("", true);
    }
   
	private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + name + " " + totWeight);
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1)
                    .print(prefix + (isTail ?"    " : "│   "), true);
        }
    }
	/*private void updateTotal(ProgramNode child){
		int inWeight = child.weight;
		while (child.parent != null) {
			child.totWeight = child.totWeight + inWeight;
			child = child.parent;
		}
	}*/
}

}