package hotelBeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetBookingDetails extends HotelBedsBase{
	
	@Test(dependsOnMethods= {"hotelBeds.BookAroomInHotel.bookAroom"})
	public void getBookingInfo() {
		
		
		Response response = RestAssured.given().log().all()
		.headers(paramsMap).accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.queryParam("cancellationFlag", "CANCELLATION")
		.when()
		.get("bookings/"+bookingId+"");
		
		response.prettyPrint();
		
	
	}
	
	@Test(dependsOnMethods= {"hotelBeds.GetBookingDetails.getBookingInfo"})
	public void deleteBooking() {
		
		Response response = RestAssured.given().log().all()
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.headers(paramsMap)
		.when()
		.delete("bookings/"+bookingId+"");
		
		response.prettyPrint();
		
	
	}

}
