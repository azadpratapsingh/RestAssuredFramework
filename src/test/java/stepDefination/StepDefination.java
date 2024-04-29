package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals; // import static explicitly, wont show by eclipse

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Locations;
import resources.TestDataBuild;

public class StepDefination {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild testDataBuild = new TestDataBuild();

	@Given("Add place payload")
	public void add_place_payload() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setPhone_number("4564554");
		place.setWebsite("http://google.com");
		place.setName("Frontline house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		place.setTypes(myList);
		Locations location = new Locations();
		location.setLng(33.427362);
		location.setLat(-38.383494);
		place.setLocation(location);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		res = given().spec(req).body(testDataBuild.addPayLoad());

	}

	@When("USer calls {string} with Post http request")
	public void u_ser_calls_with_post_http_request(String string) {
		response = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();

	}

	@Then("The API call got success eith status code {int}")
	public void the_api_call_got_success_eith_status_code(Integer int1) {
		assertEquals(response.statusCode(), 200); // import static explicitly, wont show by eclipse

	}

	@Then("{string} is response body is {string}")
	public void is_response_body_is(String keyValue, String ExpectedValue) {
		String respString = response.asString();
		JsonPath jsonPath = new JsonPath(respString);
		assertEquals(jsonPath.get(keyValue).toString(), ExpectedValue);

	}
}
