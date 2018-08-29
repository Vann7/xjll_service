import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object Rdd_2 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .set("spark.driver.host","localhost")
      .setMaster("local[2]")
      .setAppName("rdd_3")
    val sc = new SparkContext(conf)
    pair_rdd(sc)
    sc.stop()
  }




  def pair_rdd(sc: SparkContext) = {
    val lines = sc.textFile(System.getProperty("user.dir") + "/test/test2.txt")
    val words = lines.flatMap(x => (x.split(" ")))
    val word = words.map(x => (x, 1)).reduceByKey((x, y) => x+y)
    word.saveAsTextFile(System.getProperty("user.dir")+"/test/test-"+ System.currentTimeMillis())
//    println(word.collect().mkString(""))
  }


}
