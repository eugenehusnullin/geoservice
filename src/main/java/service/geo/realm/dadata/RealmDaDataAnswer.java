package service.geo.realm.dadata;

import com.google.gson.annotations.SerializedName;

public class RealmDaDataAnswer {

    private String uuid;

    private String value;
    private String unrestricted_value;
    @SerializedName("data")
    private RealmData realmData;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnrestricted_value() {
        return unrestricted_value;
    }

    public void setUnrestricted_value(String unrestricted_value) {
        this.unrestricted_value = unrestricted_value;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public RealmData getRealmData() {
        return realmData;
    }

    public void setRealmData(RealmData realmData) {
        this.realmData = realmData;
    }
}