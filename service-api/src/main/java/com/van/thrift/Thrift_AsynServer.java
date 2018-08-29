package com.van.thrift;

import com.van.Service.LoginServiceImpl;
import com.van.Service.UserServiceImpl;
import com.van.thrift.user.LoginService;
import com.van.thrift.user.UserService;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class Thrift_AsynServer {

    //@Value("${server.port}")
    private int serverPort = 8083;


    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private LoginServiceImpl loginService;

    @PostConstruct
    public void init() {
        new Thread(){
           @Override
           public void run() {
               //startRPCServer();
               //rpcServer();
               multServer();
           }
        }.start();
    }

    public void startRPCServer() {
        try {
            // 设置协议工厂为 TBinaryProtocol.Factory
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
            // 关联处理器与 服务的实现
            TMultiplexedProcessor processor = new TMultiplexedProcessor();

            //非阻塞方式
            //TNonblockingServerTransport serverTransport;
            //serverTransport = new TNonblockingServerSocket(serverPort);

            TServerTransport t = new TServerSocket(serverPort);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
            processor.registerProcessor(UserService.class.getSimpleName(), new UserService.Processor<UserService.Iface>(userServiceImpl));
//         TSimpleServer server = new TSimpleServer(new Args(t).processor(processor));
            System.out.println("the serveris started and is listening at 8082...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }


    public void multServer() {
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(serverPort);

            TMultiplexedProcessor processor = new TMultiplexedProcessor();

            TProcessor user_processor = new UserService.Processor<UserService.Iface>(userServiceImpl);
            TProcessor longin_processor = new LoginService.Processor<LoginService.Iface>(loginService);
            processor.registerProcessor("user",user_processor);
            processor.registerProcessor("login",longin_processor);

            
            TJSONProtocol.Factory pro_factory = new TJSONProtocol.Factory();
            TFramedTransport.Factory tran_factory = new TFramedTransport.Factory();
            TProcessorFactory process_factory = new TProcessorFactory(processor);

            TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
            //TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(socket);
            args.processorFactory(process_factory);
            args.transportFactory(tran_factory);
            args.protocolFactory(pro_factory);

            TServer server = new TNonblockingServer(args);
            //TServer server = new TThreadedSelectorServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public void rpcServer() {
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(serverPort);
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor(UserService.class.getSimpleName(), new UserService.Processor<UserService.Iface>(userServiceImpl));
            //UserService.Processor processor = new UserService.Processor(userServiceImpl);
            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
            arg.protocolFactory(new TJSONProtocol.Factory());
            arg.transportFactory(new TFramedTransport.Factory());
            arg.processorFactory(new TProcessorFactory(processor));
            TServer server = new TNonblockingServer(arg);
            server.serve();
        }catch (TTransportException e) {
            e.printStackTrace();
        }
    }

}


