package com.van.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.van.bean.Msg;
import org.springframework.stereotype.Service;

@Service("socketIOResponse")
public class SocketIOResponse {
    public void sendEvent(SocketIOClient client, Msg bean) {
        System.out.println("推送消息");
        client.sendEvent("OnMSG", bean);
    }
}