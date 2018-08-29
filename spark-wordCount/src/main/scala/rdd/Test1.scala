package com.van.rdd

import org.apache.spark.{SparkConf, SparkContext}

object Test1 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]")
      .setAppName("Test1")
      .set("spark.driver.host","localhost")
    val sc = new SparkContext(conf)
//    val rdd = sc.parallelize(1 to 10, 4)
//    rdd.collect()
//    println(rdd.partitions.size)

//    val collect = Seq((1 to 10, Seq("Master","Slave1")), (11 to 15, Seq("Slave2", "Slave3")))
//    println(collect)
//    val rdd = sc.makeRDD(collect)
//    println(rdd.collect().mkString(", "))
//    println(rdd.preferredLocations(rdd.partitions(0)))
//    println(rdd.preferredLocations(rdd.partitions(1)))

//    val rdd1 = sc.makeRDD(Array(("A", 0),("A", 3), ("B",2), ("B", 3), ("C", 1)),1)
//    println(rdd1.groupByKey().collect().mkString(", "))
//    println(rdd1.reduceByKey(_+_).collect().mkString(", "))
//    val rdd2 = rdd1.reduceByKey(_+_)
//    rdd2.saveAsTextFile("/Users/van/Desktop/msg")
    val pre_data = sc.textFile("/Users/van/Desktop/demo/ip/ip.txt")
    val rdd_1 = pre_data.map((line => line.trim.replace("|", " ").replace("||", " ").split(" ")))
    val rdd2 = rdd_1.map(x => (x(7), 1)).filter( _._1 !="").groupByKey().map(x => (x._1, x._2.reduce(_+_)))
//    val fields = pre_data.map(line => line.trim.replace("|", " ").replace("||", " ").split(" ").filter( _(7) != ""))
//    val data_tmp = fields.map(fields => (fields(7), 1)).groupByKey().map(x => (x._1, x._2.reduce(_+_)))
    val data_final = rdd2.map(x => (x._2, x._1)).sortByKey(false).map(x => (x._2, x._1)).collect()
    val array = data_final.mkString("\n");
    println(array)


//    val fields = pre_data.map((line => line.trim.replace("|", " ").replace("||", " ").split(" ")))
//    val pre_ann = fields.map(fields => (fields(9), 1)).filter(x => x._1 !="").groupByKey().map{ x => (x._1, x._2.reduce(_+_))}
//    val pre_sort = pre_ann.sortBy(x=> (x._2, x._1)).sortByKey(true)
//    println(pre_sort.collect().mkString("\n"))
//    pre_sort.saveAsTextFile("/Users/van/Desktop/msg")

  }

}
