package com.van.socket;


import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.van.bean.Msg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("eventListener")
public class EventListener {

    @Resource(name = "clientCache")
    private SocketIOClientCache clientCache;

    @Resource(name = "socketIOResponse")
    private SocketIOResponse socketIOResponse;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("建立连接");
    }

    @OnEvent("onMSG")
    public void onSync(SocketIOClient client, Msg msg) {
        System.out.printf("收到消息-from: %s to %n",msg.getFrom(), msg.getTo());
        clientCache.addClient(client, msg);
        SocketIOClient ioClients = clientCache.getClient(msg.getTo());
        System.out.println("clientCache");
        if (ioClients == null) {
            System.out.println("你发送消息的用户不在线");
            return;
        }
        socketIOResponse.sendEvent(ioClients,msg);
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("关闭连接");
    }

}
