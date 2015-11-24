package service.geo.realm.yandex.geocode;

public class RealmYandexAdministrativeArea {
    private String AdministrativeAreaName;
    private RealmYandexSubAdministrativeArea SubAdministrativeArea;
    private RealmYandexLocality Locality;

    public String getAdministrativeAreaName() {
        return AdministrativeAreaName;
    }

    public void setAdministrativeAreaName(String administrativeAreaName) {
        AdministrativeAreaName = administrativeAreaName;
    }

    public RealmYandexSubAdministrativeArea getSubAdministrativeArea() {
        return SubAdministrativeArea;
    }

    public void setSubAdministrativeArea(RealmYandexSubAdministrativeArea subAdministrativeArea) {
        SubAdministrativeArea = subAdministrativeArea;
    }

    public RealmYandexLocality getLocality() {
        return Locality;
    }

    public void setLocality(RealmYandexLocality locality) {
        Locality = locality;
    }
}
