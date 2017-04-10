import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class TCSS543A {
	public static void main(String arg[])
	{

		//Declaration of variables for use 
		HashMap<Integer,vertex> hmVertices = new HashMap<Integer,vertex>();
		PriorityQueue<vertex> pqVertices = new PriorityQueue<vertex>();
		Scanner input = new Scanner(System.in);
		RandomGenerator randomGenHelper = new RandomGenerator(); 

		int color = 0 ;
		float density_group1;
		float density_group2;
		float density_group3;
		float density_group4; 
		long startProcessTime,endProcessTime; 

		//Get input number of trials 
		//System.out.println("Enter number of trials"); 
		int numTrial = 100; 


		/*System.out.println("Enter density for test group 1 - lower limit"); 
		float lowLimit1 = input.nextFloat(); 

		System.out.println("Enter density for test group 1 - Upper limit");
		float upperLimit1 = input.nextFloat(); 
		density_group1 = randomGenHelper.findDensity(lowLimit1, upperLimit1); 


		System.out.println("Enter density for test group 2 - lower limit"); 
		float lowLimit2 = input.nextFloat(); 

		System.out.println("Enter density for test group 2 - Upper limit");
		float upperLimit2 = input.nextFloat(); 
		density_group2 = randomGenHelper.findDensity(lowLimit2, upperLimit2); 

		System.out.println("Enter density for test group 3 - lower limit"); 
		float lowLimit3 = input.nextFloat(); 

		System.out.println("Enter density for test group 3 - Upper limit");
		float upperLimit3 = input.nextFloat(); 
		density_group3 = randomGenHelper.findDensity(lowLimit3, upperLimit3); 

		System.out.println("Enter density for test group 4 - lower limit"); 
		float lowLimit4 = input.nextFloat(); 

		System.out.println("Enter density for test group 4 - Upper limit");
		float upperLimit4 = input.nextFloat(); 
		density_group4 = randomGenHelper.findDensity(lowLimit2, upperLimit2);*/  


		density_group1 = randomGenHelper.findDensity(0.73F, 0.82F); 
		density_group2 = randomGenHelper.findDensity(0.61F, 0.72F); 
		density_group3 = randomGenHelper.findDensity(0.44F, 0.59F); 
		density_group4 = randomGenHelper.findDensity(0.26F, 0.34F); 

		/*System.out.println("Enter the number of vertices");
		 int numVertices= input.nextInt(); */

		//int numVertices;

		//Get input file name - With edges of graph as input 
		System.out.println("Enter the file location to write random graphs");
		String fileName = input.next();
		//int numVertices=10;
		for(int numVertices=10;numVertices<=100;numVertices=numVertices+10)
		{
			System.out.println("for " + numVertices + " vertices, the four group values");
		for(int densityLoop=0;densityLoop<4;densityLoop++)
		{
			ArrayList<Long> timeTaken = new ArrayList<Long>(); 
			ArrayList<Integer> colorsNeeded = new ArrayList<Integer>(); 	
			float density_value = 0; 
			//System.out.println("for " + numVertices + " the four group values");
			if(densityLoop==0)
			{
				density_value = density_group1; 
			}
			else if(densityLoop==1)
			{
				density_value = density_group2; 
			}
			else if(densityLoop==2)
			{
				density_value = density_group3; 
			}
			else if(densityLoop==3)
			{
				density_value = density_group4; 
			}

			for(int trialLoop=0; trialLoop<numTrial; trialLoop++ )
			{
				String currentFileName = fileName+density_value+"_"+trialLoop+".txt";
				randomGenHelper.createRandomGraph(currentFileName,numVertices,density_value); 

				readInputGraph readGraph = new readInputGraph();
				hmVertices = readGraph.readGraphFromFile(currentFileName); 
				Collection<vertex> lVertices = hmVertices.values();
				Iterator itrVertices = lVertices.iterator(); 

				while(itrVertices.hasNext())
				{
					vertex temp = (vertex) itrVertices.next(); 
					//System.out.println("Vertex: "+temp.getVertex_name()+" Adjacency degree: "+temp.getAdjacency_degree()+"Adj"+temp.getHeadAdjacency());
					/*LinkedNode currentNode = temp.getHeadAdjacency(); 
			while(currentNode!=null)
			{
				System.out.print("-->"+currentNode.getVertex());
				currentNode = currentNode.getNextNode(); 
			}*/
					pqVertices.add(temp);
				}

				/*System.out.println("Priority queue contents: ");
		while(!pqVertices.isEmpty())
		{
			vertex temp = pqVertices.poll(); 
			System.out.println("Vertex name: "+temp.getVertex_name()+" Adj: "+temp.getAdjacency_degree()+" Sat.deg: "+temp.getSaturation_degree());
		}*/

				//graphColoring coloringHelper = new graphColoring();
				//coloringHelper.colorGraph(hmVertices, pqVertices);

				graphColoring_optimized coloringHelper = new graphColoring_optimized();
				startProcessTime = System.nanoTime();
				color = coloringHelper.colorGraph(hmVertices, pqVertices);
				colorsNeeded.add(color);
				endProcessTime = System.nanoTime();
				timeTaken.add(endProcessTime - startProcessTime); 

			}

			System.out.println("Density Value: "+density_value);
			long totalTime = 0; 
			for(int timeLoop=0;timeLoop<numTrial;timeLoop++)
			{
				totalTime = totalTime + timeTaken.get(timeLoop);

			}
			float avgLoop = totalTime / numTrial; 
			System.out.println("Average time taken: "+ (avgLoop/1000)); 

			int totalColors = 0; 
			for(int colorLoop=0;colorLoop<numTrial;colorLoop++)
			{
				totalColors = totalColors + colorsNeeded.get(colorLoop);

			}
			float avgColors = totalColors / numTrial; 
			System.out.println("Average colors used: "+ avgColors);
			//numVertices=numVertices+10;

		}
		}





	}

}