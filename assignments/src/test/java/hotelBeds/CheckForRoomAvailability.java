package hotelBeds;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CheckForRoomAvailability extends HotelBedsBase{
	
	List<Map<Object, Object>> hotelList;
	List<Map<Object,Object>> roomsList;
	List<Map<Object,Object>> rates;
	
	@Test
	public void checkForAvaialability() {
		
		File bodyFile=new File("hotelAvaialbility.json");
		
		Response response = RestAssured.given().log().all().headers(paramsMap)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when().body(bodyFile).post("hotels");
		//response.prettyPrint();
		
		
		JsonPath jsonResponse = response.jsonPath();
		
		 hotelList = jsonResponse.getList("hotels.hotels");
		
		
		for(int i=0;i<hotelList.size()-1;i++) {
			 roomsList = jsonResponse.getList("hotels.hotels["+i+"].rooms");
			
			
			for(int j=0;j<roomsList.size()-1;j++) {
				 rates = jsonResponse.getList("hotels.hotels["+i+"].rooms["+j+"].rates");
				//System.out.println(rates);
				for(int k=0;k<rates.size()-1;k++) {
					if(rates.get(k).get("net")!=null&&rates.get(k).get("sellingRate")!=null) {
						System.out.println("********************************************************");
						System.out.println("hotel nam is-->"+ hotelList.get(i).get("name").toString());
						System.out.println("room name is-->"+ roomsList.get(j).toString());
						System.out.println("room net rates is-->"+ rates.get(k).get("net"));
						System.out.println("room selling rate is-->"+rates.get(k).get("sellingRate"));
						System.out.println("room rateType is-->"+ rates.get(k).get("rateType"));
						System.out.println("********************************************************");
					}
				}
			}
		}
		
		for (Map<Object, Object> eachRates : rates) {
			
			if(eachRates.get("rateType").equals("BOOKABLE")) {
				 ratekey=eachRates.get("rateKey");
				System.out.println("rate key is-->"+ratekey);
				break;
			}
			
		}
		
	
		
		
		
	}

}
