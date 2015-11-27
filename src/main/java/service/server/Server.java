package service.server;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import service.Conf;
import service.geo.api.SuggestionRequestBody;
import service.geo.cache.CachingProvider;
import service.geo.cache.Redis;
import service.geo.dto.ErrorObject;
import service.geo.dto.GeoObject;
import service.geo.provider.DaDataService;
import service.geo.provider.GeoProvider;
import service.geo.provider.GeoProviderException;
import service.geo.provider.GoogleMapsService;
import service.geo.provider.SuggestionProvider;
import service.geo.provider.Utils;
import service.geo.provider.YandexMapsService;

@Path("/")
public class Server {

	private Gson gson = new GsonBuilder().serializeNulls().create();
	private SuggestionProvider _suggestion;
	private GeoProvider _geoYandex;
	private GeoProvider _geoDadata;
	private GeoProvider _geoGoogle;

	public Server() {
		CachingProvider _cache = null;
		if (Conf.WITH_CACHE) {
			try {
				_cache = new Redis();
			} catch (Exception e) {
				System.out.println("Cant connect to redis");
			}
		}
		_suggestion = new DaDataService(_cache);
		_geoDadata = new DaDataService(_cache);
		_geoYandex = new YandexMapsService(_cache);
		_geoGoogle = new GoogleMapsService(_cache);
	}

	@GET
	public String apiMain() {
		return "Nothing here. Check the docs.";
	}

	@GET
	@Path("/suggest")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public String suggestions(
			@QueryParam("q") String q,
			@DefaultValue("10") @QueryParam("c") int count,
			@QueryParam("lat") float lat,
			@QueryParam("lng") float lng,
			@DefaultValue("city") @QueryParam("locations") String locations,
			@QueryParam("locations_value") String locationsValue

	) {
		try {
			SuggestionRequestBody request = new SuggestionRequestBody(q, count);
			return gson.toJson(
					_suggestion.getSuggestions(request, _geoYandex, lat, lng, locations, locationsValue));
		} catch (IOException ex) {
			return gson.toJson(new ErrorObject("Network error"));
		}
	}

	@GET
	@Path("/geocode_reverse")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public String reverseGeo(
			@QueryParam("lat") float lat,
			@QueryParam("lng") float lng) {
		List<GeoObject> objects = _geoGoogle.getObjects(lat, lng);
		if (objects.isEmpty())
			objects = _geoYandex.getObjects(lat, lng);
		return gson.toJson(objects);
	}

	@GET
	@Path("/geocode")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public String reverseGeo(
			@QueryParam("name") String name) {
		List<GeoObject> dadataObjects = _geoDadata.getObjects(name);
		List<GeoObject> yandexObjects = null;

		if (Utils.geocoderResponseQuality(dadataObjects) > 2)
			yandexObjects = _geoYandex.getObjects(name);

		if (yandexObjects == null
				|| Utils.geocoderResponseQuality(dadataObjects) < Utils.geocoderResponseQuality(yandexObjects))
			return gson.toJson(dadataObjects);
		else
			return gson.toJson(yandexObjects);
	}

	@GET
	@Path("/route")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public String route(
			@QueryParam("lat_start") float latStart,
			@QueryParam("lng_start") float lngStart,
			@QueryParam("lat_end") float latEnd,
			@QueryParam("lng_end") float lngEnd) {
		try {
			return gson.toJson(_geoYandex.getRoute(latStart, lngStart, latEnd, lngEnd));
		} catch (GeoProviderException e) {
			return gson.toJson(new ErrorObject(e.getMessage()));
		}
	}

}