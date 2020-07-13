package com.org.assignments;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import groovyjarjarantlr4.v4.runtime.RuleDependencies;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class CreateAproductInPaypal extends GeneratePaypalToken {
	
	@DataProvider(name="testdata")
	public String[] testDataFiles() {
		
		String []files=new String[2];
		files[0]="data1.json";
		files[1]="data2.json";
		
		return files;
	}
	
	
    @Test(dataProvider="testdata")
    	//	dependsOnMethods= {"com.org.assignments.GeneratePaypalToken.geneRateToken"})
	public void createProduct(String dataFiles) {
    	
    	File files=new File(dataFiles);
    	RestAssured.baseURI="https://api.sandbox.paypal.com/v1/catalogs/products";
    	RestAssured.authentication=RestAssured.oauth2("A21AAFx89vtqjTtcC8E8-JculsKXs6yKeTX3Heh_NVoyENync4oCfS49JJwLgznIXO8AKJr5Rwj2zQ2k_rFD2WAs83f-NWGEw");
    	
    	HashMap<String,String> params=new HashMap<String, String>();
    	
    	params.put("Content-Type", "application/json");
    	params.put("accept", "application/json");
    	
    	Response response = RestAssured.given().log().all()
    	.headers(params)
    	.body(files)
    	.when()
    	.post();
    	
    	
    	response.prettyPrint();
    	System.out.println(response.getStatusCode());
    	
    	if(response.getStatusCode()==201) {
    		System.out.println("product created succesfully and the right status code was observed");
    	}else {
    		System.err.println("Status code mismated..something went wrong during product creation");
    	}
    	
    	JsonPath jsonResponse = response.jsonPath();
    	String productId = jsonResponse.get("id");
    	System.out.println(productId);
    	
    	
    
		
	}
    
    @AfterMethod
    public void getProducts() {
    	
    	RestAssured.baseURI="https://api.sandbox.paypal.com/v1/catalogs/products";
    	RestAssured.authentication=RestAssured.oauth2("A21AAFx89vtqjTtcC8E8-JculsKXs6yKeTX3Heh_NVoyENync4oCfS49JJwLgznIXO8AKJr5Rwj2zQ2k_rFD2WAs83f-NWGEw");
    	
    	HashMap<String, String> paramsMap=new HashMap<String, String>();
    	paramsMap.put("accept", "application/json");
    	
    	Response response = RestAssured.given().log().all()
    	.accept(ContentType.JSON)
    	.when()
    	.get();
    	//response.prettyPrint();
    	
    	JsonPath jsonResponse = response.jsonPath();
    	List<String> list = jsonResponse.getList("products.name");
    	
    	System.out.println("The product names are--"+list);
    	
    }

}
