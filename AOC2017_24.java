import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_24
{
	public static LinkedList<Node> nodes;
	public static int lrgstSum;
	public static int lngstSum;
	public static int lngstNum;
	public static int totPaths;

	public static void main ( String args[] )
	{
		Node currNode;
		nodes = new LinkedList<Node>();
		lrgstSum = lngstSum = lngstNum = totPaths = 0;

		load();
		for (int i = 0; i < nodes.size(); i++) {
			currNode = nodes.get(i);
			System.out.print(currNode.a + "/" + currNode.b + " - ");
			for (Node n: currNode.childrenA)
				 System.out.print(n.a + "/" + n.b + " ");
			System.out.print("\n       ");

			for (Node n: currNode.childrenB)
				 System.out.print(n.a + "/" + n.b + " ");
			System.out.print("\n");
		}

//Part 1 & 2

		//BFS(getNode("0"));

		LinkedList<Node> roots = new LinkedList<Node>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).a == 0 || nodes.get(i).b == 0)
				roots.add(nodes.get(i));
		}

		for (Node n: roots) {
			LinkedList<Node> queue = new LinkedList<Node>();
			queue.add(n);
			BFS(queue, 0);
			//System.out.print("\n");
			//reset();
		}

		System.out.println("Part 1: " + lrgstSum);
		System.out.println("Part 2: Sum " + lngstSum + " Num " + lngstNum);
		System.out.println("Total Paths " + totPaths);

		//Reset

//		for (int i = 0; i < nodes.size(); i++) {
//			currNode = nodes.get(i);
//			if (currNode.visited == false) {
//				groups++;
//				BFS(currNode);
//			}
//		}
//		System.out.println("Part 2 " + groups);
	}

	public static void DFS(Node root, int last)
    {       
        //System.out.print(root.a + "/" + root.b + "  ");
        //root.mark();

		
		Stack<Node> st = new Stack<Node>();
		//Stack<Node> prntSt = new Stack<Node>();
		Stack<Integer> lastST = new Stack<Integer>();
		st.push(root);
		lastST.push(last);
		while (st.size() != 0){
	         Node n = st.peek();
       		 //prntSt.push(n);
	         int next = lastST.peek();
	         //if (n.visited == false) {      
	            n.mark();

				List<Node> children;

				if (next == n.a) {
					children = n.childrenB;
					next = n.b;
				}
				else {
					children = n.childrenA;
					next = n.a;
				}

	            if (children.size() == 0 || visited(children)) {
	            	printPath(st);
	            	st.pop();
	            	lastST.pop();
	            	//prntSt.pop();
	            }
	            else {
	            	boolean found = false;
	            	int i = 0;
		            while(found == false && i < children.size()) {
		            	if (children.get(i).visited == false) {
	                		st.push(children.get(i));
	                		lastST.push(next);
	                		//unmarkTree(children.get(i));
	                		found = true;
	                	}
	                	i++;
	            	}
	            }
	        //}
	        //else {
	        //	printPath(st);
			//	st.pop();
	        //}
	    }         

    }

	private static void BFS(LinkedList<Node> queue, int last) {

			Node n = queue.peek();
			int next = last;
			List<Node> children;
			//System.out.println(n.a + "/" + n.b);
			if (next == n.a) {
				children = n.childrenB;
				next = n.b;
			}
			else {
				children = n.childrenA;
				next = n.a;
			}

			boolean noChildren = true;
			for (int i = 0; i < children.size(); i++)
            {
            	Node childNode = children.get(i);
                if (inList(queue, childNode.id) == false)
                {
                	noChildren = false;
                	LinkedList<Node> newQueue = copyLL(queue);
                    newQueue.push(childNode);
                    BFS(newQueue, next);
                }
            }
            if (noChildren == true)
            	printPath(queue);

	}

	private static boolean inList(LinkedList<Node> queue, int id) {
		boolean found = false;
		int i = 0;
		while (found == false && i < queue.size()) {
			if (queue.get(i).equals(id))
				found = true;
			i++;
		}
		
		return found;
	}

	private static LinkedList<Node> copyLL(LinkedList<Node> a) {
		LinkedList<Node> b = new LinkedList<Node>();
		for (int i = 0; i < a.size(); i++) {
			b.add(a.get(i));
		}
		return b;
	}

	private static void printPath(Stack<Node> st) {
		int sum = 0;
		for(Node c: st) {
			sum += c.a + c.b;
			System.out.print(c.a + "/" + c.b + " ");
		}
		System.out.print(sum + "\n");
		if (sum > lrgstSum) lrgstSum = sum;
	}

	private static void printPath(LinkedList<Node> st) {
		totPaths++;
		int sum = 0;
		int num = st.size();
		for(Node c: st) {
			sum += c.a + c.b;
			System.out.print(c.a + "/" + c.b + " ");
		}
		System.out.print(sum + "\n");
		if (sum > lrgstSum) lrgstSum = sum;

		if (num == lngstNum) {
			if (sum > lngstSum) lngstSum = sum;
		}
		else if (num > lngstNum) {
			lngstNum = num;
			lngstSum = sum;
		}
	}

	private static boolean visited(List<Node> children) {
		boolean rtn = true;
		for(Node c: children) {
	    	if (c.visited == false)
	    		rtn = false;
	    }
	    return rtn;
	}



	private static void load()
	{
		try {
			FileReader fr = new FileReader("input24.txt");
			BufferedReader br = new BufferedReader(fr);
			int i = 0;
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split("/");
				nodes.add(new Node(i, Integer.parseInt(tempArray[0]), Integer.parseInt(tempArray[1])));
				i++;
			}
			fr.close();
		}
		catch(FileNotFoundException fN) {
			fN.printStackTrace();
		}
		catch(IOException e) {
			System.out.println(e);
		}

		Node currNode;
		for (int i = 0; i < nodes.size(); i++) {
			currNode = nodes.get(i);
			for (int j = 0; j < nodes.size(); j++) {
				if (i != j) {
					if (currNode.a == nodes.get(j).a || currNode.a == nodes.get(j).b)
						currNode.addChildA(nodes.get(j));
					if (currNode.b == nodes.get(j).a || currNode.b == nodes.get(j).b)
						currNode.addChildB(nodes.get(j));
				}
			}
		}
	}

	private static void reset() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).unMark();
		}
	}

public static class Node {

    int id;
    int a;
    int b;
    boolean visited;
    List<Node> childrenA;
    List<Node> childrenB;

    public Node(int id, int a, int b) {
        this.id = id;
        this.a = a;
        this.b = b;
        visited = false;
        this.childrenA = new LinkedList<Node>();
        this.childrenB = new LinkedList<Node>();
    }

    public void addChildA(Node child) {
        this.childrenA.add(child);
	}

	public void addChildB(Node child) {
        this.childrenB.add(child);
	}

	public void mark() {
        this.visited = true;
	}

	public void unMark() {
        this.visited = false;
	}

	public boolean equals(int id) {
		if (this.id == id)
			return true;
		else
			return false;
	}

	public boolean equals(int a, int b) {
		if (this.a == a && this.b == b)
			return true;
		else
			return false;
	}

}

}