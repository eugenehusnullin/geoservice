package service.geo.realm.yandex.route;

import java.util.List;

import service.geo.dto.Point;

public class RealmYandexRouteProperties {
	RealmYandexRouteRouteMetaData RouterMetaData;
	RealmYandexRouteMetaData SegmentMetaData;
	RealmYandexRouteMetaData PathMetaData;
	List<List<Float>> boundedBy;
	String encodedCoordinates;
	private static YandexRouteDecoder yandexRouteDecoder = new YandexRouteDecoder();

	public RealmYandexRouteRouteMetaData getRouterMetaData() {
		return RouterMetaData;
	}

	public void setRouterMetaData(RealmYandexRouteRouteMetaData routerMetaData) {
		RouterMetaData = routerMetaData;
	}

	public RealmYandexRouteMetaData getSegmentMetaData() {
		return SegmentMetaData;
	}

	public void setSegmentMetaData(RealmYandexRouteMetaData segmentMetaData) {
		SegmentMetaData = segmentMetaData;
	}

	public RealmYandexRouteMetaData getPathMetaData() {
		return PathMetaData;
	}

	public void setPathMetaData(RealmYandexRouteMetaData pathMetaData) {
		PathMetaData = pathMetaData;
	}

	public List<List<Float>> getBoundedBy() {
		return boundedBy;
	}

	public void setBoundedBy(List<List<Float>> boundedBy) {
		this.boundedBy = boundedBy;
	}

	public String getEncodedCoordinates() {
		return encodedCoordinates;
	}

	public void setEncodedCoordinates(String encodedCoordinates) {
		this.encodedCoordinates = encodedCoordinates;
	}

	public List<Point> getRoute() {
		return yandexRouteDecoder.decode(encodedCoordinates);
	}
}
