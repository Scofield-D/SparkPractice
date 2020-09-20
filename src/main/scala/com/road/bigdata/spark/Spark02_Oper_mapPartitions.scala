package com.road.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Oper_mapPartitions {
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

    // mapPartitions可以对一个RDD中所有的分区进行遍历
    // mapPartitions的效率优于map，减少了发送到excutor执行器的次数
    // mapPartitions类似于批处理，一次发送一个分区的数据到对应的执行器执行
    // mapPartitions可能出现内存溢出（OOM），因为批量的分区数据在excutor内计算完毕之前不会被释放
    // map每条数据都要执行网络传输
    val mapPartitionsRDD: RDD[Int] = listRDD.mapPartitions(datas => {
      datas.map(_ * 2)
    })
    mapPartitionsRDD.collect().foreach(println)

  }
}
