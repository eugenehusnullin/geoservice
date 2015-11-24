package service.geo.cache;

import service.Conf;
import redis.clients.jedis.Jedis;

public class Redis implements CachingProvider {

    private Jedis jedis = new Jedis(Conf.REDIS_HOST);

    @Override
    public void cache(String key, String value) {
        jedis.set(key, value);
    }

    @Override
    public void cache(String key, String value, int secondsExpire){
        jedis.set(key, value);
        jedis.expire(key, secondsExpire);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public Boolean exists(String key) {
        return jedis.exists(key);
    }
}
