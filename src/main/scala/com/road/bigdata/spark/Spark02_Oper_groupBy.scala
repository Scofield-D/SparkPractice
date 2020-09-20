package com.road.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_Oper_groupBy {
  def main(args: Array[String]): Unit = {

    // 创建SparkConf对象
    // local模式
    val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)
    // 1.从内存中创建， 底层实现就是 parallelize
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10, 3)

    // 生成数据按照指定规则分组
    // 分组后的数据形成了对偶元组(k, v)， k表示分组的key,v表示分组的数组集合
    val groupByRDD: RDD[(Int, Iterable[Int])] = listRDD.groupBy(i => i%2)

    groupByRDD.collect().foreach(println)
  }
}
