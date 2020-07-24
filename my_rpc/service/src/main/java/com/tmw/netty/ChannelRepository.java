package com.tmw.netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TMW
 * @date 2020/7/23 16:25
 */
public class ChannelRepository {

    private final Map<String, Channel> channelCacheMap = new ConcurrentHashMap<>();

    public ChannelRepository put(String key,Channel channel){
        channelCacheMap.put(key,channel);
        return this;
    }

    public Channel get(String key){
        return channelCacheMap.get(key);
    }

    public void remove(String key){
        this.channelCacheMap.remove(key);
    }

    public int size(){
        return channelCacheMap.size();
    }

}
