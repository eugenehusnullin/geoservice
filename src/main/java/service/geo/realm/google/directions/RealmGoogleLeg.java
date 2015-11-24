package service.geo.realm.google.directions;

import service.geo.realm.google.RealmGoogleCord;

import java.util.List;

public class RealmGoogleLeg {
    private RealmGoogleDistance distance;
    private RealmGoogleDistance duration;
    private String end_address;
    private RealmGoogleCord end_location;
    private String start_address;
    private RealmGoogleCord start_location;
    private List<RealmGoogleStep> steps;

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

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public RealmGoogleCord getEnd_location() {
        return end_location;
    }

    public void setEnd_location(RealmGoogleCord end_location) {
        this.end_location = end_location;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public RealmGoogleCord getStart_location() {
        return start_location;
    }

    public void setStart_location(RealmGoogleCord start_location) {
        this.start_location = start_location;
    }

    public List<RealmGoogleStep> getSteps() {
        return steps;
    }

    public void setSteps(List<RealmGoogleStep> steps) {
        this.steps = steps;
    }
}
