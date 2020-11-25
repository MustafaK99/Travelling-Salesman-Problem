package main;



import solutions.*;

public class Main {
	 public static void  main(String[] args) {
		   
		int[] cityArray = {1,2,3,4,5,6,7,8,9,10};
		CSVProcessor csv = new CSVProcessor();
	    csv.setCityList();
	    Graph graph = new Graph(csv.getAdjacencyMatrix());
	    Route route = new Route(cityArray, csv.getListOfCities());
	    //CostCalculator cc = new CostCalculator(graph,route);
	   // cc.calculateCost();
	    //System.out.println(cc.toString());
	  
	    
	    RandomRoutes randomRoutes = new RandomRoutes(graph,route);
	    //randomRoutes.generatePermutations();
	    //randomRoutes.calculateCost();
	    
	    //LocalSearch localSearch = new LocalSearch(randomRoutes,5000, graph);
	    //localSearch.randomInitialisation();
	   // localSearch.twoOptNeighbourhood();
	   // localSearch.stepFunction();
	   // localSearch.termination();
	   // localSearch.printResults();
	    
	      evolutionary evol = new evolutionary(randomRoutes, graph);
	      evol.solveTSP();
	    
	    
	    
	}
	  
}
