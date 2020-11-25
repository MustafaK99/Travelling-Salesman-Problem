package solutions;

import java.util.Arrays;

public class CostCalculator {
	
	private double cost;
	private Route route;
	private Graph graph;
	public CostCalculator(Graph givenGraph, Route givenRoute) {
		
		this.cost = 0.00;
		this.route = givenRoute;
		this.graph = givenGraph;
		
	}
	
	public void calculateCost() {
		
		int[] routeRecieved = route.getRoute();
		for(int i = 0; i < routeRecieved.length - 1 ; i++) {
			
			int currentNode = routeRecieved[i] - 1;
			int nextNode = routeRecieved[i+1] - 1;
			double[][] currentGraph = graph.getGraph();
			cost += currentGraph[currentNode][nextNode];
				
		   }
		
		
		
	}
	
	
	public String toString() {
		
		int[] route = this.route.getRoute();
		String stringRoute = Arrays.toString(route);
		String costStatement = "The cost for the current route: " + stringRoute + " is " + cost;
		return costStatement;
		
	}
	public double getCost() {
		
		return this.cost;
	}

}
