package com.aojing.redstore.goods.common;

import org.apache.catalina.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/14 12:03
 */
@Component
public class SearchHistoryAndAutoMatchs {

//    @Autowired
//    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     * 更新商品词库
     *
     * @param goodsName
     * @param storeName
     */
    public void updateDefaulList(String goodsName, String storeName) {
        String key = "all_key_words";

        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        ops.leftPush(key, goodsName);// 把该关键词放在最顶部（左边）
        ops.leftPush(key, storeName);// 把该关键词放在最顶部（左边）
    }

    /**
     * 更新搜索历史词库列表
     *
     * @param userId    用户id
     * @param searchkey 本次搜索关键词
     */
    public void updateList(Integer userId, String searchkey) {
        String key = "recent_search_" + userId;
        // 为了保证事务和性能，采用pipeline
        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        ops.remove(key, 1, searchkey);// 如果该关键词已存在先删除
        ops.leftPush(key, searchkey);// 把该关键词放在最顶部（左边）
        ops.trim(key, 0, 4);// 裁剪list 保留最近的5个关键词
    }

    /**
     * 从搜索历史列表中获取匹配的列表
     *
     * @param userId
     * @param pre
     * @return
     */
    public List<String> getAutoMatchs(Integer userId, String pre) {
        String key = "recent_search_" + userId;
        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        List<String> all = ops.range(key, 0, -1);// 获取该用户对应的“搜索历史列表”
        if (all == null) {
            return null;
        }
        if (pre != null && pre.length() > 0) {
            List<String> matchList = new ArrayList<String>();
            for (String one : all) {
                // 前缀匹配
                if (one.startsWith(pre)) {
                    matchList.add(one);
                }
            }
            return matchList;// 返回匹配到的“搜索历史列表”
        } else {
            return all;// 用户还没有输入，就返回所有的“搜索历史列表”
        }
    }

    /**
     * 从词库列表中获取匹配的列表
     *
     * @param pre
     * @return
     */
    public List<String> getDefaultAutoMatchs(String pre) {
        String key = "all_key_words";
        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        List<String> all = ops.range(key, 0, -1);//获取“关键词词库列表”
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        if (pre != null && pre.length() > 0) {
            List<String> matchList = new ArrayList<String>();
            for (String one : all) {
                //前缀匹配
                if (one.indexOf(pre)!=-1) {
                    matchList.add(one);
                }
            }
            return matchList;//返回匹配到的“关键词词库列表”
        } else {
            return null;//用户还没有输入，就返回所有的“关键词词库列表”
        }
    }

    public static void main(String[] args) {
        String aa = "test";
        List<String> test1 = Arrays.asList("test1", "123","test2","3test");
        List<String> matchList = new ArrayList<String>();
        for (String s : test1) {
            int i = s.indexOf(aa);
            if (i != -1) {
                matchList.add(s);
            }
        }
        System.out.println(matchList);
    }
}

