import java.util.Comparator;


public class vertex implements Comparable{
	
	int vertex_name;
	int adjacency_degree;
	int saturation_degree; 
	LinkedNode headAdjacency; 
	int colorNumber; 
	

	public int getColorNumber() {
		return colorNumber;
	}

	public void setColorNumber(int colorNumber) {
		this.colorNumber = colorNumber;
	}

	public vertex()
	{
		adjacency_degree=0;
		saturation_degree=0;
		colorNumber = -1; 
	}
	
	public vertex(int name)
	{
		vertex_name = name; 
		adjacency_degree=0;
		saturation_degree=0;
		colorNumber = -1; 
	}
	
	public LinkedNode getHeadAdjacency() {
		return headAdjacency;
	}

	public void setHeadAdjacency(LinkedNode headAdjacency) {
		this.headAdjacency = headAdjacency;
	}
	
	public int getVertex_name() {
		return vertex_name;
	}

	public void setVertex_name(int vertex_name) {
		this.vertex_name = vertex_name;
	}

	public int getAdjacency_degree() {
		return adjacency_degree;
	}

	public void setAdjacency_degree(int adjacency_degree) {
		this.adjacency_degree = adjacency_degree;
	}

	public int getSaturation_degree() {
		return saturation_degree;
	}

	public void setSaturation_degree(int saturation_degree) {
		this.saturation_degree = saturation_degree;
	}

	@Override
	public int compareTo(Object o) {
		
		vertex temp1 = (vertex) this;
		vertex temp2 = (vertex) o; 
		
		if(temp1.getSaturation_degree() == temp2.getSaturation_degree()) 
		{
			
			if(temp1.getAdjacency_degree()>temp2.getAdjacency_degree())
			{
				return -1;
			}
			if(temp1.getAdjacency_degree()<temp2.getAdjacency_degree())
			{
				return 1; 
			}
			if(temp1.getAdjacency_degree()==temp2.getAdjacency_degree())
			{
				return 0; 
			}
		}
		else if(temp1.getSaturation_degree()>temp2.getSaturation_degree())  
		{
			return -1;
		}
		else if(temp1.getSaturation_degree()<temp2.getSaturation_degree()) 
		{
			return 1;  
		}
		return 0;
	}
	

}
