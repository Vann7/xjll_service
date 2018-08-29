package com.van.demo.socket

import java.io._
import java.net.{ServerSocket, Socket}


object SendMsg {

//  def handlerRegisterMsg(username: String, password: String): ResultMsg = {
//    //对消息的处理
//    println("username: " + username + "password: " + password)
//    ResultMsg(1, "注册成功！！")
//  }
//
//  def handlerHeartbeat(hostname: String): ResultMsg = {
//    println(hostname + "当前的时间：" + System.currentTimeMillis())
//    ResultMsg(1, "心跳成功成功！！")
//  }

  def main(args: Array[String]): Unit = {
//    val socket = new Socket(InetAddress.getByName("localhost"), 8188)
    println("开始服务.....")
    val serverSocket: ServerSocket = new ServerSocket(9999)
    val clientSocket: Socket = serverSocket.accept()

    val inputStream = new DataInputStream(clientSocket.getInputStream)
    val br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))

    val outputStream = new DataOutputStream(clientSocket.getOutputStream)
    val bw = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))
    println("有客户端连接: " + clientSocket.getInetAddress.getHostName)
    var data: Array[Char] = new Array[Char](512)


      var msg = "123 223 323 111"

      while (true) {
        bw.write(msg)
        bw.flush()
        println(msg)
        Thread.sleep(1000)
      }

      //服务端要向客户端发送 结果消息

      bw.write(msg)
      bw.flush()
      println(msg)
      bw.close()
      br.close()
//      outputStream.writeUTF(msg)
//      outputStream.flush()

//    }

  }




}
