package service.geo.realm.yandex.geocode;

public class RealmYandexMetaDataResponseProperty {
    private RealmYandexGeocoderResponseMetaData GeocoderResponseMetaData;

    public RealmYandexGeocoderResponseMetaData getGeocoderResponseMetaData() {
        return GeocoderResponseMetaData;
    }

    public void setGeocoderResponseMetaData(RealmYandexGeocoderResponseMetaData geocoderResponseMetaData) {
        GeocoderResponseMetaData = geocoderResponseMetaData;
    }
}
