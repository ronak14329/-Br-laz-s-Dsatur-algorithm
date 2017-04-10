import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class readInputGraph {
	
	public HashMap<Integer,vertex> readGraphFromFile(String fileName)
	{
		int noEdges = 0; 
		HashMap<Integer,vertex> hmVertices = new HashMap<Integer,vertex>();
		
		FileInputStream fstream=null;
		try {
			fstream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DataInputStream inGraph = new DataInputStream(fstream);
		BufferedReader brGraph = new BufferedReader(new InputStreamReader(inGraph));
		
		String strLine = "";
		
		try {
			while((strLine = brGraph.readLine())!=null)
			{
				String component[] = strLine.split(" ");
				
				if(component[0].toLowerCase().equals("e"))
				{
					//Found an edge
					noEdges++; 
					
					Integer vertex1 = Integer.parseInt(component[1]); 
					Integer vertex2 = Integer.parseInt(component[2]);
					
					//System.out.println("Found edge between "+vertex1+" and "+vertex2);
					
					//Check if component[1] is already created vertex 
					int vertex1IsAvailable = 0; 
					
					if(hmVertices.containsKey(vertex1))
					{
						vertex1IsAvailable=1;
					}
					
					//Check if component[2] is already created vertex
					int vertex2IsAvailable = 0;
					
					if(hmVertices.containsKey(vertex2))
					{
						vertex2IsAvailable = 1; 
					}
					
					if(vertex1IsAvailable == 0)
					{
						//Vertex 1 is not available
						vertex newNode = new vertex(vertex1);
						hmVertices.put(vertex1, newNode);						
					}

					if(vertex2IsAvailable == 0)
					{
						//Vertex 2 is not available
						vertex newNode = new vertex(vertex2);
						hmVertices.put(vertex2, newNode);						
					}

					
					
					
					int isAlreadyAddedVertex1 = 0; 
					int isAlreadyAddedVertex2 = 0; 
					
					//Add in adjacency list for vertex1 
					LinkedNode head = hmVertices.get(vertex1).getHeadAdjacency(); 
					LinkedNode currentNode = head; 
					if(currentNode!=null){
					while(currentNode.getNextNode()!=null)
					{
						if(currentNode.getVertex() == vertex2)
						{
							isAlreadyAddedVertex1=1;
						}
						currentNode = currentNode.getNextNode(); 
					}
					if(currentNode.getVertex() == vertex2)
					{
						isAlreadyAddedVertex1=1;
					}
					}
					
					
					if(isAlreadyAddedVertex1==0){
						hmVertices.get(vertex1).setAdjacency_degree(hmVertices.get(vertex1).getAdjacency_degree()+1);
					LinkedNode newNode = new LinkedNode(); 
					newNode.setVertex(vertex2);
					if( currentNode != null )
					{
						currentNode.setNextNode(newNode);
					}
					else 
					{
						hmVertices.get(vertex1).setHeadAdjacency(newNode);
					}
					}
					
					//Add in adjacency list for vertex2 
					head = hmVertices.get(vertex2).getHeadAdjacency(); 
					currentNode = head; 
					if(currentNode!=null){
					while(currentNode.getNextNode()!=null)
					{
						if(currentNode.getVertex() == vertex1)
						{
							isAlreadyAddedVertex2=1;
						}
						currentNode = currentNode.getNextNode(); 
					}
					if(currentNode.getVertex() == vertex1)
					{
						isAlreadyAddedVertex2=1;
					}
					}
					
					
					if(isAlreadyAddedVertex2==0){
						hmVertices.get(vertex2).setAdjacency_degree(hmVertices.get(vertex2).getAdjacency_degree()+1);
					LinkedNode newNode2 = new LinkedNode(); 
					newNode2.setVertex(vertex1);
					if( currentNode != null )
					{
						currentNode.setNextNode(newNode2);
					}
					else
					{
						hmVertices.get(vertex2).setHeadAdjacency(newNode2);
					}
					}
					
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Number of edges: "+noEdges);
		return hmVertices; 
		
	}

}
