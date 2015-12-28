package org.ligson.coderstar2.system.redis.utils;

import org.ligson.coderstar2.system.data.SerializeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.UnsupportedEncodingException;

/**
 * Created by ligson on 2015/12/28.
 * redis client
 */
public class RedisClient {
    private static Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private ShardedJedis shardedJedis;
    private ShardedJedisPool shardedJedisPool;

    public void init() {
        try {
            shardedJedis = shardedJedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("连接redis失败:{}", e);
        }
    }

    public void set(String key, Object value) {
        byte[] keyBuffer = null;
        try {
            keyBuffer = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        byte[] buffer = SerializeTool.serialize(value);
        shardedJedis.set(keyBuffer, buffer);
    }

    public <T> T get(String key, Class<T> clazz) {
        byte[] keyBuffer = null;
        try {
            keyBuffer = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        byte[] buffer = shardedJedis.get(keyBuffer);
        return SerializeTool.deserialize(buffer, clazz);
    }

    public ShardedJedis getShardedJedis() {
        return shardedJedis;
    }

    public void setShardedJedis(ShardedJedis shardedJedis) {
        this.shardedJedis = shardedJedis;
    }

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }
}
