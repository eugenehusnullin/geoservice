package service.geo.realm.yandex.route;

import java.util.List;

public class RealmYandexRouteRouterMetaData {

    List<RealmYandexRouteRouterMetaDataWaypoint> Waypoints;

    public List<RealmYandexRouteRouterMetaDataWaypoint> getWaypoints() {
        return Waypoints;
    }

    public void setWaypoints(List<RealmYandexRouteRouterMetaDataWaypoint> waypoints) {
        Waypoints = waypoints;
    }
}
