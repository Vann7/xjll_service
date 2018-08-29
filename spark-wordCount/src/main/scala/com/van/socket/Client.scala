package com.van.demo.socket

import java.io._
import java.net.{InetAddress, Socket}


object client {

  def main(args: Array[String]): Unit = {
    val socket = new Socket("127.0.0.1", 9999)

    val outputStream = new DataOutputStream(socket.getOutputStream)
    val bw = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))

    val inputStream = new DataInputStream(socket.getInputStream)
    val br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))

    var data: Array[Char] = new Array[Char](1024)

    for (i <- 0 until  1) {
      val msg = "123"
      bw.write(msg,0,msg.length)
      bw.flush()
//      outputStream.writeUTF()
//      outputStream.flush()

      //接收服务端传送来的消息
//      val resultMsg = inputStream.readUTF()
      val len = br.read()
      val resultMsg = String.valueOf(data,0,len)
      println(" 我是客户端，我接收到了服务端传送过来的消息: " + resultMsg)

    }

//    //向服务端发送心跳
//    outputStream.writeUTF("client msg2")
//    outputStream.flush()
//
//    //接收服务端传送来的消息
//    val resultMsg2 = inputStream.readUTF()
//    println(" 我是客户端，我接收到了服务端传送过来心跳的结果: " + resultMsg2)

    outputStream.close()
    inputStream.close()
    socket.close()

  }

  }