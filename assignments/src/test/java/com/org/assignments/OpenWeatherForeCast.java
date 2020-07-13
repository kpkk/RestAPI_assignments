package com.org.assignments;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class OpenWeatherForeCast {
	
	@Test
	public void rainVolumeMeasurer() {
		
		RestAssured.baseURI="https://api.openweathermap.org/data/2.5/forecast";
	//	RestAssured.baseURI="api.openweathermap.org/data/2.5/forecast?q=mumbai, india&appid=6fa11fe8f3d00ec3e3169fddffa7adee";
		//RestAssured.authentication=RestAssured.oauth2("6fa11fe8f3d00ec3e3169fddffa7adee");
		
		HashMap<String, String> paramsMap=new HashMap<String, String>();
		
		paramsMap.put("q", "mumbai, india");
		paramsMap.put("appid", "6fa11fe8f3d00ec3e3169fddffa7adee");
		
		Response response = RestAssured.given().log().all()
				.queryParams(paramsMap)
		.header(new Header("Content-Type","application/json"))
		.contentType(ContentType.JSON)
		.when().get();
		
		/*Response response = RestAssured.given().log().all().queryParams(paramsMap)
		.when().get();*/
		
		int statusCode = response.getStatusCode();
		
		if(statusCode==200) {
			System.out.println("able to hit the url and get the response");
		}
		else {
			System.err.println("something went wrong");
		}
		
		
		
	}
	

}
