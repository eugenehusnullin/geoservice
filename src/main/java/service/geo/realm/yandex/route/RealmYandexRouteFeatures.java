package service.geo.realm.yandex.route;

import java.util.List;

public class RealmYandexRouteFeatures {

    List<RealmYandexRouteFeatures> features;
    RealmYandexRouteProperties properties;
    String type;

    public List<RealmYandexRouteFeatures> getFeatures() {
        return features;
    }

    public void setFeatures(List<RealmYandexRouteFeatures> features) {
        this.features = features;
    }

    public RealmYandexRouteProperties getProperties() {
        return properties;
    }

    public void setProperties(RealmYandexRouteProperties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
