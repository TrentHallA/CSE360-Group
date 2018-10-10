import java.util.*;

public class PseudoCodeNonsense
{
	
	ArrayList<String> arrayOfPaths;// = new String[20];
	ArrayList<PERT_Node> nodeList;
	ArrayList<PERT_Node> finalNodeList;
	int i;
	int numOfNodes;
	String firstLoopString;
	String secondLoopString;
	
	// constructor
	public PseudoCodeNonsense(int numberOfNodes, ArrayList<PERT_Node> nodeList)
	{
		this.finalNodeList = new ArrayList<PERT_Node>();
		this.arrayOfPaths = new ArrayList<String>();
		this.nodeList = nodeList;
		this.numOfNodes = numberOfNodes;
		i = 0;
		for(int j = 0; j < nodeList.size(); j++)
		{
			System.out.println("hi " + nodeList.get(j).name);
		}
		System.out.println("ok, at least the object was instantiated");
	}
//2^n is the MAX number of paths given n nodes, I think... I just put 20 here to test

	//int i = 0;

	public void findFinalNodes()
	{
		if(this.nodeList.get(0) == null)// || this.arrayOfPaths.get(0) == null)
		{
			System.out.println("give me a node list please");
			return;
		}
		
		// need to decide which nodes are final nodes
		for (int j = 0; j < numOfNodes; j++)
		{
			i = 0; // reset the flag for every loop; the default assumption is that it is a final node
			firstLoopString = nodeList.get(j).name;//[j].name;
			for (int k = 0; k < numOfNodes; k++)
			{
				if (nodeList.get(k).dependencies[0] != null && !nodeList.get(k).dependencies[0].equals(""))
				{
					for (int s = 0; s < nodeList.get(k).dependencies.length; s++)
					{
						if (firstLoopString.equals(nodeList.get(k).dependencies[s]))
						{
							i = 1; 	// set flag making note that the node is listed as 
									// a dependency and so it's not a final node
						}
					}
				}
			}
			// If the node isn't listed as a dependency anywhere
			// add it to the list of "final nodes"
			if(i == 0)
			{
				finalNodeList.add(nodeList.get(j));
			}
		}
		if(finalNodeList.get(0) == null)
		{
			System.out.println("yeah it's fucked");
		}
		System.out.flush();
		else if(finalNodeList.get(0).name.equals(""))
		{
			System.out.println("yeah it's an empty string");
		}
		System.out.flush();
		else
		{
			System.out.println("\n a" + finalNodeList.get(0).name + "\n");
		}
		System.out.flush();
		System.out.println("a " + finalNodeList.get(0);
		System.out.println("All final nodes found");
		return;
	}	
	
	public void traceAllPaths()
	{
		// checks to make sure we're not processing an empty list
		if(finalNodeList.get(0) == null || nodeList.get(0) == null)
		{
			return;
		}
		for(int j = 0; j < finalNodeList.size(); j++)
		{
			traceAllPathsHelper(finalNodeList.get(j), finalNodeList.get(j).name, nodeList);
			// checks for edge case where a node is independent (starting and final node)
			/*
			if(finalNodeList.get(j).dependencies == null)
			{
				arrayOfPaths.add(finalNodeList.get(j).name);
			}
			else
			*/
			System.out.println("test");	
		}
		System.out.println("trace all paths completed");
		return;
	}
	
	public void traceAllPathsHelper(PERT_Node Node, String pathSoFar, ArrayList<PERT_Node> allNodes)
	{
		// when recursion bottoms out, add the completed path to the arrayOfPaths and return
		if(Node.dependencies[0] == null)
		{
			arrayOfPaths.add(pathSoFar);
			System.out.println("one done");
			return;
		}
		// continue depth-first recursion 
		else
		{
			for(int j = 0; j < Node.dependencies.length; j++)
			{
				for(int k = 0; k < allNodes.size(); k++)
				{
					if(allNodes.get(k).name.equals(Node.dependencies[j]))
					{
						pathSoFar = pathSoFar + " -> " + allNodes.get(k).name;
						traceAllPathsHelper(allNodes.get(k), pathSoFar, allNodes);
					}
				}
			}
		}
		System.out.println("finished tracing all paths");
		return;
	}	
/*		Pseudo-code for tracing all possible paths	*/

// when searching for a node which is used as a dependency by another node, I'll need to iterate through all of the nodes and just 
// check which one has the name attribute which matches the one referenced by the dependent node
//
//
//for all final nodes
//	String path = "finalNode.name";
//	Backtrack(finalNode, path);
//
//
//Backtrack(PERT_Node node, String path){
//	if node.dependencies == null
//		arrayOfpaths[i] = path;
//		i++;
//		return;
//	for dependencies in node
//		String Backpath = String.copy(path);
//		Backpath.prepend(dependency.name);
//		Backtrack(dependency, Backpath);
//	return;
}