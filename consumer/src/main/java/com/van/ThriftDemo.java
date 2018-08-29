package com.van;

public class ThriftDemo {
    public static void main(String[] args){
        //TTransport transport;
        // int port=8082;
        // String host="172.18.3.126";
        // int timeout=6000;
        // TSocket socket = new TSocket(host,port,timeout);
        // transport = new TFramedTransport(socket);
        //
        // TProtocol protocol = new TBinaryProtocol(transport);
        //
        //UserService.Client userService = new UserService.Client(protocol);
        //LoginService.Client loginService = new LoginService.Client(protocol);
        //try {
        //    transport.open();
        //    loginService.test1();
        //    //userService.getList();
        //} catch (TTransportException e) {
        //    e.printStackTrace();
        //} catch (TException e) {
        //    e.printStackTrace();
        //}

        String value = "1，2";
        String[] list = value.split(",");
        System.out.println(list);
    }
}
