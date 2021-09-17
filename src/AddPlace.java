import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import dataPack.ReUsableClass;
import dataPack.SchemaFile;

public class AddPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// given 
		// when
		// then 

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(SchemaFile.SchemaAdd())  
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
				.extract().response().asString();

		System.out.println(response);

		JsonPath js = new JsonPath(response);  //for parsing json
		String PlaceId = js.getString("place_id");

		System.out.println(PlaceId);

		//put - update 
		String newAdd = " 79 winter walk, USA (new)";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json;charset=UTF-8")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceId+"\",\r\n"
				+ "\"address\":\""+newAdd+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//get
		String response1 = given().log().all().queryParam("key", "qaclick123").queryParam("place_id",PlaceId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		System.out.println(response1);
		
		//JsonPath js1 = new JsonPath(response1);  //for parsing json
		JsonPath js1 = ReUsableClass.rawJson(response1);
		String AddResult = js1.getString("address");

		System.out.println(AddResult);
		 
		Assert.assertEquals(AddResult, newAdd);

	}

}
