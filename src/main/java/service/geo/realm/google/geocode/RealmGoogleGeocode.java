package service.geo.realm.google.geocode;

import service.geo.dto.GeoObject;
import service.geo.dto.Point;

import java.util.ArrayList;
import java.util.List;

public class RealmGoogleGeocode {
    String status;
    List<RealmGoogleGeocodeResult> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RealmGoogleGeocodeResult> getResults() {
        return results;
    }

    public void setResults(List<RealmGoogleGeocodeResult> results) {
        this.results = results;
    }

    public List<GeoObject> getGeoObjects(){

        List<GeoObject> ret = new ArrayList<>();

        for(RealmGoogleGeocodeResult realmGoogleGeocodeResult : results){

            // The most relative object is in the top of list, better way to get only him.
            // After the first object goes higher level objects (etc country)

            GeoObject object = new GeoObject(
                    new Point(realmGoogleGeocodeResult.getGeometry().getLocation().getLat(),
                            realmGoogleGeocodeResult.getGeometry().getLocation().getLng()),
                    realmGoogleGeocodeResult.getFormatted_address(),
                    realmGoogleGeocodeResult.getFormatted_address()
            );

            for(RealmGoogleAddressComponent comp : realmGoogleGeocodeResult.getAddress_components()){
                if(comp.getTypes().contains("street_number")) object.setHouse(comp.getShort_name());
                if(comp.getTypes().contains("route")) object.setStreet(comp.getShort_name());
                if(comp.getTypes().contains("locality")) object.setCity(comp.getShort_name());
                if(comp.getTypes().contains("administrative_area_level_1")) object.setRegion(comp.getLong_name());
                if(comp.getTypes().contains("country")) object.setCountry(comp.getLong_name());
            }

            ret.add(object);

        }

        return ret;
    }
}
