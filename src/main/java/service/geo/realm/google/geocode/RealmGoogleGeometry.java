package service.geo.realm.google.geocode;

import service.geo.realm.google.RealmGoogleBounds;
import service.geo.realm.google.RealmGoogleCord;

public class RealmGoogleGeometry {

    RealmGoogleBounds bounds;
    RealmGoogleCord location;
    String location_type;
    RealmGoogleBounds viewport;

    public RealmGoogleBounds getBounds() {
        return bounds;
    }

    public void setBounds(RealmGoogleBounds bounds) {
        this.bounds = bounds;
    }

    public RealmGoogleCord getLocation() {
        return location;
    }

    public void setLocation(RealmGoogleCord location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public RealmGoogleBounds getViewport() {
        return viewport;
    }

    public void setViewport(RealmGoogleBounds viewport) {
        this.viewport = viewport;
    }
}