package com.van.thrift;


import com.van.thrift.user.LoginService;
import com.van.thrift.user.UserService;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;


//@Component
public class Thrift_AsynClient {

    private int port=8083;
    private String host="127.0.0.1";
    private int timeout=6000;


    private TNonblockingSocket transport;

    private UserService.AsyncClient userService_async;
    private LoginService.AsyncClient loginService_async;

    public LoginService.AsyncClient getLoginService_async() {
        return loginService_async;
    }

    public UserService.AsyncClient getUserService_async() {
        return userService_async;
    }

    public void open() {
        try {
            if (!transport.isOpen()) {
                transport.open();
            }
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (transport.isOpen()) {
            transport.close();
        }
    }


    public Thrift_AsynClient() {
        TProtocolFactory protocolFactory = new TJSONProtocol.Factory();
        try {
            TAsyncClientManager clientManager = new TAsyncClientManager();

            transport = new TNonblockingSocket(host, port);

            TJSONProtocol p = new TJSONProtocol(transport);

            TMultiplexedProtocol user_protocol = new TMultiplexedProtocol(p,"user");

            TMultiplexedProtocol login_protocol = new TMultiplexedProtocol(p, "login");



            userService_async = new UserService.AsyncClient(protocolFactory,clientManager,transport);

            loginService_async = new LoginService.AsyncClient(protocolFactory,clientManager,transport);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
