package newsLetter2Go;

import PojoClasses.*;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.jknack.handlebars.helper.EachHelper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;




public class SetToken {

	HashMap<String, String> headerMap=new HashMap<String,String>();
	public static String listId;
	public static String groupId;
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI="https://api.newsletter2go.com";
		headerMap.put("Authorization", "Basic ZGdqYmV3Z25fSFZwOVFqMHpfOTF0N2phY2VhXzdPdkRmYm5fczJBUVJOOnBxamE5b2pi");
		headerMap.put("Content-Type","application/json");
	}
	
	@Test
	public void getAccessToken() {
		
		
		NewsLetterAccessTokenGeneration token=new  NewsLetterAccessTokenGeneration();
		
		token.setUsername("kadarla.pradeep4@gmail.com");
		token.setPassword("Xerox@123");
		token.setGrant_type("https://nl2go.com/jwt");
		RestAssured.baseURI="https://api.newsletter2go.com/oauth/v2/token";
		
		
		Response response = RestAssured.given().log().all()
		.headers(headerMap)
		.body(token)
		.when()
		.post()
		.then()
		.extract().response();
		response.prettyPrint();
	}
	
	@Test
	public void getRefreshToken() {
		
		RefreshTokenFields refreshToken=new RefreshTokenFields();
		refreshToken.setRefresh_token("__eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfXy5haWQiOjQyNzg3OSwiX18uZW52aXJvbm1lbnQiOiJwcm9kdWN0aW9uIiwiX18uZXhwaXJlc0F0IjoxNTk1OTEyMTgwLCJfXy5zY29wZSI6IiIsIl9fLmNpZCI6Ijg3MDY4X0hWcDlRajB6XzkxdDdqYWNlYV83T3ZEZmJuX3MyQVFSTiIsIl9fLnJkMSI6ODU1NDYsIl9fLnJkMiI6NTY1MDQsIl9fLnJlZnJlc2giOnRydWV9.Q7NZzeHyQ5NzuKMr1YUdOZmSw7Zd2zn6uIrgI_ZjDZk");
		refreshToken.setGrant_type("https://nl2go.com/jwt");
		RestAssured.baseURI="https://api.newsletter2go.com/oauth/v2/token";
		headerMap.put("Authorization", "Basic ZGdqYmV3Z25fSFZwOVFqMHpfOTF0N2phY2VhXzdPdkRmYm5fczJBUVJOOnBxamE5b2pi");
		headerMap.put("Content-Type","application/json");
		
		Response response = RestAssured.given().log().all()
		.headers(headerMap)
		.body(refreshToken)
		.when()
		.post()
		.then()
		.extract().response();
		
		response.prettyPrint();
		
		
	}
	
	@Test//(dependsOnMethods= {"newsLetter2Go.SetToken.SetToken"})
	public void createList() {
		CreateListFileds bodyFields=new CreateListFileds();
		bodyFields.setHas_clicktracking(true);
		bodyFields.setHas_conversiontracking(true);
		bodyFields.setHas_opentracking(true);
		bodyFields.setHeader_from_email("mail@example.org");
		bodyFields.setHeader_from_name("myName");
		bodyFields.setHeader_reply_email("reply@example.org");
		bodyFields.setHeader_reply_name("reply name");
		bodyFields.setImprint("http://example.org/imprint");
		bodyFields.setLandingpage("http://example.org/unsubscribe-landingpage");
		bodyFields.setName("Pradeeps List");
		bodyFields.setTracking_url(null);
		bodyFields.setUse_ecg_list(false);
		bodyFields.setUses_econda(false);
		bodyFields.setUses_googleanalytics(true);
		
		RestAssured.baseURI="https://api.newsletter2go.com/lists";
		Response response = RestAssured.given().log().all()
		.headers("Content-Type","application/json")
		.headers("Authorization","Bearer __eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfXy5haWQiOjQyNzg3OSwiX18uZW52aXJvbm1lbnQiOiJwcm9kdWN0aW9uIiwiX18uZXhwaXJlc0F0IjoxNTkzMzI3MzgwLCJfXy5zY29wZSI6IiIsIl9fLmNpZCI6Ijg3MDY4X0hWcDlRajB6XzkxdDdqYWNlYV83T3ZEZmJuX3MyQVFSTiIsIl9fLnJkMSI6ODk0OTIsIl9fLnJkMiI6MTI1NTJ9.7auOqXOyQOuZuVbuIW4uSWjEUlUui6Ih1qY0PcRRhTQ")
		.body(bodyFields)
		.when()
		.post()
		.then()
		.extract().response();
		
		JsonPath jsonPath = response.jsonPath();
		 listId = jsonPath.get("value[0].id");
	}
	
	@Test(dependsOnMethods= {"newsLetter2Go.SetToken.createList"})
	public void createAsegment() {
		RestAssured.baseURI="https://api.newsletter2go.com/groups";
		
		Response response = RestAssured.given().log().all()
		.headers("Content-Type","application/json")
		.headers("Authorization","Bearer __eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfXy5haWQiOjQyNzg3OSwiX18uZW52aXJvbm1lbnQiOiJwcm9kdWN0aW9uIiwiX18uZXhwaXJlc0F0IjoxNTkzMzI3MzgwLCJfXy5zY29wZSI6IiIsIl9fLmNpZCI6Ijg3MDY4X0hWcDlRajB6XzkxdDdqYWNlYV83T3ZEZmJuX3MyQVFSTiIsIl9fLnJkMSI6ODk0OTIsIl9fLnJkMiI6MTI1NTJ9.7auOqXOyQOuZuVbuIW4uSWjEUlUui6Ih1qY0PcRRhTQ")
		.body("{\r\n" + 
				"	\"list_id\": \""+listId+"\"\",\r\n" + 
				"	\"name\": \"My Segement\",\r\n" + 
				"	\"is_dynamic\": false\r\n" + 
				"}")
		.when()
		.post()
		.then()
		.extract().response();
		JsonPath jsonResponse = response.jsonPath();
		 groupId = jsonResponse.get("value[0].id");
	
	}
	
	public void updateSegment() {
       RestAssured.baseURI="https://api.newsletter2go.com/groups/"+groupId+"";
		
		Response response = RestAssured.given().log().all()
		.headers("Content-Type","application/json")
		.headers("Authorization","Bearer __eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfXy5haWQiOjQyNzg3OSwiX18uZW52aXJvbm1lbnQiOiJwcm9kdWN0aW9uIiwiX18uZXhwaXJlc0F0IjoxNTkzMzI3MzgwLCJfXy5zY29wZSI6IiIsIl9fLmNpZCI6Ijg3MDY4X0hWcDlRajB6XzkxdDdqYWNlYV83T3ZEZmJuX3MyQVFSTiIsIl9fLnJkMSI6ODk0OTIsIl9fLnJkMiI6MTI1NTJ9.7auOqXOyQOuZuVbuIW4uSWjEUlUui6Ih1qY0PcRRhTQ")
		.body("{\r\n" + 
				"	\"name\": \"My New Segment Name\"\r\n" + 
				"}")
		.when()
		.patch()
		.then()
		.extract().response();
		JsonPath jsonResponse = response.jsonPath();
	}
	
	public void getAllLists() {
		
		RestAssured.baseURI="https://api.newsletter2go.com/lists";
		Response response = RestAssured.given().log().all()
		.headers("Authorization","Bearer __eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfXy5haWQiOjQyNzg3OSwiX18uZW52aXJvbm1lbnQiOiJwcm9kdWN0aW9uIiwiX18uZXhwaXJlc0F0IjoxNTkzMzI3MzgwLCJfXy5zY29wZSI6IiIsIl9fLmNpZCI6Ijg3MDY4X0hWcDlRajB6XzkxdDdqYWNlYV83T3ZEZmJuX3MyQVFSTiIsIl9fLnJkMSI6ODk0OTIsIl9fLnJkMiI6MTI1NTJ9.7auOqXOyQOuZuVbuIW4uSWjEUlUui6Ih1qY0PcRRhTQ")
		.when()
		.get()
		.then()
		.extract()
		.response();
		JsonPath jsonPathResp = response.jsonPath();
		List<HashMap<String,String>> list = jsonPathResp.getList("value");
		for(HashMap<String,String> eachList: list ) {
			String MyListId = eachList.get("id");
			if(MyListId.equals(listId)) {
				System.out.println("added list is present"+ listId);
			}
			
		}
	}
	
	@Test()
	public void deleteAlist() {
		RestAssured.baseURI="https://api.newsletter2go.com/lists/"+listId+"";
		
		RequestSpecification build = new RequestSpecBuilder().addHeader("Authorization", "Bearer __eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfXy5haWQiOjQyNzg3OSwiX18uZW52aXJvbm1lbnQiOiJwcm9kdWN0aW9uIiwiX18uZXhwaXJlc0F0IjoxNTkzMzI3MzgwLCJfXy5zY29wZSI6IiIsIl9fLmNpZCI6Ijg3MDY4X0hWcDlRajB6XzkxdDdqYWNlYV83T3ZEZmJuX3MyQVFSTiIsIl9fLnJkMSI6ODk0OTIsIl9fLnJkMiI6MTI1NTJ9.7auOqXOyQOuZuVbuIW4uSWjEUlUui6Ih1qY0PcRRhTQ")
		.build();
		
		Response delete = build.delete();
	}
	
}
