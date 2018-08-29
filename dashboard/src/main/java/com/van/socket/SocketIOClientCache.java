package com.van.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.van.bean.Msg;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Service("clientCache")
public class SocketIOClientCache {
    //String：EventType类型
    private Map<String,SocketIOClient> clients=new ConcurrentHashMap<String,SocketIOClient>();

    //用户发送消息添加
    public void addClient(SocketIOClient client,Msg msgBean){
        clients.put(msgBean.getFrom(),client);
    }

    //用户退出时移除
    public void remove(Msg msgBean) {
        clients.remove(msgBean.getFrom());
    }

    //获取所有
    public  SocketIOClient getClient(String to) {
        return clients.get(to);
    }
}
