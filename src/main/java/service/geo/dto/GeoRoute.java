package service.geo.dto;

import java.util.List;

public class GeoRoute {

    GeoObject origin;
    GeoObject destination;

    Float distance;
    Float duration;

    List<Point> route;

    public GeoRoute(){};

    public GeoRoute(GeoObject origin, GeoObject destination, Float distance, Float duration) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
    }

    public GeoObject getOrigin() {
        return origin;
    }

    public void setOrigin(GeoObject origin) {
        this.origin = origin;
    }

    public GeoObject getDestination() {
        return destination;
    }

    public void setDestination(GeoObject destination) {
        this.destination = destination;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public List<Point> getRoute() {
        return route;
    }

    public void setRoute(List<Point> route) {
        this.route = route;
    }
}
