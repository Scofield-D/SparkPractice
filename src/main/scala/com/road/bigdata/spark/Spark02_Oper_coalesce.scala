package com.road.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_Oper_coalesce {
  def main(args: Array[String]): Unit = {

    // 创建SparkConf对象
    // local模式
    val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)
    // 1.从内存中创建， 底层实现就是 parallelize
    val listRDD: RDD[Int] = sc.makeRDD(1 to 16, 4)
    println("缩减分区前的分区数："+listRDD.partitions.size)

    // 缩减分区数，实际上时合并分区数，如将4号分区合并到了3号分区
    // 数据没有打乱重组，所以没有shuffle过程
    val coalesceRDD: RDD[Int] = listRDD.coalesce(3)
    println("缩减分区后的分区数："+coalesceRDD.partitions.size)
    coalesceRDD.saveAsTextFile("output")

  }
}
