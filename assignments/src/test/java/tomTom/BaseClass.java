package tomTom;


import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.*;
import io.restassured.http.ContentType;

public class BaseClass {
	
	public static String apiKey="DlP2JNHMNAAi6USt79H0jCx1tJoHWOCt";
	
	public static String secret="pradeep12345";
	
	public static String adminKey="pr9IGKnFvAfnB2Gp7ilo6WRV8IK6LXWAUxQH0ykgub3zAsuj"; 
	
	public static String projectId;
	public static String projectName;
	
	
	@BeforeSuite
	public void setUp() {
		
		RestAssured.baseURI="https://api.tomtom.com/";
		
		
	}
	
	public void generateAdminKey() {
		
		//RestAssured.baseURI="https://api.tomtom.com/";
		
		String asString = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.and()
		.body("")
		.when()
		.post("geofencing/1/register?key="+apiKey+"")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(asString);
		
		String adminKey = js.get("adminKey");
		
		
	}

}
