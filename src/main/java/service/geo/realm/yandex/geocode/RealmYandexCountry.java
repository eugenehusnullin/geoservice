package service.geo.realm.yandex.geocode;

public class RealmYandexCountry {
    private String AddressLine;
    private RealmYandexAdministrativeArea  AdministrativeArea;
    private String CountryName;
    private String CountryNameCode;

    public String getAddressLine() {
        return AddressLine;
    }

    public void setAddressLine(String addressLine) {
        AddressLine = addressLine;
    }

    public RealmYandexAdministrativeArea getAdministrativeArea() {
        return AdministrativeArea;
    }

    public void setAdministrativeArea(RealmYandexAdministrativeArea administrativeArea) {
        AdministrativeArea = administrativeArea;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryNameCode() {
        return CountryNameCode;
    }

    public void setCountryNameCode(String countryNameCode) {
        CountryNameCode = countryNameCode;
    }

}
