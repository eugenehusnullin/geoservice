package service.geo.dto;

public class GeoSuggestion {

    String address_full;
    String address_short;
    String place_title;
    String country;
    String region;
    String city;
    String district;
    String street;
    String house;
    float lat;
    float lng;

    public String getAddress_full() {
        return address_full;
    }

    public void setAddress_full(String address_full) {
        this.address_full = address_full;
    }

    public String getAddress_short() {
        return address_short;
    }

    public void setAddress_short(String address_short) {
        this.address_short = address_short;
    }

    public String getPlace_title() {
        return place_title;
    }

    public void setPlace_title(String place_title) {
        this.place_title = place_title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
