package com.road.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_Oper_distinct {
  def main(args: Array[String]): Unit = {

    // 创建SparkConf对象
    // local模式
    val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)
    // 1.从内存中创建， 底层实现就是 parallelize
    val listRDD: RDD[Int] = sc.makeRDD(List(1,2,1,5,2,9,6,1))

    // 数据顺序被打乱，进行了shuffle
    // 将RDD中一个分区的数据打乱重组到其他不同的分区的操作，称之为shuffle过程
    // Spark的算子没有shuffle过程的性能比较快
//    val distinctRDD: RDD[Int] = listRDD.distinct()

    // 2个分区来接收计算后的结果集
    // 去重后数据减少，可以改变默认的分区数量
    val distinctRDD: RDD[Int] = listRDD.distinct(2)

//    distinctRDD.collect().foreach(println)
    distinctRDD.saveAsTextFile("output")
  }
}
