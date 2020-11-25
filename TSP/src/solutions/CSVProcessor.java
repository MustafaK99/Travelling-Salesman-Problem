package solutions;
import java.util.ArrayList;


public class CSVProcessor {
	
	private Reader reader;
	private ArrayList<String[]> listOfCities;
	
	
	public CSVProcessor() {
		
		this.reader = new Reader("C:\\Users\\rmk79\\Downloads\\cities57_10.csv");
		this.listOfCities = new ArrayList<String[]>();
		
	}
	
	
   
   public void setCityList() {
	   ArrayList<String[]> fileDetails =  reader.getFileDetails();
	   for(int i = 2; i < fileDetails.size(); i++ ) {
		   listOfCities.add(fileDetails.get(i));
		   
	   }
	   
	   
   }
   
   public double[][] getAdjacencyMatrix(){
	   double[][] AdjMatrix = new double[listOfCities.size()][listOfCities.size()];
	   ArrayList<double[]> weightsOfCities = new ArrayList<double[]>();
	   
	   for(int i = 0 ; i < listOfCities.size();i++) {
		   
		   String[] currentCity = listOfCities.get(i);
		   String coordinateX = currentCity[1];
		   String coordinateY = currentCity[2];
		   double positionX = Double.parseDouble(coordinateX);
		   double positionY = Double.parseDouble(coordinateY);
		   double[] coordinatePositions = new double[2];
		   coordinatePositions[0] = positionX;
		   coordinatePositions[1] = positionY;
		   weightsOfCities.add(coordinatePositions);
		   
	   }
	   for(int i = 0 ; i < weightsOfCities.size();i++) {
		   double [] currentCityCoordinates = weightsOfCities.get(i);
		   for(int j = 0; j < weightsOfCities.size();j++) {
			   double[] otherCityCoordinates = weightsOfCities.get(j);
			   double distance = Math.sqrt((otherCityCoordinates[1] - currentCityCoordinates[1]) * (otherCityCoordinates[1] - currentCityCoordinates[1]) + (otherCityCoordinates[0] - currentCityCoordinates[0]) * (otherCityCoordinates[0] - currentCityCoordinates[0]));
			   AdjMatrix[i][j] = distance;
		   }
		   
	   }
	   
	   return AdjMatrix;  
	   
   }
   
   public int[] getListOfCities () {
	   int[] cities = new int[listOfCities.size()];
	   for(int i=0; i < listOfCities.size();i++) {
		   String[] currentCity =  listOfCities.get(i);
		   int currentIntCity  = Integer.parseInt(currentCity[0]);
		   cities[i] = currentIntCity;
		   
		   
	   }
	   
	   
	  return cities;
	   
   }
   


}
