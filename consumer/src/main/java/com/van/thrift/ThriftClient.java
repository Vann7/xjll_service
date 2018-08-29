package com.van.thrift;


import com.van.thrift.user.LoginService;
import com.van.thrift.user.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.stereotype.Component;


@Component
public class ThriftClient {

    private int port=8082;
    //private String host="127.0.0.1";
    private String host="172.18.3.126";
    private int timeout=6000;

    private TTransport transport;
    private TProtocol protocol;
    private UserService.Client userService;
    private LoginService.Client loginService;

    public LoginService.Client getLoginService() {
        return loginService;
    }

    public UserService.Client getUserService() {
        return userService;
    }


    public void open() {
        try {
            if (transport.isOpen()) {
                return;
            }
            transport.open();

        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (transport.isOpen()){
            transport.close();
        }
    }


    public ThriftClient() {
        TSocket tSocket = new TSocket(host,port,timeout);
        //transport 定义 消息是怎样在客户端和服务 端之间通信的
        //其中 TFramedTransport 以帧为传输单位

        transport = new TFramedTransport(tSocket);
        //定义传输协议为Json格式
        //protocol = new TJSONProtocol(transport);
        protocol = new TBinaryProtocol(transport);



        //如果服务端使用TMultiplexedProcessor接收处理，客户端必须用TMultiplexedProtocol并且指定serviceName和服务端的一致
        //TMultiplexedProtocol user_protocol =new TMultiplexedProtocol(protocol,"user");
        //TMultiplexedProtocol login_protocol = new TMultiplexedProtocol(protocol,"login");
        //userService = new  UserService.Client(protocol);
        loginService = new LoginService.Client(protocol);
    }

    public TTransport getTransport() {
        return transport;
    }

    public void setTransport(TTransport transport) {
        this.transport = transport;
    }
}
