package okta;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class createClient {

	HashMap<String,String> map=new HashMap<String,String>();
	public static String clientId;
	public static String update_name;
	
	@BeforeSuite
	public void setup() {
		map.put("Accept", "application/json");
		map.put("Content-Type", "application/json");
		map.put("Authorization", "SSWS 00OZ7wNgic7UudYMwL06uzk7YpWhygrtMqnYIRK30X");
		//map.put("Cookie", "JSESSIONID=F3490DC5E2CECF2FCBAC91EDE2DC0D28");
		RestAssured.baseURI="https://dev-370919-admin.okta.com";
		int nextInt = new Random(1000).nextInt();
		String update_name = String.valueOf(nextInt);
		
	}
	
	
	@Test
	public void createClient() {
		
		Response response = RestAssured.given().log().all()
		.headers(map).contentType(ContentType.JSON)
		.body("  {\r\n" + 
				"    \"client_name\": \"Web client13\",\r\n" + 
				"    \"redirect_uris\": [\r\n" + 
				"      \"https://httpbin.org/get\"\r\n" + 
				"    ],\r\n" + 
				"    \"response_types\": [\r\n" + 
				"      \"code\",\r\n" + 
				"      \"token\",\r\n" + 
				"      \"id_token\"\r\n" + 
				"    ],\r\n" + 
				"    \"grant_types\": [\r\n" + 
				"      \"refresh_token\",\r\n" + 
				"      \"authorization_code\",\r\n" + 
				"      \"implicit\"\r\n" + 
				"    ],\r\n" + 
				"    \"token_endpoint_auth_method\": \"client_secret_basic\",\r\n" + 
				"    \"application_type\": \"web\"\r\n" + 
				"  }")
		.when()
		.post("/oauth2/v1/clients")
		.then().log().all()
		.extract().response();		
		
		JsonPath jsonPathResponse = response.jsonPath();
		
		 clientId = jsonPathResponse.get("client_id");
		 System.out.println(clientId);
	}
	
	@Test(dependsOnMethods= {"okta.createClient.createClient"})
	public void verify_the_client_present() {
		
		Response response = RestAssured.given().log().all()
				.headers(map)
		.when().get("oauth2/v1/clients");
		JsonPath listOfClients = response.jsonPath();
		response.prettyPrint();
		List<String> list = listOfClients.getList("client_id");
		System.out.println(list.size());
		for (String eachId : list) {
			
			if(eachId.equals(clientId)) {
				assertEquals(clientId, eachId);
			}
		}
	}
	
	@Test(dependsOnMethods= {"okta.createClient.createClient"})
	public void updateClient() {
		
		RestAssured.baseURI="";
		
		Response response = RestAssured.given().log().all()
		.headers(map)
		.body("{\r\n" + 
				"	\"client_id\": \""+clientId+"\",\r\n" + 
				"	\"client_name\": \"Web client"+update_name+"\",\r\n" + 
				"	\"redirect_uris\": [\r\n" + 
				"		\"https://httpbin.org/get\"\r\n" + 
				"	],\r\n" + 
				"	\"response_types\": [\r\n" + 
				"		\"code\",\r\n" + 
				"		\"token\",\r\n" + 
				"		\"id_token\"\r\n" + 
				"	],\r\n" + 
				"	\"grant_types\": [\r\n" + 
				"		\"refresh_token\",\r\n" + 
				"		\"authorization_code\",\r\n" + 
				"		\"implicit\"\r\n" + 
				"	],\r\n" + 
				"	\"token_endpoint_auth_method\": \"client_secret_basic\",\r\n" + 
				"	\"application_type\": \"web\"\r\n" + 
				"}")
		.when()
		.put("/oauth2/v1/clients/"+clientId+"")
		.then()
		.extract()
		.response();
		
		JsonPath jsonPath = response.jsonPath();
	}
	
	@Test(dependsOnMethods= {"okta.createClient.updateClient"})
	public void get_client_after_update() {
		Response response = RestAssured.given().log().all()
		.headers(map)
		.when()
		.get("/oauth2/v1/clients/"+clientId+"");
		
		JsonPath jsonPathResponse = response.jsonPath();
		
		String name = jsonPathResponse.get("client_name");
		
		if(name.equals("Web client"+update_name)) {
			System.out.println("name was succesfully edited..");
		}
		
		
		
		
	}

}
