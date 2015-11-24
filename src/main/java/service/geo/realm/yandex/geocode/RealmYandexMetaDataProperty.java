package service.geo.realm.yandex.geocode;

public class RealmYandexMetaDataProperty {
    private RealmYandexGeocoderMetaData GeocoderMetaData;
    private String name;

    public RealmYandexGeocoderMetaData getGeocoderMetaData() {
        return GeocoderMetaData;
    }

    public void setGeocoderMetaData(RealmYandexGeocoderMetaData geocoderMetaData) {
        GeocoderMetaData = geocoderMetaData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
