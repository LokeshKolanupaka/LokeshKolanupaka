package dataPack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DynamicJson {


	@Test(dataProvider="BookData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		Response resp = given().
				header("Content-Type","application/json").
				body(SchemaFile.AddBook(isbn,aisle)).
				when().post("/Library/Addbook.php").
				then().assertThat().statusCode(200).extract().response();
		JsonPath js = ReUsableClass.rawToJson(resp);
		String id = js.get("ID");
		System.out.println(id); 
	}  
/*
	@Test(dataProvider="BookData1")
	public void deleteBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().
				header("Content-Type","application/json").
				when().delete("/Library/Addbook.php").
				then().assertThat(). statusCode(200).
				extract().response().asString();

		System.out.println("Response is\t"+response);
	}      */

@DataProvider(name="BookData")
public Object getData() {
	return new Object[][] {{"teswqwwwt1","111"},{"teswqwwwt21","2111"}, {"tssssqwseste1","1111"}};
}
@DataProvider(name="BookData1")
public Object getData1() {
	return new Object[][] {{"teswqwwwt1","111"},{"teswqwwwt21","2111"}, {"tssssqwseste1","1111"}};
}

}
