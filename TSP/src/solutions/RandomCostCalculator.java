package solutions;

import java.util.Arrays;

public class RandomCostCalculator {
	
	private Graph graph;
	private double cost;
	private int[] currentRoute;
	
	public RandomCostCalculator(Graph givenGraph) {
		
		this.graph = givenGraph;
		cost = 0.00;
		
	}
public void calculateCost(int[] givenRoute) {
		this.currentRoute = givenRoute;
		int[] routeRecieved = givenRoute;
		for(int i = 0; i < routeRecieved.length - 1 ; i++) {
			
			int currentNode = routeRecieved[i] - 1;
			int nextNode = routeRecieved[i+1] - 1;
			double[][] currentGraph = graph.getGraph();
			cost += currentGraph[currentNode][nextNode];
			
		   }
	}
	
	
	public String toString() {
		
		int[] route = this.currentRoute;
		String stringRoute = Arrays.toString(route);
		String costStatement = "The cost for the current route: " + stringRoute + " is " + cost;
		return costStatement;
		
	}
	public double getCost() {
		
		return this.cost;
	}
	
	public void setNewCost() {
		this.cost = 0.00;
	}
	
	
	
	
	
	
	

}
