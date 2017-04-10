import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;


public class backupgraphColoring_optimized {
	public void colorGraph(HashMap<Integer,vertex> hmVertices,PriorityQueue<vertex> pqVertices)
	{
		
		Iterator itr = pqVertices.iterator();
		System.out.println("PQ contents at beginning ");
		while(itr.hasNext())
		{
			vertex val = (vertex) itr.next(); 
			System.out.println(val.getVertex_name()+" Adj Deg: "+val.getAdjacency_degree()+" Sat deg: "+ val.getSaturation_degree()+"Head adj"+val.getHeadAdjacency());
		}
		
		//Set max Color number to -1 initially
		int maxColorNumber = -1; 
		int noOperations = 0; 
		
		//pqVertices - Priority queue contains the vertex with maximum adjacency degree at first
		//Pick the first element which contains maximum adjacency degree
		//Color the first element with Color 0 - Color of lowest number 
		vertex firstVertex = pqVertices.poll(); 
		firstVertex.setColorNumber(0);
		maxColorNumber=0;
		
		
		
		LinkedNode currentNode = firstVertex.getHeadAdjacency(); 
		System.out.println("First vertex "+firstVertex.getVertex_name()+"Head adj: "+firstVertex.getHeadAdjacency());
		while(currentNode!=null)
		{
			int currentVertex = currentNode.getVertex(); 
			System.out.println("Setting for the vertex: "+currentVertex);
			int original_sat_degree = hmVertices.get(currentVertex).getSaturation_degree();
			hmVertices.get(currentVertex).setSaturation_degree(original_sat_degree+1);
			currentNode = currentNode.getNextNode();
		}
		
		vertex currentVertex = null; 
		while((currentVertex = pqVertices.poll())!=null)
		{
			itr = pqVertices.iterator();
			System.out.println("PQ contents while checking for vertex "+currentVertex.getVertex_name()+" : ");
			while(itr.hasNext())
			{
				vertex val = (vertex) itr.next(); 
				System.out.println(val.getVertex_name()+" Adj Deg: "+val.getAdjacency_degree()+" Sat deg: "+ val.getSaturation_degree()+"Head adj"+val.getHeadAdjacency());
			}
				
			HashMap<Integer,Integer> hmColorUsed = new HashMap<Integer,Integer>(); 
			for(int iLoop=0; iLoop<=maxColorNumber;iLoop++)
			{
				hmColorUsed.put(iLoop, 0);
			}
			
				
				LinkedNode current = hmVertices.get(currentVertex.getVertex_name()).getHeadAdjacency();  
				while(current!=null)
				{
					System.out.println("-->"+current.getVertex());
					noOperations++; 
					int pointerAtVertex = current.getVertex();
					hmColorUsed.put(hmVertices.get(pointerAtVertex).getColorNumber(), 1); 
					
					current = current.getNextNode();
				}
				
				int color_to_be_used = -1; 
				
				for(int iLoop=0; iLoop<=maxColorNumber;iLoop++)
				{
					if(hmColorUsed.get(iLoop)==0)
					{
						color_to_be_used = iLoop; 
						break; 
						
					}
				}
				
				if(color_to_be_used == -1)
				{
					maxColorNumber++;
					color_to_be_used = maxColorNumber; 
				}
					
					hmVertices.get(currentVertex.getVertex_name()).setColorNumber(color_to_be_used);
					
					LinkedNode currentNode_2 = hmVertices.get(currentVertex.getVertex_name()).getHeadAdjacency(); 
					while(currentNode_2!=null)
					{
						int saturation_degree_original = hmVertices.get(currentNode_2.getVertex()).getSaturation_degree(); 
						hmVertices.get(currentNode_2.getVertex()).setSaturation_degree(saturation_degree_original+1);
						currentNode_2 = currentNode_2.getNextNode();
					}
					
					System.out.println("Current vertex: "+ currentVertex.getVertex_name()+ "Max color: "+maxColorNumber); 
			
					
				
			}
		
		
		
		
		/*System.out.println("Priority queue contents: ");
		while(!pqVertices.isEmpty())
		{
			vertex temp = pqVertices.poll(); 
			System.out.println("Vertex name: "+temp.getVertex_name()+" Adj: "+temp.getAdjacency_degree()+" Sat.deg: "+temp.getSaturation_degree());
		}*/
		
		System.out.println("Number of vertices: "+hmVertices.size());
		System.out.println("Total colors used: "+(maxColorNumber+1)); 
		Collection<vertex> lVertices = hmVertices.values();
		Iterator itrVertices = lVertices.iterator(); 
		
		while(itrVertices.hasNext())
		{
			vertex temp = (vertex) itrVertices.next(); 
			System.out.println("Vertex: "+temp.getVertex_name()+" Adjacency degree: "+temp.getAdjacency_degree()+"Saturation deg: "+temp.getSaturation_degree()+" Color: "+temp.getColorNumber());
			LinkedNode current = temp.getHeadAdjacency(); 
			while(current!=null)
			{
				//System.out.print(current.getVertex()+"-->");
				current = current.getNextNode(); 
			}
		}	
	
		System.out.println("Number of operations: "+noOperations);
	}
}
