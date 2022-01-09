package projekt;


import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonReader {
	
	private Pytania pytania = new Pytania();

	   
	   public JsonReader() {
		      ObjectMapper mapper = new ObjectMapper();
		      
		      try {
		         pytania = mapper.readValue(new File(".\\jsonfile\\pytania.json"), Pytania.class);
		      } catch(Exception e) {
		         e.printStackTrace();
		      }
		   }
	   
	   public Pytania getPytania() {
		   return pytania;
	   }

}