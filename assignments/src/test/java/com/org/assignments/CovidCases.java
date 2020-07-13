package com.org.assignments;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CovidCases {
	
	@Test
	public void getCovidCasesCount() {
		RestAssured.baseURI="https://covid-19.dataflowkit.com/v1";
		Response response = RestAssured.given().log().all()
				.relaxedHTTPSValidation("SSL")
				.config(RestAssuredConfig.config().sslConfig(new SSLConfig().allowAllHostnames()))
		.accept(ContentType.JSON)
		.get();
		JsonPath jsonResponse = response.jsonPath();
		
		jsonResponse.prettyPrint();
	}

}
