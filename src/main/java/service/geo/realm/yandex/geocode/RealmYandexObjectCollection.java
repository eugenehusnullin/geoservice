package service.geo.realm.yandex.geocode;

import java.util.ArrayList;

public class RealmYandexObjectCollection {
    private ArrayList<RealmYandexFeatureMember> featureMember;
    private RealmYandexMetaDataResponseProperty metaDataProperty;

    public ArrayList<RealmYandexFeatureMember> getFeatureMember() {
        return featureMember;
    }

    public void setFeatureMember(ArrayList<RealmYandexFeatureMember> featureMember) {
        this.featureMember = featureMember;
    }

    public RealmYandexMetaDataResponseProperty getMetaDataProperty() {
        return metaDataProperty;
    }

    public void setMetaDataProperty(RealmYandexMetaDataResponseProperty metaDataProperty) {
        this.metaDataProperty = metaDataProperty;
    }
}
