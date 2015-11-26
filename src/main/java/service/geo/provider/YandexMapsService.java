package service.geo.provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import service.Conf;
import service.geo.api.YandexGeocodeApi;
import service.geo.api.YandexMapsApi;
import service.geo.cache.CachingProvider;
import service.geo.dto.GeoObject;
import service.geo.dto.GeoRoute;
import service.geo.realm.yandex.geocode.RealmYandexFeatureMember;
import service.geo.realm.yandex.geocode.RealmYandexGeocode;
import service.geo.realm.yandex.route.RealmYandexRouteResponse;

public class YandexMapsService implements GeoProvider {

	private CachingProvider _cache;
	private Gson _gson;
	private YandexGeocodeApi _geocodeApi;
	private YandexMapsApi _mapsApi;

	public YandexMapsService(CachingProvider cache) {

		_cache = cache;
		_gson = new GsonBuilder()
				.serializeNulls()
				.setExclusionStrategies(new ExclusionStrategy() {
					@Override
					public boolean shouldSkipField(FieldAttributes f) {
						return false;
					}

					@Override
					public boolean shouldSkipClass(Class<?> clazz) {
						return false;
					}
				}).create();

		RestAdapter restAdapter = new RestAdapter.Builder()
				.setLogLevel(Conf.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
				.setEndpoint(Conf.YANDEX_GEOCODE_BASE_URL)
				.setConverter(new GsonConverter(_gson))
				.build();

		RequestInterceptor COOKIES_REQUEST_INTERCEPTOR = new RequestInterceptor() {
			@Override
			public void intercept(RequestFacade request) {
				request.addHeader("accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				request.addHeader("accept-language", "en-US,en;q=0.8");
				request.addHeader("upgrade-insecure-requests", "1");
				request.addHeader("user-agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
				request.addHeader("Cookie", "yandexuid=1470774661426770341");
			}
		};

		RestAdapter restMapsAdapter = new RestAdapter.Builder()
				.setLogLevel(Conf.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
				.setEndpoint(Conf.YANDEX_MAPS_API_BASE_URL)
				.setRequestInterceptor(COOKIES_REQUEST_INTERCEPTOR)
				.setConverter(new GsonConverter(_gson))
				.build();

		_geocodeApi = restAdapter.create(YandexGeocodeApi.class);

		_mapsApi = restMapsAdapter.create(YandexMapsApi.class);

	}

	public static String getTextFromUrl(String url) throws Exception {
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		connection.setRequestProperty("Cookie", "yandexuid=1470774661426770341");
		connection.setRequestProperty("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");

		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						connection.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);

		in.close();

		return response.toString();
	}

	private String getToken() throws GeoProviderException {
		String document;
		try {
			document = getTextFromUrl(Conf.YANDEX_MAPS_JS);

			int index = document.indexOf(Conf.YANDEX_TOKEN_SEARCH);

			if (index > 0) {
				return document.substring(Conf.YANDEX_TOKEN_SEARCH.length() + index,
						Conf.YANDEX_TOKEN_SEARCH.length() + index + (int) Conf.YANDEX_TOKEN_LENGTH);
			} else {
				throw new Exception("Invalid yandex token.");
			}

		} catch (Exception e) {
			throw new GeoProviderException("Cant get token");
		}
	}

	private List<GeoObject> getCachedItems(String cacheKey) {
		ArrayList<GeoObject> geoO = new ArrayList<>();
		ArrayList unparsedList = _gson.fromJson(_cache.get(cacheKey), ArrayList.class);
		for (Object unparsedItem : unparsedList) {
			geoO.add(_gson.fromJson(_gson.toJson(unparsedItem), GeoObject.class));
		}
		return geoO;
	}

	@Override
	public List<GeoObject> getObjects(String name) {

		String cacheKey = String.format("%s%s", Conf.YANDEX_GEOCODE_CACHE_PREFIX, name);

		if (_cache != null && _cache.exists(cacheKey)) {
			return getCachedItems(cacheKey);
		} else {
			List<GeoObject> response = _geocodeApi.geocode(new HashMap<String, String>() {
				{
					put("geocode", name);
					put("format", "json");
				}
			}).getGeoObjects();

			if (_cache != null)
				_cache.cache(cacheKey,
						_gson.toJson(response),
						Conf.GEOCODE_CACHE_SEC);

			return response;
		}
	}

	@Override
	public void getObjectsAsync(String name, Callback<List<GeoObject>> callback) {

		String cacheKey = String.format("%s%s", Conf.YANDEX_GEOCODE_CACHE_PREFIX, name);

		if (_cache != null && _cache.exists(cacheKey)) {
			callback.success(getCachedItems(cacheKey), null);
		} else {

			_geocodeApi.geocodeAsync(new HashMap<String, String>() {
				{
					put("geocode", name);
					put("format", "json");
				}
			}, new Callback<RealmYandexGeocode>() {
				@Override
				public void success(RealmYandexGeocode realmYandexGeocode, Response response) {
					if (_cache != null)
						_cache.cache(cacheKey,
								_gson.toJson(response),
								Conf.GEOCODE_CACHE_SEC);
					callback.success(realmYandexGeocode.getGeoObjects(), response);
				}

				@Override
				public void failure(RetrofitError error) {
				}
			});

		}
	}

	@Override
	public List<GeoObject> getObjects(float lat, float lng) {

		String cacheKey = String.format(Locale.ENGLISH, "%s%f,%f", Conf.YANDEX_GEOCODE_CACHE_PREFIX, lng, lat);

		if (_cache != null && _cache.exists(cacheKey)) {
			return getCachedItems(cacheKey);
		} else {
			List<GeoObject> response = _geocodeApi.geocode(new HashMap<String, String>() {
				{
					put("geocode", String.format(Locale.ENGLISH, "%f,%f", lng, lat));
					put("format", "json");
				}
			}).getGeoObjects();

			if (_cache != null)
				_cache.cache(cacheKey,
						_gson.toJson(response),
						Conf.GEOCODE_CACHE_SEC);

			return response;
		}
	}

	@Override
	public List<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest)
			throws GeoProviderException {

		String cacheKey = String.format(Locale.ENGLISH, "%s%f,%f,%f,%f", Conf.YANDEX_GEOCODE_CACHE_PREFIX, latOrigin,
				lngOrigin, latDest, lngDest);

		if (_cache != null && _cache.exists(cacheKey)) {
			return _gson.fromJson(_cache.get(cacheKey), ArrayList.class);
		} else {

			String token = getToken();

			if (token == null)
				throw new GeoProviderException("Cannot get token");

			RealmYandexRouteResponse r = _mapsApi.getRoute(new HashMap<String, String>() {
				{
					put("rll", String.format(Locale.ENGLISH, "%f,%f~%f,%f", lngOrigin, latOrigin, lngDest, latDest));
					put("token", token);
					put("lang", Conf.YANDEX_LANG);
				}
			});

			if (r == null)
				throw new GeoProviderException("Outer geoservice troubles");

			List<GeoRoute> response = r.getGeoRoutes(latOrigin, lngOrigin, latDest,  lngDest);

			if (_cache != null)
				_cache.cache(cacheKey,
						_gson.toJson(response),
						Conf.ROUTE_CACHE_SEC);

			return response;
		}

	}

	@Override
	public String getLocationMeta(float lat, float lng, String locationType) {

		RealmYandexGeocode response = _geocodeApi.geocode(new HashMap<String, String>() {
			{
				put("geocode", String.format(Locale.ENGLISH, "%f,%f", lng, lat));
				put("format", "json");
			}
		});

		if (response != null) {
			for (RealmYandexFeatureMember r : response.getResponse().getGeoObjectCollection().getFeatureMember()) {
				try {
					String meta = r.getGeoObject().getMetaDataProperty().getGeocoderMetaData().getKind();
					if (Conf.YANDEX_DADATA_KINDS.containsKey(meta) &&
							Conf.YANDEX_DADATA_KINDS.get(meta).equals(locationType)) {

						// if it's area then go some hardcode
						if (locationType.equals("area") || locationType.equals("region")) {
							return r.getGeoObject().getName().split(" ")[0];
						}

						return r.getGeoObject().getName();
					}
				} catch (NullPointerException ex) {
				}
			}
		}

		return null;

	}
}
