package service.geo.realm.yandex.geocode;

public class RealmYandexSubAdministrativeArea {
    private RealmYandexLocality Locality;
    private String SubAdministrativeAreaName;

    public RealmYandexLocality getLocality() {
        return Locality;
    }

    public void setLocality(RealmYandexLocality locality) {
        Locality = locality;
    }

    public String getSubAdministrativeAreaName() {
        return SubAdministrativeAreaName;
    }

    public void setSubAdministrativeAreaName(String subAdministrativeAreaName) {
        SubAdministrativeAreaName = subAdministrativeAreaName;
    }
}
