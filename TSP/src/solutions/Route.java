package solutions;

import java.util.Arrays;

public class Route {
	
	private int[] Route;
	private int[] listOfCities;
	
	public Route(int[] givenRoute, int[] givenListOfCities) {

		    this.Route = givenRoute;
			this.listOfCities = givenListOfCities;
	}
	
	public int[] getCityList() {
		return this.listOfCities;
	}
	
	public int[] getRoute() {
		int[] finalRoute = Arrays.copyOf(this.Route, this.Route.length + 1);
	    finalRoute[finalRoute.length - 1] = this.Route[0];
		return finalRoute;
	}  
	
		
}
		
		
		



