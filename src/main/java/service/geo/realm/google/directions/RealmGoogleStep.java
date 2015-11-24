package service.geo.realm.google.directions;

import service.geo.realm.google.RealmGoogleCord;

public class RealmGoogleStep {
    private RealmGoogleDistance distance;
    private RealmGoogleDistance duration;
    private RealmGoogleCord end_location;
    private String html_instructions;
    private RealmGooglePolyline polyline;
    private RealmGoogleCord start_location;
    private String travel_mode;

    public RealmGoogleDistance getDistance() {
        return distance;
    }

    public void setDistance(RealmGoogleDistance distance) {
        this.distance = distance;
    }

    public RealmGoogleDistance getDuration() {
        return duration;
    }

    public void setDuration(RealmGoogleDistance duration) {
        this.duration = duration;
    }

    public RealmGoogleCord getEnd_location() {
        return end_location;
    }

    public void setEnd_location(RealmGoogleCord end_location) {
        this.end_location = end_location;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public RealmGooglePolyline getPolyline() {
        return polyline;
    }

    public void setPolyline(RealmGooglePolyline polyline) {
        this.polyline = polyline;
    }

    public RealmGoogleCord getStart_location() {
        return start_location;
    }

    public void setStart_location(RealmGoogleCord start_location) {
        this.start_location = start_location;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }
}