package hotelBeds;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookAroomInHotel extends HotelBedsBase{
	
	@Test(dependsOnMethods= {"hotelBeds.CheckForRoomAvailability.checkForAvaialability"})
	public void bookAroom() {
		
		
		//RestAssured.baseURI="https://api.test.hotelbeds.com/hotel-api/1.0";
		Response response = RestAssured.given().log().all()
		.headers(paramsMap)//.queryParam("Accept-Encoding", "gzip")
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.when()
		.body("        {\r\n" + 
				"            \"holder\": {\r\n" + 
				"                \"name\": \"roger\",\r\n" + 
				"                \"surname\": \"k\"\r\n" + 
				"            },\r\n" + 
				"            \"rooms\": [\r\n" + 
				"                {\r\n" + 
				"                    \"rateKey\": \""+ratekey+"\",\r\n" + 
				"                    \"paxes\": [\r\n" + 
				"                        {\r\n" + 
				"                            \"roomId\": 1,\r\n" + 
				"                            \"type\": \"AD\",\r\n" + 
				"                            \"name\": \"Roger\",\r\n" + 
				"                            \"surname\": \"K\"\r\n" + 
				"                        }\r\n" + 
				"                       \r\n" + 
				"                    ]\r\n" + 
				"                }\r\n" + 
				"            ],\r\n" + 
				"            \"clientReference\": \"IntegrationAgency\",\r\n" + 
				"            \"remark\": \"Booking remarks are to be written here.\",\r\n" + 
				"            \"tolerance\": 2\r\n" + 
				"        }").post("bookings");
		
		response.prettyPrint();
		
		JsonPath jsonPathResponse = response.jsonPath();
		
		  bookingId = jsonPathResponse.get("booking.reference");
		  System.out.println("booking id is"+ bookingId);
		 
		Object status = jsonPathResponse.get("booking.status");
		if(status.toString().equalsIgnoreCase("CONFIRMED")) {
			System.out.println("hotel room successfully booked");
		}
		
		
	}

}
