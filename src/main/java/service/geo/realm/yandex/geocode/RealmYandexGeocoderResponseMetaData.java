package service.geo.realm.yandex.geocode;

public class RealmYandexGeocoderResponseMetaData {
    private RealmYandexPoint Point;
    private String found;
    private String request;
    private String results;

    public RealmYandexPoint getPoint() {
        return Point;
    }

    public void setPoint(RealmYandexPoint point) {
        Point = point;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
