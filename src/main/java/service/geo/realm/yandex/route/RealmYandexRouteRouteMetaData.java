package service.geo.realm.yandex.route;

public class RealmYandexRouteRouteMetaData {
    RealmYandexRouteValues Distance;
    RealmYandexRouteValues Duration;
    RealmYandexRouteValues DurationInTraffic;
    String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
