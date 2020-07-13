package amio;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class amioOps {
	
	public static String channelId;
	public static HashMap<String, String> headersMap;
	public static List<String> channelList;
	
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI="https://api.amio.io";
		 headersMap= new HashMap<String, String>();
		headersMap.put("Content-Type","application/json");
		headersMap.put("Authorization", "Bearer D3geWsLHw51jgTTpmqPd7pnVcU2N2H6EWMg1vgnndR5JByHUuDxe2w4q3d96U40XnG6nuj2fzSHZHXlIVXAuIiUfPB");
		
				
	}
	
	@Test
	public void createChannel() {
		
		File bodyFile=new File("amio.json");
	
	//	RestAssured.baseURI="https://api.amio.io/v1/channels";
		
		Response response = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.headers(headersMap)
		.body(bodyFile)
		.when()
		.post("v1/channels")
		.then().log().all()
		.statusCode(201).and().extract().response();
		JsonPath jsonPathResponse= response.jsonPath();
		 channelId = jsonPathResponse.get("id");
		 System.out.println("channel id thats created was-->"+channelId);
	}
	
	@Test(dependsOnMethods= {"amio.amioOps.createChannel"})
	public void Confirm_channel_present() {
	Response response = RestAssured.given().log().all()
	.headers(headersMap)
	.when()
	.get("v1/channels");
	
	 channelList = response.jsonPath().getList("id");
	System.out.println(channelList);
	if(channelList.contains(channelId)) {
		System.out.println("channel has been succesfull created--->"+ channelId);
		
	}
	}
	
	@Test(dependsOnMethods= {"amio.amioOps.createChannel"})
	public void deleteChannel() {
		
		Response response = RestAssured.given().log().all()
		.headers(headersMap)
		.when()
		.delete("https://api.amio.io/v1/channels/"+channelId+"").then()
		.log().all()
		.statusCode(204)
		.and().extract().response();
		//JsonPath jsonPathResponse = response.jsonPath();
		//jsonPathResponse.get
		/*if(channelList.contains(channelId)) {
			System.out.println("channel ID hasn't been deleted");
			}*/
	}

}
