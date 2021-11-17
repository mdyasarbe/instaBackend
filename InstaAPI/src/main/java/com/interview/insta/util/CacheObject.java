package com.interview.insta.util;

import java.util.HashMap;

import com.interview.insta.exception.NullValueException;

class CacheObject {
    public String index;
    public volatile HashMap<String, Object> cache = new HashMap<String, Object>();

    private long expiryTime;

    CacheObject(String newindex) throws Exception {
        if (newindex != null) {
            this.index = newindex;
        } else {
            throw new NullValueException("Null Value ", "Null key Received at CacheObject Initialisation");
        }
    }
// 2 3

    public void insertCache(String key,Object data) {
        try {
            cache.put(key, data);

        }catch (Exception ex) {
            throw(ex);
        }

    }
    public boolean hasCache(String key){

        return this.cache.containsKey(key);
    }

    public  Object getCache(String key) {

        return this.cache.get(key);
    }

    public  void deleteCache( String key) {

        if(key!=null && cache.containsKey(key)){

            cache.remove(key);

        }
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }

    public void updateCache(String key, Object data) {
    }
}