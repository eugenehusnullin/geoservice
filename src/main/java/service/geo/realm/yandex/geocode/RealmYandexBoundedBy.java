package service.geo.realm.yandex.geocode;

public class RealmYandexBoundedBy {
    private RealmYandexEnvelope Envelope;
    private String description;

    public RealmYandexEnvelope getEnvelope() {
        return Envelope;
    }

    public void setEnvelope(RealmYandexEnvelope envelope) {
        Envelope = envelope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
