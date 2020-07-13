package tomTom;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FenceOps extends BaseClass{
	
	public File getBodyData() {
		return new File("fence.json");
	}
	
	
	public void createFence() {
	//	RestAssured.baseURI="https://api.tomtom.com";
		
		HashMap<String,String> map=new HashMap<String,String>();
		
		map.put("key", apiKey);
		map.put("adminKey", adminKey);
		
		Response jsonPathResponse = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(getBodyData())
		.when()
		.post("geofencing/1/projects/"+projectId+"/fence");
		
		jsonPathResponse.prettyPrint();
	}
	
	@Test(dependsOnMethods= {"tomTom.createFence"})
	public void getFenceDetails() {
		
	//	RestAssured.baseURI="https://api.tomtom.com";
		
		Response response = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.queryParams("key",apiKey)
		.when()
		.get("geofencing/1/projects/"+projectId+"/fences");
		
		response.prettyPrint();
	}

}
