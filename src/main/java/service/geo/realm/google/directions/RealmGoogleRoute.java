package service.geo.realm.google.directions;

import service.geo.realm.google.RealmGoogleBounds;

import java.util.List;

public class RealmGoogleRoute {
    private RealmGoogleBounds bounds;
    private String copyrights;
    private List<RealmGoogleLeg> legs;
    private RealmGoogleOverviewPolyline overview_polyline;
    private String summary;

    public RealmGoogleBounds getBounds() {
        return bounds;
    }

    public void setBounds(RealmGoogleBounds bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public List<RealmGoogleLeg> getLegs() {
        return legs;
    }

    public void setLegs(List<RealmGoogleLeg> legs) {
        this.legs = legs;
    }

    public RealmGoogleOverviewPolyline getOverview_polyline() {
        return overview_polyline;
    }

    public void setOverview_polyline(RealmGoogleOverviewPolyline overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}