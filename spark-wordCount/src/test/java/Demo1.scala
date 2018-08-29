import org.apache.spark.{SparkConf, SparkContext}

object Demo1 {

  val path = System.getProperty("user.dir") + "/test/test.txt"
  val out  = System.getProperty("user.dir") +"/test/out-" +System.currentTimeMillis();

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wc").set("spark.driver.host","localhost")

    val sc = new SparkContext(conf)
    val lines = sc.textFile(path)

    val totalNum = lines.flatMap(line => line.split("\t").map(id => (id, 1))).reduceByKey(_+_)
    println(totalNum.count())
    println(totalNum.take(2))

    totalNum.saveAsTextFile(out)
    sc.stop()

  }

}
