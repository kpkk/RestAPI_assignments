package com.org.assignments;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GeneratePaypalToken {
	
	public static String access_token;
	
	@Test
	public void geneRateToken() {
		RestAssured.baseURI="https://api.sandbox.paypal.com/v1/oauth2/token";
		RestAssured.authentication=RestAssured.basic("AedSl3CPEOL8y3TpKzXMnyVNBSV2hCcOXRXJJW5yhLDwFLakvYAE8009LlwtRmx6-21Q8dl4FWjMl7Pd", "AedSl3CPEOL8y3TpKzXMnyVNBSV2hCcOXRXJJW5yhLDwFLakvYAE8009LlwtRmx6-21Q8dl4FWjMl7Pd");
		
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("grant_type", "client_credentials");
		
	//	RestAssured.setContentType("application json;charset = UTF-8");
		Response response = RestAssured.given().log().all().
				formParam("grant_type","client_credentials").
				headers("Content-Type","x-www-form-urlencoded")
			//.headers("grant_type","client_credentials").
		//	.contentType(ContentType.URLENC).
		.accept(ContentType.JSON)
		//.contentType("x-www-form-urlencoded")
		.when().
		//body(map).
		post();
		response.prettyPrint();
		
		JsonPath jsonResponse = response.jsonPath();
		
		 access_token = jsonResponse.get("access_token");
		 System.out.println("token is:--  "+access_token);
	}

}
