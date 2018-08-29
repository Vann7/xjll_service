import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object Rdd_1 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host","localhost")
      .setMaster("local[2]")
      .setAppName("rdd_3")
    val sc = new SparkContext(conf)

//    countNum(sc)
//    filtNum(sc)
      flat(sc)
    sc.stop()
  }


  def countNum(sc : SparkContext) = {
    val rdd1 = sc.parallelize(List(1,2,3,4,5,6,7,8,9))

    val totalNum = rdd1.reduce((x,y) => x+y)
    println(totalNum)
  }

  def filtNum(sc: SparkContext):Unit ={
    val rdd_2 = sc.parallelize(List(1,2,3,4,5,6,7,8,9))
    val rdd_filter = rdd_2.filter(i => i >5)

    rdd_2.persist(StorageLevel.MEMORY_ONLY)
    println(rdd_2.count())
    println(rdd_filter.collect().mkString(" "))
  }


  def flat(sc : SparkContext): Unit = {
    val rdd_3 = sc.parallelize(List(("coffee panda"),("happy panda"), ("happiest panda party")))
    val result = rdd_3.flatMap(line => line.split(" "))
    println(result.collect().mkString(" "))
  }


}
