<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <h3>Avaiable methods</h3>
    <p><b>/api/suggest?q={QUERY}[&c={COUNT}][&lat={LAT}&lng={LON}][&locations={LOC:street|city|area|region}&[locations_value={LOCV}]]</b> get suggestions for text address where QUERY - text request, COUNT (optional) - max returning objects (default 10).
      <i>Note: "geo_lat" "geo_lon" are set if c=1.</i> Optional you can set {LAT} and {LON} of object to set region of search.
      And also you can set filter of search {LOC}, default is <i>city</i>. Also you can provide text name of filter in {LOCV}</p>
    <p><b>/api/geocode?name={QUERY}</b> get list of objects by text address where QUERY - text request</p>
    <p><b>/api/geocode_reverse?lat={LATITUDE}&lng={LONGTITUDE}</b> get list of objects by coordinates where LATITUDE and LONGTITUDE is latitude and longtitude in english float format (e.g. 55.7525254 and 37.6074081)</p>
    <p><b>/api/route?lat_start={LATS}&lng_start={LNGS}&lat_end={LATE}&lng_end={LNGE}</b> get routes from point to point</p>
  </body>
</html>
