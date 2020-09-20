package com.road.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_Oper_simple {
  def main(args: Array[String]): Unit = {

    // 创建SparkConf对象
    // local模式
    val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)
    // 1.从内存中创建， 底层实现就是 parallelize
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10)
    // 以指定的随机种子随机抽样出数量为fraction的数据，
    // withReplacement表示是抽出的数据是否放回，true为有放回的抽样，false为无放回的抽样，
    // seed用于指定随机数生成器种子。（抽取的开始位置）
    // val simpleRDD: RDD[Int] = listRDD.sample(true, 1, 1)  fraction=1表示全部满足
    // val simpleRDD: RDD[Int] = listRDD.sample(true, 0, 1)  fraction=0表示全部不满足
    val simpleRDD: RDD[Int] = listRDD.sample(true, 0.4, 1)

    // 伪随机数，fraction相同时，每次抽取结果一样
    simpleRDD.collect().foreach(println)

  }
}
