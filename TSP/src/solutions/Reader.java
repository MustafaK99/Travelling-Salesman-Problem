package solutions;

import java.util.ArrayList;
//import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Reader {


	private ArrayList<String[]> fileDetails;
	
	public Reader(String givenFilename) {
    	
   	 this.fileDetails = new ArrayList<String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader(givenFilename)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
           	 String [] commaSeperated = sCurrentLine.split(",");
           
           	fileDetails.add(commaSeperated);
            }
            
 

        } catch (IOException e) {
       	 
     
        }
		

	}
	
	public ArrayList<String[]> getFileDetails (){
		
		
		return this.fileDetails;
	}
		    

		
}
	
	
	

