package com.interview.insta.util;

import java.util.HashMap;

import com.interview.insta.exception.NullValueException;

import org.springframework.stereotype.Component;

@Component
public class CacheMain {

    public static volatile HashMap<String, CacheObject> segment = new HashMap<String, CacheObject>();

    public static void cleanCache(){
        segment.clear();
    }

    public static void insertCache(String index, String key, Object data) throws Exception {

        try {
            if(data==null){
                throw new NullValueException("Null data","Null data at insert Cache  at CacheMain ");
            }
            if(key==null){
                throw new NullValueException("Null key","Null key at insert Cache at CacheMain ");
            }
            if(index==null){
                throw new NullValueException("Null index","Null key at insert Cache at CacheMain ");
            }

            if (segment.containsKey(index)) {
                CacheObject internalkey = segment.get(index);
                internalkey.insertCache(key,data);

            } else {

                CacheObject internalkey = new CacheObject(index);
                internalkey.insertCache(key,data);
                segment.put(index, internalkey);
            }
        } catch (NullValueException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    public static boolean hasCache(String index, String key){
        if(segment.containsKey(index)){
            return segment.get(index).hasCache(key);
        }
        return false;
    }

    
    public static Object getCache(String index, String key) {
        try {
            if(index==null){
                throw new NullValueException("Null index","Null index at get Cache at CacheMain ");
            }
            if(key==null){
                throw new NullValueException("Null key","Null key at get Cache at CacheMain ");
            }

            if (segment.containsKey(index)) {
                CacheObject internalkey = segment.get(index);
                return internalkey.getCache(key);
            }else{
                throw new NullValueException("No value for the index :"+index,"Null key at get Cache at CacheMain ");
            }


        } catch (NullValueException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return true;
    }

    public static void updateCache(String index, String key, Object data) {
        try {
            if(data==null){
                throw new NullValueException("Null data","Null data at update Cache  at CacheMain ");
            }
            if(key==null){
                throw new NullValueException("Null key","Null key at update Cache at CacheMain ");
            }
            if(index==null){
                throw new NullValueException("Null index","Null key at update Cache at CacheMain ");
            }

            if (segment.containsKey(index)) {
                CacheObject internalkey = segment.get(index);
                internalkey.updateCache(key,data);

            } else {

               throw new NullValueException("No value found with index"+index,"try inserting before update");
            }
        } catch (NullValueException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static void deleteCache(String index, String key) {
        if(index!=null && segment.containsKey(index)){
            CacheObject internalkey = segment.get(index);
            internalkey.deleteCache(key);

        }
    }

}
