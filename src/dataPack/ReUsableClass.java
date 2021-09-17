package dataPack;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReUsableClass {

	public static JsonPath rawJson(String response){

		JsonPath js2 = new JsonPath(response);  //for parsing json
		return js2; 

	} 
	
	public static JsonPath rawToJson(Response r) {
		String respon=r.asString();
		JsonPath x=new JsonPath(respon);
		return x;
	}
	
	public static String getSessionKEY() {
		RestAssured.baseURI="http://216.10.245.166";
		Response response = given().
				header("Content-Type","application/json").
				body("{\"username\":\"rahulonlinetutor\",\"password\":\"Jira12345\"}").
				when().post("/rest/auth/1/session").
				then().statusCode(200).extract().response();
		JsonPath js2 = ReUsableClass.rawToJson(response);
		String sessionid = js2.get("session.value");
		return sessionid;
		
	}
	

} 
