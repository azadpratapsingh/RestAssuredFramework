package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefination stepDefination = new StepDefination();
		stepDefination.add_place_payload("Shetty", "French", "Asia");
		stepDefination.u_ser_calls_with_post_http_request("AddPlaceAPI", "POST");
		stepDefination.verify_place_id_created_maps_to_using("Shetty", "POST");

	}
}
