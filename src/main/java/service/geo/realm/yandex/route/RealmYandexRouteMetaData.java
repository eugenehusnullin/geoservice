package service.geo.realm.yandex.route;

public class RealmYandexRouteMetaData {
    RealmYandexRouteValues Action;
    RealmYandexRouteValues Distance;
    RealmYandexRouteValues Duration;
    RealmYandexRouteValues DurationInTraffic;
    float angle;
    String street;
    String text;

    public RealmYandexRouteValues getAction() {
        return Action;
    }

    public void setAction(RealmYandexRouteValues action) {
        Action = action;
    }

    public RealmYandexRouteValues getDistance() {
        return Distance;
    }

    public void setDistance(RealmYandexRouteValues distance) {
        Distance = distance;
    }

    public RealmYandexRouteValues getDuration() {
        return Duration;
    }

    public void setDuration(RealmYandexRouteValues duration) {
        Duration = duration;
    }

    public RealmYandexRouteValues getDurationInTraffic() {
        return DurationInTraffic;
    }

    public void setDurationInTraffic(RealmYandexRouteValues durationInTraffic) {
        DurationInTraffic = durationInTraffic;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
