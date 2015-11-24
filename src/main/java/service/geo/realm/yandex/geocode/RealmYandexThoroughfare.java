package service.geo.realm.yandex.geocode;

public class RealmYandexThoroughfare {
    String ThoroughfareName;
    RealmYandexPremise Premise;

    public String getThoroughfareName() {
        return ThoroughfareName;
    }

    public void setThoroughfareName(String thoroughfareName) {
        ThoroughfareName = thoroughfareName;
    }

    public RealmYandexPremise getPremise() {
        return Premise;
    }

    public void setPremise(RealmYandexPremise premise) {
        Premise = premise;
    }
}
