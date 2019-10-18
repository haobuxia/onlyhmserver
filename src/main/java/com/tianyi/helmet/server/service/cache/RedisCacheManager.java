package com.tianyi.helmet.server.service.cache;


import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 基于redis的缓存管理器
 *
 */
public class RedisCacheManager extends AbstractCacheManager {
    private final RedisConnectionFactory redisConnectionFactory;
    private RedisTemplate defaultTemplate;
    private final Map<String, RedisTemplate> templates;
    private boolean usePrefix = true;
    private RedisCachePrefix cachePrefix = new DefaultRedisCachePrefix();
    private boolean dynamic = false;
    private long defaultExpiration = 0L;
    private final Map<String, Long> expires;

    public RedisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.defaultTemplate = new RedisTemplate();
        this.defaultTemplate.setConnectionFactory(this.redisConnectionFactory);
        this.defaultTemplate.afterPropertiesSet();
        this.templates = new ConcurrentHashMap();
        this.expires = new ConcurrentHashMap();
    }

    public void setUsePrefix(boolean usePrefix) {
        this.usePrefix = usePrefix;
    }

    public void setCachePrefix(RedisCachePrefix cachePrefix) {
        this.cachePrefix = cachePrefix;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public void setDefaultExpiration(long defaultExpiration) {
        this.defaultExpiration = defaultExpiration;
    }

    public RedisCacheManager withCache(String cacheName, long expiration) {
        return this.withCache(cacheName, this.defaultTemplate, expiration);
    }

    public RedisCacheManager withCache(String cacheName, RedisTemplate template, long expiration) {
        this.templates.put(cacheName, template);
        this.expires.put(cacheName, Long.valueOf(expiration));
        RedisCache cache = this.createCache(cacheName, template, expiration);
        this.addCache(cache);
        return this;
    }

    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        return (Cache)(cache == null && this.dynamic?this.createCache(name, this.defaultTemplate, this.defaultExpiration):cache);
    }

    protected RedisCache createCache(String cacheName, RedisTemplate template, long expiration) {
        return new RedisCache(cacheName, this.usePrefix?this.cachePrefix.prefix(cacheName):null, template, expiration);
    }

    protected Collection<? extends Cache> loadCaches() {
        Assert.notNull(this.defaultTemplate, "A redis template is required in order to interact with data store");
        return (Collection)this.getCacheNames().stream().map(this::getCache).collect(Collectors.toList());
    }

    public void setCaches(Collection<RedisCacheInfo> caches) {
        if(caches != null) {
            Iterator var4 = caches.iterator();

            while(var4.hasNext()) {
                RedisCacheInfo cache = (RedisCacheInfo)var4.next();
                RedisTemplate redisTemplate = cache.getTemplate();
                Long expiration = cache.getExpiration();
                if(redisTemplate != null) {
                    this.withCache(cache.getName(), redisTemplate, expiration != null?expiration.longValue():this.defaultExpiration);
                } else {
                    this.withCache(cache.getName(), expiration != null?expiration.longValue():this.defaultExpiration);
                }
            }
        }

    }

    public RedisTemplate getDefaultTemplate() {
        return this.defaultTemplate;
    }

    public void setDefaultTemplate(RedisTemplate defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }
}

