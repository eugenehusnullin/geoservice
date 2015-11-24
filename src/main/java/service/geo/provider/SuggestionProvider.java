package service.geo.provider;

import service.geo.api.SuggestionRequestBody;
import service.geo.dto.GeoSuggestion;

import java.io.IOException;
import java.util.List;

public interface SuggestionProvider {

    List<GeoSuggestion> getSuggestions(SuggestionRequestBody query, GeoProvider geoProvider, float lat, float lng, String locations, String locationsValue) throws IOException;

}
