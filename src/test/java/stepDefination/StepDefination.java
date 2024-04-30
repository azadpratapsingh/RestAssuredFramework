package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals; // import static explicitly, wont show by eclipse

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification req;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild testDataBuild = new TestDataBuild();
	String place_Id;
	JsonPath jsonPath;
	static String place_id;

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		req = given().spec(requestSpecifications()).body(testDataBuild.addPayLoad(name, language, address));
	}

	@When("USer calls {string} with {string} http request")
	public void u_ser_calls_with_post_http_request(String resource, String method) {

		APIResources resourceApi = APIResources.valueOf(resource);
		System.out.println(resourceApi.getResource());

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = req.when().post(resourceApi.getResource()).then().spec(resSpec).extract().response();
		else if (method.equalsIgnoreCase("GET")) {
			response = req.when().get(resourceApi.getResource()).then().spec(resSpec).extract().response();
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = req.when().delete(resourceApi.getResource()).then().spec(resSpec).extract().response();
		}

	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_eith_status_code(Integer int1) {
		assertEquals(response.statusCode(), 200); // import static explicitly, wont show by eclipse

	}

	@Then("{string} is response body is {string}")
	public void is_response_body_is(String keyValue, String ExpectedValue) {

		assertEquals(getJsonPath(response, keyValue), ExpectedValue);

	}

	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		req = given().spec(requestSpecifications()).queryParam("place_id", place_id);
		u_ser_calls_with_post_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		req = given().spec(requestSpecifications()).body(testDataBuild.deletePlacePayload(place_id));
	}

}
