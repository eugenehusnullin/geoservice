package service.geo.realm.yandex.geocode;

public class RealmYandexLocality {
    private String uuid;
    private String LocalityName;
    private RealmYandexThoroughfare Thoroughfare;
    private RealmYandexDependentLocality DependentLocality;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLocalityName() {
        return LocalityName;
    }

    public void setLocalityName(String localityName) {
        LocalityName = localityName;
    }

    public RealmYandexThoroughfare getThoroughfare() {
        return Thoroughfare;
    }

    public void setThoroughfare(RealmYandexThoroughfare thoroughfare) {
        Thoroughfare = thoroughfare;
    }

    public RealmYandexDependentLocality getDependentLocality() {
        return DependentLocality;
    }

    public void setDependentLocality(RealmYandexDependentLocality dependentLocality) {
        DependentLocality = dependentLocality;
    }
}
