package tomTom;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class projectOps extends BaseClass{
	
	public void createProject() {
		
		String bodyFile="{\r\n" + 
				"    \"name\":\"Ops_findForest\"\r\n" + 
				"}";
		
	//	RestAssured.baseURI="https://api.tomtom.com";

		HashMap<String, String> paramsMap=new HashMap<String, String>();
		
		paramsMap.put("key", apiKey);
		paramsMap.put("adminKey", adminKey);
		Response projectResponse = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.queryParams(paramsMap)
		.body(bodyFile)
		.when()
		.post("geofencing/1/projects/project");
		
		JsonPath jsonPathResponse = projectResponse.jsonPath();
	   projectId = jsonPathResponse.get("id");
	   projectName = jsonPathResponse.get("name");
		
	}
	
	@Test(dependsOnMethods= {"tomTom.createProject"})
	public void listAllProjects() {
		
	//	RestAssured.baseURI="https://api.tomtom.com";
		
		HashMap<String,String>queryMap=new HashMap<String, String>();
		queryMap.put("key", apiKey);
		
		Response jsonPathresponse = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.queryParams(queryMap)
		.when()
		.get("geofencing/1/projects");
		jsonPathresponse.prettyPrint();
		
	}

}
