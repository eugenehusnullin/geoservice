package service.geo.api;

import java.util.HashMap;
import java.util.List;

public class SuggestionRequestBody{

    private String query;
    private int count;
    private List<HashMap<String, String>> locations;

    public SuggestionRequestBody(String query, int count) {
        this.query = query;
        this.count = count;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HashMap<String, String>> getLocations() {
        return locations;
    }

    public void setLocations(List<HashMap<String, String>> locations) {
        this.locations = locations;
    }
}
