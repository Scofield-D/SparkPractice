package com.road.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_Oper_map {
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

    val mapRDD: RDD[Int] = listRDD.map(_*2)

    mapRDD.collect().foreach(println)

  }
}
