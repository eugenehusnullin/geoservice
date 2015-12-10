package service.geo.realm.yandex.geocode;

import service.Conf;
import service.geo.dto.GeoObject;
import service.geo.dto.Point;

import java.util.ArrayList;
import java.util.List;

public class RealmYandexGeocode {
    private RealmYandexResponse response;

    public RealmYandexResponse getResponse() {
        return response;
    }

    public void setResponse(RealmYandexResponse response) {
        this.response = response;
    }

    public List<GeoObject> getGeoObjects(){

        ArrayList<GeoObject> geoObjectList = new ArrayList<>();

        if(getResponse() != null &&
            getResponse().getGeoObjectCollection() != null &&
            getResponse().getGeoObjectCollection().getFeatureMember() != null) {
                getResponse().getGeoObjectCollection().getFeatureMember()
                        .stream()
                        .filter(featureMember -> featureMember != null && featureMember.getGeoObject() != null)
                        .forEach(featureMember -> {
                            String[] pos = featureMember.getGeoObject().getPoint().getPos().split(" ");
                            GeoObject object = new GeoObject(
                                    new Point(Float.parseFloat(pos[1]), Float.parseFloat(pos[0])),
                                    featureMember.getGeoObject().getName(),
                                    featureMember.getGeoObject().getDescription());
                            RealmYandexAddressDetails address = featureMember.getGeoObject().getMetaDataProperty().getGeocoderMetaData().getAddressDetails();
                            
                            object.setKind(featureMember.getGeoObject().getMetaDataProperty().getGeocoderMetaData().getKind());

                            object.setPrecision( Conf.YANDEX_DADATA_PERCISION.get(featureMember.getGeoObject().getMetaDataProperty().getGeocoderMetaData().getPrecision()) );

                            if(address.getCountry() != null) {
                                object.setCountry(address.getCountry().getCountryName());
                                if(address.getCountry().getAdministrativeArea() != null) {
                                    object.setRegion(address.getCountry().getAdministrativeArea().getAdministrativeAreaName());
                                    if((address.getCountry().getAdministrativeArea().getSubAdministrativeArea() != null &&
                                            address.getCountry().getAdministrativeArea().getSubAdministrativeArea().getLocality() != null
                                    ) ||
                                            address.getCountry().getAdministrativeArea().getLocality() != null){
                                        RealmYandexLocality locality;
                                        if(address.getCountry().getAdministrativeArea().getLocality() != null)
                                            locality = address.getCountry().getAdministrativeArea().getLocality();
                                        else
                                            locality = address.getCountry().getAdministrativeArea().getSubAdministrativeArea().getLocality();

                                        String cityName = locality.getLocalityName();

                                        if(locality.getDependentLocality() != null) {
                                            if(locality.getDependentLocality().getPremise() != null)
                                                object.setHouse(locality.getDependentLocality().getPremise().getPremiseNumber());
                                        }

                                        object.setCity(cityName);
                                        if(locality.getThoroughfare() != null){
                                            object.setStreet(locality.getThoroughfare().getThoroughfareName());
                                            if(locality.getThoroughfare().getPremise() != null){
                                                object.setHouse(locality.getThoroughfare().getPremise().getPremiseNumber());
                                            }
                                        }

                                    }
                                }
                            }
                            geoObjectList.add(object);
            });
        }

        return geoObjectList;
    }
}
