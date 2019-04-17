package com.gsoft.cache.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsoft.cache.config.RedisConfiguration;
import com.gsoft.cache.entity.User;
import com.gsoft.cache.service.UserService;
import com.gsoft.cache.utils.RedisCache;
import com.gsoft.cache.utils.RedisCacheHelper;

/**
 * 用户service
 * @author liupantao
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	

	    @Autowired
	    RedisTemplate redisTemplate;

	    @Autowired
	    StringRedisTemplate stringRedisTemplate;
	    
	    @Autowired
	    RedisCacheHelper cacheHelper;
	    
	    @Autowired
	    RedisCache cacheList;

	    /**
	     * Logger
	     */
	    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	    @Override
		public String getHello(String name) {
			log.info("接收参数name -> [{}]", name);
			stringRedisTemplate.opsForValue().set("name", name,60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间  
			String value = stringRedisTemplate.opsForValue().get("name");//根据key获取缓存中的val
			log.info("获取参数name的value -> [{}]", value);
			return name;
		}
	
	    
	    @Override
	    public String query(Long id) {

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    System.out.println(sdf.format(new Date()) + " : query id is " + id);
         
	    User user=  new User();
	    user.setAge(11);
	    user.setName("张三");
	    
	    User user1=  new User();
	    user1.setAge(12);
	    user1.setName("李四");
	    
	    List<User> list= new ArrayList<>();
	    list.add(user);
	    list.add(user1);
	    list.add(user);
	    
	    long lGetListSize = cacheHelper.lGetListSize("user:list");
	    
	    log.info("Lget size 个数 --> [{}]",lGetListSize);
	    
	    //List<Object> lGet = cacheHelper.lGet("user:list", 0L, -1L);
	    List<User>  lUser= (List<User>) cacheHelper.lGetIndex("user:list", 0);
	    //List<User> lUser = (List<User>) lGet.get(0);
	    
	    for (User user2 : lUser) {
	    	log.info("存放Lget--> [{}]",user2.getName());
		}
	    
	    List range = cacheList.range("user:list", 0L, -1L);
	    log.info("获取list3 个数 --> [{}]",range.size());
	    
	    
	    if(range.size()<=0) {
	    	
	    	boolean set = cacheList.set("user:list", list);
	    	log.info("存放list--> [{}]",set);
	    }else {
	    	List<User>  lists= (List<User>) range.get(0);
	    	for (User user2 : lists) {
	    		log.info("存放list--> [{}]",user2.getName());
			}
	    	
	    }
	    
	    
	    return "123";

	    }

}
