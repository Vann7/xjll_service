package rdd

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

case class Msg(ip:String, location:String, num:Int)

object Test_SparkSQL {
  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setMaster("local[2]")
//      .setAppName("Test1")
//      .set("spark.driver.host","localhost")
//    val sc = new SparkContext(conf)
    val spark = SparkSession
         .builder()
        .appName("Spark Example")
        .master("local[2]")
        .config("spark.some.config.option", "some-value")
        .config("spark.driver.host","localhost")
        .getOrCreate()
      val sqlContext = spark.sqlContext;

    import spark.implicits._
    val lineRdd = spark.sparkContext.textFile("/Users/van/Desktop/demo/ip/ip.txt")
    val pre_Rdd = lineRdd.map((x => x.trim.replace("|", " ").replace("||", " ").split(" ")))
    val ipRDD = pre_Rdd.map(x => Msg(x(0),x(7), 1))
    val ipDF = ipRDD.toDF
//    ipDF.show()
//    ipDF.select(ipDF.col("location")).show(4)
//    ipDF.select("ip").show(4)
//    ipDF.groupBy("location").count().show()
    ipDF.createOrReplaceTempView("t_ip")
//    sqlContext.sql("select * from t_ip order by num desc limit 12").show()
    sqlContext.sql("desc t_ip").show() //显示表的schema信息

  }


}
