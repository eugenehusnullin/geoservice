package service.geo.provider;

import service.geo.dto.GeoObject;

import java.util.List;

public class Utils {

    public static Integer geocoderResponseQuality(List<GeoObject> responses){
        Integer quality = 5;

        if(responses == null) return 999;

        for(GeoObject response : responses){
            if(response != null)
                if(response.getPrecision() < quality) quality = response.getPrecision();
        }

        return quality;

    }
}