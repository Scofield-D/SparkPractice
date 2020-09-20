package com.road.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD {
    def main(args: Array[String]): Unit = {

      // 使用idea开发wordcount
      // 创建SparkConf对象
      // local模式
      val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")

      val sc = new SparkContext(conf)
      // 1.从内存中创建， 底层实现就是 parallelize
      var listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
      listRDD.collect().foreach(println)
      // 2.从内存中创建
      // 分区个数：主机内核数与2进行比较的最大值
      // conf.getInt("spark.default.parallesim", math.max(totalCoreCount.get(), 2)
      var arrayRDD: RDD[Int] = sc.parallelize(Array(1, 2, 3))
      arrayRDD.collect().foreach(println)

      // 3.从外部文件创建
      // 分区个数：主机内核数与2进行比较的最小值
      // defaultPartions: Int = math.min(defaultParallesim, 2)
      var fileRDD: RDD[String] = sc.textFile("input")
      // 读取文件时传递的分区参数为最小分区数，但是不一定是这个分区数，取决于hadoop读取文件时的分片规则
      var fileRDD2: RDD[String] = sc.textFile("input", 2)

      fileRDD.saveAsTextFile("output")
    }
}
