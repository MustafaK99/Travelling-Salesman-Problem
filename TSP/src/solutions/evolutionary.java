package solutions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class evolutionary {
	
	public static final int POPULATION_SIZE = 100;
	public static final int SELECTION_SIZE = 50;
	public static final int GENERATIONS = 50;
	public static final int MUTATION_PROBABILITY =  30;
	public static final int RECOMBINATION_PROBABILITY = 100;
	private double[][] graph;
	private RandomRoutes routeGenerator;
	private ArrayList<int[]> Population;
	private ArrayList<int[]> Parents;
	private double bestCost;
	private ArrayList<int[]> bestRoute;
	public evolutionary(RandomRoutes givenRouteGenerator, Graph givenGraph) {
		
		this.graph = givenGraph.getGraph();
		this.routeGenerator = givenRouteGenerator;
		this.Population = new ArrayList<int[]>();
		this.Parents = new ArrayList<int[]>();
		this.bestCost = 0.0;
		this.bestRoute = new ArrayList<int[]>();
		
	}
	
	public void solveTSP() {
		GeneratePopulation();
		bestCostForPopulation(this.Population);
		
		System.out.println("The best route for the initial population is: " + Arrays.toString(this.Population.get(0)));
		System.out.println("The cost is "+ this.bestCost);
		
		for(int i = 0; i < GENERATIONS; i++) {
			generateNextGen();
			bestCostForPopulation(this.Population);
		}
		
		System.out.println("The best route for the final population is: " + Arrays.toString(this.Population.get(0)));
		System.out.println("The cost is "+ this.bestCost);
		
	}
	
	
	public void GeneratePopulation() {
		this.routeGenerator.generatePermutations();
		ArrayList<int[]> givenPopulation = this.routeGenerator.getPermutations();
		 Population.addAll(0,givenPopulation);
		 
		
	}
	
	public void bestCostForPopulation(ArrayList<int[]> givenPopulation) {
		 this.bestCost = calculateCost(givenPopulation.get(0));
		 this.bestRoute.add(givenPopulation.get(0));
		 for(int i = 1; i < givenPopulation.size(); i++) {
			 double currentCost = 0;
			 currentCost = calculateCost(givenPopulation.get(i));
			 if(currentCost < this.bestCost) {
				 this.bestCost = currentCost;
				 this.bestRoute.add(0, givenPopulation.get(i));
			 }		 
			 
		 }
	}
	
	
	
	public double calculateCost(int[] currentRoute) {
		
		int[] routeRecieved = currentRoute;
		double cost = 0.0;
		for(int i = 0; i < routeRecieved.length - 1 ; i++) {
			
			int currentNode = routeRecieved[i] - 1;
			int nextNode = routeRecieved[i+1] - 1;
			cost += this.graph[currentNode][nextNode];
				
		   }
		return cost;	
		
	}
	public void generateNextGen() {
		parentSelection();
		ArrayList<int[]> Children = produceChildren(this.Parents);
		this.Population.addAll(0,Children);
	}
	
	
	
	public void parentSelection() {
		Random random = new Random();
		for(int i = 0; i < SELECTION_SIZE; i++) {
			int[] BestParent;
			double bestCost = 0.0;
			ArrayList<int[]> potentialParent = new ArrayList<int[]>();
			for(int j = 0; j < 10 ; j++) {
				potentialParent.add(this.Population.get(random.nextInt(100)));	
			}
			BestParent = potentialParent.get(0);
			bestCost  = calculateCost(potentialParent.get(0));
			for(int k = 1; k < potentialParent.size(); k ++) {
				if(calculateCost(potentialParent.get(k)) < bestCost){
					bestCost = calculateCost(potentialParent.get(k));
					BestParent = potentialParent.get(k);
				}
				
			}
			this.Parents.add(BestParent);
			
		}
		
		
	}
	
	public ArrayList<int[]> produceChildren(ArrayList<int[]> givenParentList) {
		ArrayList<int[]> currentParents = this.Parents;
		ArrayList<int[]> Children = new ArrayList<int[]>();
		for(int i = 0; i < POPULATION_SIZE;i++) {
			Collections.shuffle(currentParents);
			List<int[]> selectedParents = currentParents.subList(0, 2);
			int[] currentChild = orderOneCrossOver(selectedParents.get(0),selectedParents.get(1));
			Random rn = new Random();
			if(rn.nextInt(100) < MUTATION_PROBABILITY) {
				mutate(currentChild);
			} 
			Children.add(currentChild);
		}	
		return Children;
		
	}
	
	public int[] orderOneCrossOver(int[] parent1, int[] parent2) {
		int [] child = new int[parent1.length];
		child[0] = parent1[0];
		child[child.length-1] = parent1[parent1.length-1];
		int start = randomNumber(1,parent1.length-2);
		int end = randomNumber(1,parent1.length-2);
		
		while(start >= end) {start = randomNumber(1,parent1.length-2); end = randomNumber(1,parent1.length-2);}
		
		for(int i = start; i <= end; i++){
			child[i] = parent1[i];
		}
	
		
		for(int i = 0; i < parent2.length; i++) {
			int currentElement = parent2[i];
			boolean flag = false;
			for(int j = 0; j < child.length; j++) {
				if(currentElement == child[j]) {
					flag = true;
				}
				
			}
			if(flag == false) {
				for(int z = 0; z < child.length; z++) {
					if(child[z]== 0) {
						child[z] = currentElement;
						break;
					}
					
				}
			}
			
		}
		
		
		return child;
		
		
		
	}
	
public int[] mutate(int[] givenChild) {
	ArrayList<int[]> givenNeighbourhood = twoOptNeighbourhood(givenChild);
	Random random = new Random();
	return givenNeighbourhood.get(random.nextInt(givenNeighbourhood.size()));
	
}	
	
public ArrayList<int[]> twoOptNeighbourhood(int[] givenChild) {
		ArrayList<int[]> bestNeighbourhood = new ArrayList<int[]>();
		int[] currentRoute = givenChild;
		bestNeighbourhood.add(currentRoute);
		for(int i = 1; i < (currentRoute.length); i ++) {
			for(int j = i; j < currentRoute.length - 1; j ++) {
				int[] routeDuplicate = Arrays.copyOf(currentRoute, currentRoute.length);
				  int temp =   routeDuplicate[i];
				  routeDuplicate[i] = routeDuplicate[j];
				  routeDuplicate[j] = temp;
				  if(!(Arrays.equals(currentRoute, routeDuplicate))) {
					  bestNeighbourhood.add(routeDuplicate);
				  }
				  	
			}
		
		}
		
		return bestNeighbourhood;
	}
	
	
	
	
	
	
	public static int randomNumber(int min , int max) {
		Random r = new Random();
		double d = min + r.nextDouble() * (max - min);
		return (int)d;
	}
	
	
}
