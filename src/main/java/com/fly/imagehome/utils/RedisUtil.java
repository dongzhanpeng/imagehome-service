package com.fly.imagehome.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Classname RedisUtil
 * @Description redis工具类
 * @Date 2021/12/9 11:12
 * @Author Fly
 * @Version 1.0
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        ops.set(key, value);
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        return (String) ops.get(key);
    }

    public boolean expire(String key, long time) {
        try {
            if (time > 0L)
                this.stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getExpire(String key) {
        return this.stringRedisTemplate.getExpire(key, TimeUnit.SECONDS).longValue();
    }

    public boolean hasKey(String key) {
        try {
            return this.stringRedisTemplate.hasKey(key).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void del(String... key) {
        if (key != null && key.length > 0)
            if (key.length == 1) {
                this.stringRedisTemplate.delete(key[0]);
            } else {
                this.stringRedisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
    }

    public Object getObject(String key) {
        Object object = this.redisTemplate.boundValueOps(key).get();
        return object;
    }

    public boolean setObject(String key, Object object, int time) {
        this.redisTemplate.opsForValue().set(key, object);
        if (time > 0)
            this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return true;
    }

    public String get(String key) {
        return (key == null) ? null : (String) this.stringRedisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, String value) {
        try {
            this.stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, String value, long time) {
        try {
            if (time > 0L) {
                this.stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long incr(String key, long delta) {
        if (delta < 0L)
            throw new RuntimeException("递增因子必须大于0");
        return this.stringRedisTemplate.opsForValue().increment(key, delta).longValue();
    }

    public long incr(String key) {
        return this.stringRedisTemplate.opsForValue().increment(key, 1L).longValue();
    }

    public long decr(String key, long delta) {
        if (delta < 0L)
            throw new RuntimeException("递减因子必须大于0");
        return this.stringRedisTemplate.opsForValue().increment(key, -delta).longValue();
    }

    public Object hget(String key, String item) {
        return this.stringRedisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return this.stringRedisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key, Map<String, String> map) {
        try {
            this.stringRedisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hmset(String key, Map<String, String> map, long time) {
        try {
            this.stringRedisTemplate.opsForHash().putAll(key, map);
            if (time > 0L)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item, String value) {
        try {
            this.stringRedisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item, String value, long time) {
        try {
            this.stringRedisTemplate.opsForHash().put(key, item, value);
            if (time > 0L)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void hdel(String key, Object... item) {
        this.stringRedisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key, Object item) {
        return this.stringRedisTemplate.opsForHash().hasKey(key, item).booleanValue();
    }

    public double hincr(String key, String item, double by) {
        return this.stringRedisTemplate.opsForHash().increment(key, item, by).doubleValue();
    }

    public double hdecr(String key, String item, double by) {
        return this.stringRedisTemplate.opsForHash().increment(key, item, -by).doubleValue();
    }

    public Set<String> sGet(String key) {
        try {
            return this.stringRedisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean sHasKey(String key, String value) {
        try {
            return this.stringRedisTemplate.opsForSet().isMember(key, value).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long sGetSetSize(String key) {
        try {
            return this.stringRedisTemplate.opsForSet().size(key).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public long setRemove(String key, Object... values) {
        try {
            Long count = this.stringRedisTemplate.opsForSet().remove(key, values);
            return count.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public List<String> lGet(String key, long start, long end) {
        try {
            return this.stringRedisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long lGetListSize(String key) {
        try {
            return this.stringRedisTemplate.opsForList().size(key).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public String lGetIndex(String key, long index) {
        try {
            return (String) this.stringRedisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean lSet(String key, String value) {
        try {
            this.stringRedisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, String value, long time) {
        try {
            this.stringRedisTemplate.opsForList().rightPush(key, value);
            if (time > 0L)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, List<String> value) {
        try {
            this.stringRedisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, List<String> value, long time) {
        try {
            this.stringRedisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0L)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lUpdateIndex(String key, long index, String value) {
        try {
            this.stringRedisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long lRemove(String key, long count, String value) {
        try {
            Long remove = this.stringRedisTemplate.opsForList().remove(key, count, value);
            return remove.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}