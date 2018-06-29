package com.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * Created by zhh on 2018/6/28 0028.
 */
public class JedisDemo1 {

    /**
     * 单示例的测试
     */
    @Test
    public void demo1() {
        // 1.设置ip地址和端口, 密码访问
        JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.221.122", 6379);
        jedisShardInfo.setPassword("090321");
        Jedis jedis = new Jedis(jedisShardInfo);
        // 2.保存数据
        jedis.set("name", "demo1");
        // 3.获取数据
        String name = jedis.get("name");
        System.out.println("name: " + name);
        // 4.释放资源
        jedis.close();
    }

    /**
     * 连接池方式连接
     */
    @Test
    public void demo2() {
        // 1.获得连接池的配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 2.设置最大连接数
        jedisPoolConfig.setMaxTotal(30);
        // 3.设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        // 4.获得连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.221.122", 6379, 7200, "090321");
        // 5.获得核心对象
        Jedis jedis = null;
        try {
            // 6.通过连接池获得连接
            jedis = jedisPool.getResource();
            // 7.设置数据
            jedis.set("name", "demo2");
            // 8.获取数据
            String name = jedis.get("name");
            System.out.println("name: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 9.释放资源
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }
}
