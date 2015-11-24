package service.geo.realm.dadata;

import java.util.List;

import service.geo.dto.GeoObject;
import service.geo.dto.Point;

public class RealmDaDataSuggestion {
	private String uuid;

	private List<RealmDaDataAnswer> suggestions;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<RealmDaDataAnswer> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<RealmDaDataAnswer> suggestions) {
		this.suggestions = suggestions;
	}

	public GeoObject getGeoObject() {

		if (suggestions.size() > 0) {
			RealmDaDataAnswer answer = suggestions.get(0);

			GeoObject object = new GeoObject(
					new Point(answer.getRealmData().getGeo_lat(),
							answer.getRealmData().getGeo_lon()),
					answer.getValue(),
					answer.getValue());

			object.setCountry(answer.getRealmData().getCountry());
			object.setRegion(answer.getRealmData().getRegion_with_type());
			object.setDistrict(answer.getRealmData().getCity_district());
			object.setCity(answer.getRealmData().getCity_with_type());
			object.setStreet(answer.getRealmData().getStreet_with_type());
			object.setHouse(answer.getRealmData().getHouse());
			object.setPrecision(Integer.parseInt(answer.getRealmData().getQc_geo()));

			return object;
		}
		return null;
	}

}