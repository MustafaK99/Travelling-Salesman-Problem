package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomRoutes {
	
	private int[] listOfCities;
	private Graph graph;
	private ArrayList<int[]> permutationsList;
	private RandomCostCalculator RCC;
	public RandomRoutes(Graph givenGraph, Route givenRouteList) {
		
		this.listOfCities = givenRouteList.getCityList();
		this.graph = givenGraph; 	
		this.permutationsList = new ArrayList<int[]>();
		this.RCC = new RandomCostCalculator(this.graph);
	
		
	}
	
	public void generatePermutations() {
		int[] currentList = this.listOfCities;
		int index, temp;
		Random random = new Random();
		
		for(int j = 0 ; j < 100; j++) {	
			
			for (int i = currentList.length - 1; i > 0; i--) {
				index = random.nextInt(i + 1);
				temp = currentList[index];
				currentList[index] = currentList[i];
				currentList[i] = temp;
			}
			int[] finalRoute = Arrays.copyOf(currentList, currentList.length + 1);
		    finalRoute[finalRoute.length - 1] = currentList[0];
			if(!(permutationsList.contains(Arrays.copyOf(finalRoute, finalRoute.length)))){
				permutationsList.add(Arrays.copyOf(finalRoute, finalRoute.length));
			}
			
		}		
	}
	
	public void calculateCost() {
		for(int i = 0 ; i < this.permutationsList.size(); i++) {
			RCC.calculateCost(this.permutationsList.get(i));;
			System.out.println(RCC.toString());
			RCC.setNewCost();
		}
			
	}
	
	public ArrayList<int[]> getPermutations(){
		
		return this.permutationsList;
	}

}
