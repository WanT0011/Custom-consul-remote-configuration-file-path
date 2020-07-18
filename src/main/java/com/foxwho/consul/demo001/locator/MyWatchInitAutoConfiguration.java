//package com.foxwho.consul.demo001.locator;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.cloud.consul.config.ConfigWatch;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Field;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @author WangZhiJian
// * @since 2020/7/17
// */
//@Configuration
//public class MyWatchInitAutoConfiguration implements InitializingBean {
//    @Resource
//    ConfigWatch configWatch;
//    @Resource
//    MyLocator myLocator;
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Class<? extends ConfigWatch> configWatchClass = configWatch.getClass();
//        Field consulIndexes = configWatchClass.getDeclaredField("consulIndexes");
//        consulIndexes.setAccessible(true);
//        Object o = consulIndexes.get(configWatch);
//        if(o instanceof LinkedHashMap){
//            LinkedHashMap<Object, Object> map = (LinkedHashMap<Object,Object>)o;
//            System.out.println("map = " + map);
////        consulIndexes.setAccessible(true);
////        Class<?> consulIndexesType = consulIndexes.getType();
////        Method addMethod = consulIndexesType.getDeclaredMethod("put",String.class,Long.class);
//        LinkedHashMap<String, Long> myLocatorContextIndex = myLocator.getContextIndex();
//            System.out.println("myLocatorContextIndex = " + myLocatorContextIndex);
//            for(Map.Entry<String, Long> entry : myLocatorContextIndex.entrySet()){
//                map.put(entry.getKey(),entry.getValue());
//            }
//            System.out.println("insert success!!");
//            System.out.println("map = " + map);
//        }
//
//    }
//}
