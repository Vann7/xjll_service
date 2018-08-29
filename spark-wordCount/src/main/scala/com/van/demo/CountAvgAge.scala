package com.van.demo

import org.apache.spark.{SparkConf, SparkContext}

object CountAvgAge {

//  val path = "/Users/van/Desktop/exercise/peopleage/peopleage.txt"

  val path = "hdfs://ubuntu:9000/people_age/peopleage.txt"
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("Count Average Age").set("spark.driver.host","localhost")
    val sc = new SparkContext(conf)
    val lines = sc.textFile(path, 3)

    val count = lines.count()
    val totalAge = lines.map(line => line.split(" ")(1)).map(t=>t.trim.toInt).collect().reduce((a,b)=> a+b)
    println("Total Age is: "+totalAge+"; Number of People is:"+count)
    val avgAge : Double = totalAge.toDouble / count.toDouble
    println("Average Age is:"+avgAge)
  }
}
