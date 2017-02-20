package com.lepsec.services.impl;

import com.lepsec.domain.LRU;
import com.lepsec.domain.impl.LRUImpl;
import com.lepsec.services.LRUService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jonatannietoa on 13/2/17.
 */
@Service
public class LRUServiceImpl implements LRUService{
    private LRU lru;
    private int nextKey;

    public LRUServiceImpl() {
        this.lru = LRUImpl.getInstance();
        this.nextKey=0;
    }

    @Override
    public int addStringInLRU(String string) {
        int newKey = getNewKey();
        lru.put(newKey,string);

        return newKey;
    }

    @Override
    public String getStringFromLRU(int key) throws NullPointerException {
        return lru.get(key);
    }

    @Override
    public Map<Integer,String> getLRUState() {
        return lru.getLRU();
    }

    private int getNewKey(){
        this.nextKey++;

        return nextKey;
    }
}
