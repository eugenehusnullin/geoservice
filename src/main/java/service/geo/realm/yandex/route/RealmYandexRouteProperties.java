package service.geo.realm.yandex.route;

import service.geo.dto.Point;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RealmYandexRouteProperties {
    RealmYandexRouteRouteMetaData RouterMetaData;
    RealmYandexRouteMetaData SegmentMetaData;
    RealmYandexRouteMetaData PathMetaData;
    List<List<Float>> boundedBy;
    String encodedCoordinates;

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

    public List<Point> getRoute(){
        return new YandexRouteDecoder().decode(encodedCoordinates);
    }
}

class YandexRouteDecoder{
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=".toCharArray();
    BigDecimal perc = new BigDecimal(1000000.0);

    private String getSymbolCode(char c){
        for (int i = 0; i < alphabet.length; i++) {
            if(alphabet[i] == c)
                return String.format("%6s", Integer.toBinaryString(i)).replace(" ", "0");
        }
        return null;
    }

    private String[] splitStringEvery(String s, int interval) {
        int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
        String[] result = new String[arrayLength];

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        } //Add the last bit
        result[lastIndex] = s.substring(j);

        return result;
    }

    private Long parseLong(String s){

        if(s.toCharArray()[0] == '0') {
            return Long.parseLong(s, 2);
        }else{
            return -1 * ((new Long(1) << (4*8)) - Long.parseLong(s, 2));
        }
    }

    private Long getCharsFromBinString(String binString){

        String r = "";

        List<String> strs = Arrays.asList(splitStringEvery(binString, 8));
        Collections.reverse(strs);

        for(String charBin : strs){
            r += charBin;;
        }

        return parseLong(r);

    }

    private List<Point> toCoords(List<Long> c){

        List<Point> ret = new ArrayList<>();
        ret.add(new Point(new BigDecimal(c.get(0)).divide(perc).floatValue(), new BigDecimal(c.get(1)).divide(perc).floatValue()));

        int ic = 0;

        for(int i = 2; i < c.size(); i++){
            if(i % 2 == 0) {

                if(i < c.size() - 1)
                    ret.add(new Point(
                            new BigDecimal(ret.get(ic).getLat()).add(new BigDecimal(c.get(i)).divide(perc)).floatValue(),
                            new BigDecimal(ret.get(ic).getLat()).add(new BigDecimal(c.get(i + 1)).divide(perc)).floatValue()
                    ));
                ic++;
            }
        }

        return ret;

    }

    public List<Point> decode(String encoded){

        String s = "";

        for(char c : encoded.toCharArray()){
            s += getSymbolCode(c);
        }

        ArrayList<String> codedRev = new ArrayList<>(Arrays.asList(splitStringEvery(s, 32)));
        ArrayList<Long> coded = new ArrayList<>();

        for(String c : codedRev){
            coded.add(getCharsFromBinString(c));
        }

        return toCoords(coded);

    }

}
