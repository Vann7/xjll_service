package com.van.thrift;

import com.van.thrift.user.LoginService;
import com.van.thrift.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;
import org.springframework.stereotype.Component;


@Component
public class ServiceProvider {
    private int port=8082;
    private String host="172.18.3.126";
    //private String host="127.0.0.1";
    private int timeout=6000;

    private enum ServiceType {
        USER,
        LOGIN
    }

    public UserService.Client getUserService() {
        return getService(host,port,ServiceType.USER);
    }

    public LoginService.Client getLoginService() {
        return getService(host,port,ServiceType.LOGIN);
    }


    public <T> T getService(String ip, int port, ServiceType serviceType) {
        TSocket tSocket = new TSocket(host,port,timeout);
        //transport 定义 消息是怎样在客户端和服务 端之间通信的
        //其中 TFramedTransport 以帧为传输单位

      TTransport transport = new TFramedTransport(tSocket);
      try {
          transport.open();
      } catch (TTransportException e) {
          e.printStackTrace();
          return null;
      }
        //定义传输协议为Json格式
        TProtocol protocol = new TJSONProtocol(transport);
        //如果服务端使用TMultiplexedProcessor接收处理，客户端必须用TMultiplexedProtocol并且指定serviceName和服务端的一致
        TMultiplexedProtocol user_protocol =new TMultiplexedProtocol(protocol,"user");
        TMultiplexedProtocol login_protocol = new TMultiplexedProtocol(protocol,"login");
        TServiceClient result = null;

        switch (serviceType) {
            case USER:
                result = new UserService.Client(user_protocol);
                break;
            case LOGIN:
                result = new LoginService.Client(login_protocol);
                break;
        }
        return (T)result;
        //userService = new  UserService.Client(user_protocol);
        //loginService = new LoginService.Client(login_protocol);

    }
}
