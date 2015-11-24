package service.geo.realm.yandex.geocode;

public class RealmYandexResponse {
    private RealmYandexObjectCollection GeoObjectCollection;

    public RealmYandexResponse(RealmYandexObjectCollection GeoObjectCollection) {
        this.GeoObjectCollection = GeoObjectCollection;
    }

    public RealmYandexObjectCollection getGeoObjectCollection() {
        return GeoObjectCollection;
    }

    public void setGeoObjectCollection(RealmYandexObjectCollection geoObjectCollection) {
        GeoObjectCollection = geoObjectCollection;
    }
}