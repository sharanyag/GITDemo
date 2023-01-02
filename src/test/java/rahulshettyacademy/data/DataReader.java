package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//This is the utility file to scan json file and create Hashmap automatically out of it 
public class DataReader {

	public List<HashMap<String, String>> getJsondataToMap() throws IOException
	{
		//Fileutil package has a method to read complete file and convert its content to string variable
		
		String StringContent = FileUtils.readFileToString(new File("System.getProperty(user.dir)"+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json","StandardCharset.UTF_8"));
	    // Now convert this String to Hashmap using jackson databind dependency so add it to ur pom using mvn repository
				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String,String>>  data = mapper.readValue(StringContent, new TypeReference<List<HashMap<String,String>>>(){});
	
	return data;
	}
	//Now copying this method to Basetest so we can acess it within testcode without creating object for this datareader class.
}
