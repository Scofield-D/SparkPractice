package com.road.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_Oper_filter {
  def main(args: Array[String]): Unit = {

    // 创建SparkConf对象
    // local模式
    val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)
    // 1.从内存中创建， 底层实现就是 parallelize
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10, 3)

    // 保留符合条件的数据（i%2==0）
    val filterRDD: RDD[Int] = listRDD.filter(i => i%2==0)

    filterRDD.collect().foreach(println)

  }
}
