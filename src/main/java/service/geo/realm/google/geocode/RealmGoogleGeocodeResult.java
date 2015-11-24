package service.geo.realm.google.geocode;

import java.util.List;

public class RealmGoogleGeocodeResult {

    List<RealmGoogleAddressComponent> address_components;
    String formatted_address;
    RealmGoogleGeometry geometry;
    String place_id;
    List<String> types;

    public List<RealmGoogleAddressComponent> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List<RealmGoogleAddressComponent> address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public RealmGoogleGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(RealmGoogleGeometry geometry) {
        this.geometry = geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
