import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;


public class graphColoring {
	public void colorGraph(HashMap<Integer,vertex> hmVertices,PriorityQueue<vertex> pqVertices)
	{
		int maxColorNumber = -1; 
		
		
		vertex firstVertex = pqVertices.poll(); 
		firstVertex.setColorNumber(0);
		maxColorNumber=0;
		
		LinkedNode currentNode = firstVertex.getHeadAdjacency(); 
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
			for(int iLoop=0;iLoop<hmVertices.size();iLoop++)
			{
				//iLoop = Color number
				int isColorUsed = 0; 
				
				LinkedNode current = hmVertices.get(currentVertex.getVertex_name()).getHeadAdjacency();  
				while(current!=null)
				{
					int pointerAtVertex = current.getVertex();
					if(hmVertices.get(pointerAtVertex).getColorNumber() == iLoop)
					{
						isColorUsed = 1; 
					}
					current = current.getNextNode();
				}
				
				if(isColorUsed == 0)
				{
					hmVertices.get(currentVertex.getVertex_name()).setColorNumber(iLoop);
					if(iLoop>maxColorNumber)
					{
						maxColorNumber=iLoop;
					}
					break;
				}
				else if(isColorUsed == 1)
				{
					
				}
			
				
			}
		}
		
		
		
		/*System.out.println("Priority queue contents: ");
		while(!pqVertices.isEmpty())
		{
			vertex temp = pqVertices.poll(); 
			System.out.println("Vertex name: "+temp.getVertex_name()+" Adj: "+temp.getAdjacency_degree()+" Sat.deg: "+temp.getSaturation_degree());
		}*/
		
		System.out.println("Number of vertices: "+hmVertices.size());
		System.out.println("Total colors used: "+maxColorNumber+1); 
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
		
	}
}
