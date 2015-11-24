package service.geo.realm.yandex.geocode;

public class RealmYandexDependentLocality {
    String DependentLocalityName;
    RealmYandexPremise Premise;

    public String getDependentLocalityName() {
        return DependentLocalityName;
    }

    public void setDependentLocalityName(String dependentLocalityName) {
        DependentLocalityName = dependentLocalityName;
    }

    public RealmYandexPremise getPremise() {
        return Premise;
    }

    public void setPremise(RealmYandexPremise premise) {
        Premise = premise;
    }
}
