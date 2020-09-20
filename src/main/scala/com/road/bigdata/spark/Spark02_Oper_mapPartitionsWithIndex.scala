package com.road.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_Oper_mapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {

    // 使用idea开发wordcount
    // 创建SparkConf对象
    // local模式
    val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)
    // 1.从内存中创建， 底层实现就是 parallelize
    // 1 until 10 [1, 10)
    // 1 to 10    [1, 10]
    var listRDD: RDD[Int] = sc.makeRDD(1 to 10)

    // listRDD.mapPartitionsWithIndex() 当参数较多是，可改用模式匹配的方式写
//    val indesRDD: RDD[Int] = listRDD.mapPartitionsWithIndex {
//      case (num, datas) => {
//        datas
//      }
//    }
//    indesRDD.collect().foreach(println)
    val indexRDD: RDD[(Int, String)] = listRDD.mapPartitionsWithIndex {
      case (num, datas) => {
        datas.map((_, "分区号：" + num))
      }
    }
    indexRDD.foreach(println)

  }
}
