package com.ab.service.impl;

import com.ab.anotation.MyCache;
import com.ab.service.ICacheService;
import org.springframework.stereotype.Service;

/**
 * @classname: CacheServiceImpl
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/17、23:21
 */
@Service
public class CacheServiceImpl implements ICacheService {
    @Override
    @MyCache(time = 5)
    public String getString(String string) {
        return string + " 我爱你中国 ";
    }
}
