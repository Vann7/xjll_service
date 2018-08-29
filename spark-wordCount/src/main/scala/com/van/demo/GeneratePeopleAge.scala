package com.van.demo

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random



object GeneratePeopleAge {

//  val path = "/Users/van/Desktop/exercise/peopleage/peopleage.txt"
    val path = "hdfs://ubuntu:9000/people_age/peopleage.txt"
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("hdfs-wc")
    val sc = new SparkContext(conf)
//    val fileWriter = new FileWriter(new File(path),false)
    val array = new Array[String](100)
    val rand = new Random()
     for (i <- 1 to 100) {
//       fileWriter.write(i + " " + rand.nextInt(100))
//       fileWriter.write(System.getProperty("line.separator"))
       array(i-1) = i + " " + rand.nextInt(100)
     }
    val rdd = sc.parallelize(array)
    rdd.foreach(println)
    rdd.saveAsTextFile(path)
//    fileWriter.flush()
//    fileWriter.close()

  }
}
