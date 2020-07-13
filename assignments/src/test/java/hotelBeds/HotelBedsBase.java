package hotelBeds;

import java.util.HashMap;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class HotelBedsBase {
	
	
	public static HashMap<String, String> paramsMap=new HashMap<String, String>();
	public static String signature=null;
	
	public static Object ratekey;
	public static  Object bookingId;
	
	

	@BeforeSuite
	public void generateSignature() {
		
		RestAssured.baseURI="https://api.test.hotelbeds.com/hotel-api/1.0";
		  String apiKey="ku9nehpkuq23tabsasf3sbfh";
		  String secret="WVwC5EWScH";
		  
		
		
		 signature = org.apache.commons.codec.digest.DigestUtils
				 .sha256Hex(apiKey + secret + System.currentTimeMillis() / 1000);
		 
		 paramsMap.put("Api-Key", "ku9nehpkuq23tabsasf3sbfh");
		 paramsMap.put("X-Signature", signature);
		
		System.out.println("signature is-->"+ signature);
		
		
	}
}
