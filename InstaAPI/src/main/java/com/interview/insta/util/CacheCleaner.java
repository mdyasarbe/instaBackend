package com.interview.insta.util;

public class CacheCleaner implements Runnable {

    @Override
    public void run() {
        
        CacheMain.cleanCache();
    }
    
}
