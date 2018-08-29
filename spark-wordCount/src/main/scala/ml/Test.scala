package ml

import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.Vectors

object Test {

  def main(args: Array[String]): Unit = {
    //创建密集向量
    val dv: Vector = Vectors.dense(1.0, 0.0, 3.0)
    //创建稀疏向量
    val su1: Vector = Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0))
    //创建稀疏向量
    val su2: Vector = Vectors.sparse(3, Seq((0, 1.0), (2, 3.0)))

  }
}
