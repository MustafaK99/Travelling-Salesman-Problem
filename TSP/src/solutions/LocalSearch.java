package solutions;

import java.util.ArrayList;
import java.util.Arrays;

public class LocalSearch {
	
	
	private RandomRoutes routeGenerator;
	private long time;
	private Graph graph;
	private ArrayList<int[]> permutationsList;
	private ArrayList<int[]> bestCurrentRoute;
	private ArrayList<int[]> bestNeighbourhood;
	private ArrayList<int[]> potentialBestRoute;
	private double bestRandomCostGenerated;
	private double currentRandomCostGenerated;
	private double finalBestGeneratedCost;
	private ArrayList<int[]> finalBestRouteGenerate;
	public LocalSearch(RandomRoutes routeGenerator,long givenTime, Graph givenGraph ) {
		
		this.routeGenerator = routeGenerator;
		this.graph = givenGraph;
		this.time = givenTime;
		this.permutationsList = new ArrayList<int[]>();
		this.finalBestGeneratedCost = 0.00;
		this.currentRandomCostGenerated = 0.00;
		this.bestCurrentRoute = new ArrayList<int[]>();
		this.potentialBestRoute = new ArrayList<int[]>();
		this.bestNeighbourhood = new ArrayList<int[]>();
		this.finalBestRouteGenerate = new ArrayList<int[]>();
		
	}

	public void randomInitialisation() {
		long t= System.currentTimeMillis();
		long end = t+15000;
		while(System.currentTimeMillis() < end){
		    this.routeGenerator.generatePermutations();
			this.permutationsList = this.routeGenerator.getPermutations();
			for(int i = 0; i < this.permutationsList.size();i++) {
				int[] currentRoute = this.permutationsList.get(i);
				this.potentialBestRoute.add(0, currentRoute);
				for(int j = 0; j < currentRoute.length - 1; j++) {
					int currentNode = currentRoute[j] - 1;
					int nextNode = currentRoute[j+1] - 1;
					double[][] currentGraph = graph.getGraph();
					this.currentRandomCostGenerated += currentGraph[currentNode][nextNode];
				}
				if(this.bestRandomCostGenerated == 0) {
					this.bestRandomCostGenerated = this.currentRandomCostGenerated;	
					this.bestCurrentRoute.add(0, this.potentialBestRoute.get(0));
					currentRandomCostGenerated = 0 ;
				}
				else if(this.bestRandomCostGenerated > this.currentRandomCostGenerated) {			
					this.bestRandomCostGenerated = this.currentRandomCostGenerated;
					this.bestCurrentRoute.add(0, this.potentialBestRoute.get(0));
					currentRandomCostGenerated = 0;
				}
				else {
					
					currentRandomCostGenerated = 0;
				}
				
			}	
			
		}
	}
	
	
	
	public void twoOptNeighbourhood() {
		
		int[] testArray = this.bestCurrentRoute.get(0);
		this.bestNeighbourhood.add(testArray);
		for(int i = 1; i < (testArray.length); i ++) {
			for(int j = i; j < testArray.length - 1; j ++) {
				int[] testArray2 = Arrays.copyOf(testArray, testArray.length);
				  int temp =   testArray2[i];
				  testArray2[i] = testArray2[j];
				  testArray2[j] = temp;
				  if(!(Arrays.equals(testArray, testArray2))) {
					  this.bestNeighbourhood.add(testArray2);
				  }
				  	
			}
		
		}
		
	}
	
	public void stepFunction() {
		   
		double currentCost = 0 ;
		for(int i = 0 ; i < this.bestNeighbourhood.size(); i ++){
			int[] currentRoute = this.bestNeighbourhood.get(i);
		 	for(int j = 0; j < currentRoute.length - 1; j++) {
				int currentNode = currentRoute[j] - 1;
				int nextNode = currentRoute[j+1] - 1;
				double[][] currentGraph = graph.getGraph();
				currentCost += currentGraph[currentNode][nextNode];
			}
		 	
		 	if(this.finalBestGeneratedCost == 0.00) {
		 		this.finalBestGeneratedCost = currentCost;
		 		this.finalBestRouteGenerate.add(0,currentRoute);
		 		currentCost = 0;
		 	}
		 	else if(this.finalBestGeneratedCost > currentCost) {
		 		this.finalBestGeneratedCost = currentCost;
		 		this.finalBestRouteGenerate.add(0,currentRoute);
		 		currentCost = 0;
		 		
		 	}
		 	else {
		 		currentCost = 0;
		 	}
		}
		
			
	}
	
	public void termination() {
				
		long t= System.currentTimeMillis();
		long end = t+this.time;
		while(System.currentTimeMillis() < end) {
			randomInitialisation();
			twoOptNeighbourhood();
			stepFunction();
		}
		
		
	}
	
	public void printResults() {
		
		System.out.println("Final cost from local search " + this.finalBestGeneratedCost);
		System.out.println("For the following route " + Arrays.toString(this.finalBestRouteGenerate.get(0)));
		
	}
       

}


