package uiBank;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Login {
	
	public static String  authID;
	public static String userID;
	
	public static String loanID;
	
	public File  loadData(String FileName) {
		 File bodyFile=new File(FileName);
		 return bodyFile;
		
	}
	
	@Test
	public void login() {
		File body=new File("./uiBankLogin.json");
		
		RestAssured.baseURI="https://uibank-api.azurewebsites.net";
		
		Response response = RestAssured.given().log().all()
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.when()
		.body(body)
		.post("api/users/login");
		
		response.prettyPrint();
		
		JsonPath jsonPath = response.jsonPath();
		authID=jsonPath.get("id");
		userID=jsonPath.get("userId");
		
		
	}
	
	@Test(dependsOnMethods= {"uiBank.Login.login"})
	public void createAccount() {
		RestAssured.authentication=RestAssured.oauth2(authID);
		Response createAcResponse = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		//.header("authorization", authID)
		.when()
		.body(loadData("./UIBankCreateAccount.json"))
		.post("api/accounts");
		createAcResponse.prettyPrint();
		
		JsonPath jsonPathResponse = createAcResponse.jsonPath();
		String accNumber=jsonPathResponse.get("accountNumber").toString();
		String userid = jsonPathResponse.get("id");
		if(userid.equals(userID)) {
			System.out.println("user is matching from the create account response");
		}
		
	}
	
	@Test(dependsOnMethods="uiBank.Login.createAccount")
	public void getAccountDetails() {
		RestAssured.authentication=RestAssured.oauth2(authID);
		Response getAccountDetailResponse = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.queryParams("filter[where][accountId]",userID)
		.when()
		.get("api/transactions");
		getAccountDetailResponse.prettyPrint();
	}
	
	@Test(dependsOnMethods= {"uiBank.Login.login"})
	public void applyLoan() {
		File bodyFile = loadData("./uiBankCreateLoan.json");
		
		Response LoanResponse = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.when()
		.body(bodyFile)
	    .post("api/quotes/newquote");
		LoanResponse.prettyPrint();
		JsonPath jsonPathLoanResp = LoanResponse.jsonPath();
		loanID = jsonPathLoanResp.get("quoteid").toString();
	}
	
	@Test(dependsOnMethods="uiBank.Login.applyLoan")
	public void getLoanDetails() {
		Response loanStatusresponse = RestAssured.given().log().all()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.when()
		.get("api/quotes/"+loanID+"");
		loanStatusresponse.getStatusCode();
		loanStatusresponse.prettyPrint();
	}

}
