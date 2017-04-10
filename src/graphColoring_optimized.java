import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;


public class graphColoring_optimized {
	public int colorGraph(HashMap<Integer,vertex> hmVertices,PriorityQueue<vertex> pqVertices)
	{
		
		
		
		//Set max Color number to -1 initially
		int maxColorNumber = -1; 
		
		
		//pqVertices - Priority queue contains the vertex with maximum adjacency degree at first
		//Pick the first element which contains maximum adjacency degree
		//Color the first element with Color 0 - Color of lowest number 
		
		vertex firstVertex = pqVertices.poll(); 
		firstVertex.setColorNumber(0);
		maxColorNumber=0;
		
		LinkedNode currentNode = firstVertex.getHeadAdjacency(); 
		
		while(currentNode!=null)
		{
			int currentVertex = currentNode.getVertex(); 
			int original_sat_degree = hmVertices.get(currentVertex).getSaturation_degree();
			hmVertices.get(currentVertex).setSaturation_degree(original_sat_degree+1);
			currentNode = currentNode.getNextNode();
		}
		
		vertex currentVertex = null; 
		while((currentVertex = pqVertices.poll())!=null)
		{
			HashMap<Integer,Integer> hmColorUsed = new HashMap<Integer,Integer>(); 
			for(int iLoop=0; iLoop<=maxColorNumber;iLoop++)
			{
				hmColorUsed.put(iLoop, 0);
			}
				
				LinkedNode current = hmVertices.get(currentVertex.getVertex_name()).getHeadAdjacency();  
				while(current!=null)
				{
				
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
					
					
					
				
			}
		
		
		
		
		
		/*
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
		*/
	
		
		return maxColorNumber+1; 
	}
}
