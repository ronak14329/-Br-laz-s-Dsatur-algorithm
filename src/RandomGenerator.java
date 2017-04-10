
import java.util.*;
import java.io.*;
public class RandomGenerator{
	
	public float findDensity(float lowLimit,float upperLimit)
	{
		Random randomGenerator = new Random(); 
		float random_value = randomGenerator.nextFloat(); 
		float density = lowLimit + random_value * ( upperLimit - lowLimit );
		return density; 
	}

	public void createRandomGraph(String fileName,int num_0f_Vertices,float density)
	{
		PrintWriter out=null;
		try {
			out = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	      Random rand = new Random();
	      int number, count=1, another_count=0;
	      
	      
	      
	      
	      for(int iLoop=0; iLoop<num_0f_Vertices;iLoop++)
	      {
	    	  for(int jLoop=0; jLoop<num_0f_Vertices;jLoop++)
	    	  {
	    		  if(iLoop!=jLoop)
	    		  {
	    			  if(rand.nextFloat()<density)
	    			  {
	    				  out.println("e "+iLoop+" "+jLoop);
	    			  }
	    		  }
	    		  
	    	  }
	      }
	      
	      
	      out.close();
	      //System.out.println(density);
		
	}
	
  
}
