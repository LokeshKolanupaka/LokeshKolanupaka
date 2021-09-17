import dataPack.SchemaFile;
import io.restassured.path.json.JsonPath;

public class ComplexJson {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(SchemaFile.CoursePrice());

		//to get count of vaules in array 
		int count = js.getInt("courses.size()");
		System.out.println(count);

		//to print price
		int price  = js.getInt("dashboard.purchaseAmount");
		System.out.println(price);

		//print course
		String titleFirst = js.get("courses[0].title");
		System.out.println(titleFirst);
		int FirstPrice = js.get("courses[0].price");
		System.out.println(FirstPrice);
		int Firstcopies = js.get("courses[0].copies");
		System.out.println(Firstcopies);                 

		// courses with values in single line
		for(int i=0; i<count; i++) {
			String titles = js.get("courses["+i+"].title");			//below line to call int to string
			int prices = js.getInt("courses["+i+"].price");			//System.out.println(js.get("courses["+i+"].price").toString());
			int copies = js.getInt("courses[ "+i+"].copies");
			System.out.println(titles + " " + prices + " " +copies);

		}      

		// no of copies sold by particular course.
		System.out.println("no of copies sold by particular course.");
		for(int i=0; i<count; i++) {
			String titles = js.get("courses["+i+"].title");
			if(titles.equalsIgnoreCase("Selenium Python"))
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
			}
		}
	} 

}
