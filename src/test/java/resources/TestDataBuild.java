package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Locations;

public class TestDataBuild {
	public AddPlace addPayLoad(String name, String language, String address) {
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		place.setPhone_number("4564554");
		place.setWebsite("http://google.com");
		place.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		place.setTypes(myList);
		Locations location = new Locations();
		location.setLng(33.427362);
		location.setLat(-38.383494);
		place.setLocation(location);
		return place;
	}
}
