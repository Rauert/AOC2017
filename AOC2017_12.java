import java.lang.*;
import java.util.*;
import java.io.*;

public class AOC2017_12
{
	public static LinkedList<Node> nodes;
	public static int sum;
	//public static LinkedList<Node> connectedNodes;

	public static void main ( String args[] )
	{
		Node currNode;
		sum = 0;
		int groups = 0;
		nodes = new LinkedList<Node>();
		//connectedNodes = new LinkedList<Node>();

		load();

//Part 1

		BFS(getNode("0"));
		System.out.println("Part 1 " + sum);

//Part 2

		//Reset
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).unMark();
		}

		for (int i = 0; i < nodes.size(); i++) {
			currNode = nodes.get(i);
			if (currNode.visited == false) {
				groups++;
				BFS(currNode);
			}
		}
		System.out.println("Part 2 " + groups);
	}

	private static void BFS(Node currNode) {
		LinkedList<Node> queue = new LinkedList<Node>();
		currNode.mark();
		sum++;
		queue.add(currNode);

		while (queue.size() != 0)
        {
           currNode = queue.poll();

           List<Node> c = currNode.children;
            for (int i = 0; i < c.size(); i++)
            {
            	Node childNode = c.get(i);
                if (childNode.visited == false)
                {
                    childNode.mark();
                    sum++;
                    queue.add(childNode);
                }
            }
        }
	}

	private static void load()
	{
		Node currNode;
		Node childNode;
		try {
			FileReader fr = new FileReader("input12.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String tempArray[] = line.split(", |\\s+");
				if (nodeExists(tempArray[0]) == false) {
					currNode = new Node(tempArray[0]);
					nodes.add(currNode);
				}
				else
					currNode = getNode(tempArray[0]);

				if (tempArray.length > 2) {
					for (int i = 2; i < tempArray.length; i++) {
						if (nodeExists(tempArray[i]) == false) {
							childNode = new Node(tempArray[i]);
							nodes.add(childNode);
						}
						else
							childNode = getNode(tempArray[i]);
						currNode.addChild(childNode);
					}
				}
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

	private static Node getNode(String s)
	{
		int i = 0;
		boolean found = false;
		Node rtnNode = null;
		while (i < nodes.size() && found == false) {
			if (nodes.get(i).equals(s)) {
				rtnNode = nodes.get(i);
				found = true;
			}
			i++;
		}

		return rtnNode;
	}

	private static boolean nodeExists(String s)
	{
		int i = 0;
		boolean found = false;
		while (i < nodes.size() && found == false) {
			if (nodes.get(i).equals(s)) {
				found = true;
			}
			i++;
		}
		return found;
	}

public static class Node {

    String name;
    boolean visited;
    List<Node> children;

    public Node(String name) {
        this.name = name;
        visited = false;
        this.children = new LinkedList<Node>();
    }

    public void addChild(Node child) {
        this.children.add(child);
	}

	public void mark() {
        this.visited = true;
	}

	public void unMark() {
        this.visited = false;
	}

	public boolean equals(String s) {
		if (this.name.equals(s))
			return true;
		else
			return false;
	}

}

}