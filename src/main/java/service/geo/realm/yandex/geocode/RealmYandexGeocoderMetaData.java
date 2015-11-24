package service.geo.realm.yandex.geocode;

public class RealmYandexGeocoderMetaData {

    private String uuid;
    private RealmYandexAddressDetails AddressDetails;
    private String kind;
    private String precision;
    private String text;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public RealmYandexAddressDetails getAddressDetails() {
        return AddressDetails;
    }

    public void setAddressDetails(RealmYandexAddressDetails addressDetails) {
        AddressDetails = addressDetails;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
