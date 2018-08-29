package com.van.demo.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * To run this on your local machine, you need to first run a Netcat server
  * *   $ nc -lk 9999
  */
object NetworkWC {

  val dir = "/Users/van/Desktop/output/1"

  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Usage: NetworkWC <host> <port>")
      System.exit(1)
    }

    val sparkConf = new SparkConf().setMaster("local").setAppName("wc")

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)

    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.saveAsTextFiles(dir)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()

  }

}
