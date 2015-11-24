package service.geo.realm.yandex.geocode;

public class RealmYandexGeoObject {
    private RealmYandexPoint Point;
    private RealmYandexBoundedBy boundedBy;
    private String description;
    private String name;
    private RealmYandexMetaDataProperty metaDataProperty;

    public RealmYandexPoint getPoint() {
        return Point;
    }

    public void setPoint(RealmYandexPoint point) {
        Point = point;
    }

    public RealmYandexBoundedBy getBoundedBy() {
        return boundedBy;
    }

    public void setBoundedBy(RealmYandexBoundedBy boundedBy) {
        this.boundedBy = boundedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmYandexMetaDataProperty getMetaDataProperty() {
        return metaDataProperty;
    }

    public void setMetaDataProperty(RealmYandexMetaDataProperty metaDataProperty) {
        this.metaDataProperty = metaDataProperty;
    }
}
