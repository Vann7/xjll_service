package com.van.dashboard

import java.util.HashMap

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object KafkaWordCount {

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "ubuntu:9092, ubuntu2:9092",
    "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
    "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
//    "key.deserializer" -> classOf[StringDeserializer],
//    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_group_id_for_each_stream",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )
  val topics = Array("gender")

  val dir = "/Users/van/Desktop/output/1"


  implicit val dormats = DefaultFormats

  def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setAppName("WC").setMaster("local[2]")
      val sc = new StreamingContext(conf, Seconds(2))
      sc.checkpoint("file:///Users/van/Desktop/check")

      val lines = KafkaUtils.createDirectStream[String, String](
        sc,
        PreferConsistent,
        Subscribe[String, String](topics, kafkaParams)
      )


    val words = lines.map(record => (record.value()))
    val wordCounts = words.map( x => (x, 1)).reduceByKey(_ + _).foreachRDD(
      rdd => {
        if (!rdd.isEmpty) {
          rdd.foreach(record => {
            val producer = new KafkaProducer[String, String](initProps)

             //封装成Kafka消息，topic为"result"
            val message = new ProducerRecord[String, String]("result", record._1.toString, record._1 + ", "+ record._2.toString)
            println(message)
            producer.send(message)

          })
        }
      }
    )
    sc.start()
    sc.awaitTermination()
  }

  /**
    * kafka配置文件
    * @return
    */
  def initProps: HashMap[String, Object] = {
    val props = new HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "ubuntu:9092, ubuntu2:9092")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props
  }

}
