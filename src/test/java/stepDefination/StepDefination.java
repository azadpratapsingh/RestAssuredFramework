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
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild testDataBuild = new TestDataBuild();

	@Given("Add place payload")
	public void add_place_payload() throws IOException {

	}

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		res = given().spec(requestSpecifications()).body(testDataBuild.addPayLoad(name, language, address));
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
