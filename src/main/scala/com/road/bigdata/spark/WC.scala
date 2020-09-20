package com.road.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WC {

  def main(args: Array[String]): Unit = {

    // 使用idea开发wordcount
    // 创建SparkConf对象
    // local模式
    val conf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    
    val sc = new SparkContext(conf)

    // 读取文件，将文件内容一行一行读取出来
    val lines: RDD[String] = sc.textFile("input")
    // 将一行数据分解成一个一个的单词
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 为了方便统计，将单词数据进行结构的转换
    var wordsToOne: RDD[(String, Int)] = words.map((_, 1))
    // 对转换后的结构数据进行分组聚合
    var wordToSum: RDD[(String, Int)] = wordsToOne.reduceByKey(_ + _)
    // 将统计结果进行展示
    var result: Array[(String, Int)] = wordToSum.collect()
    result.foreach(println)
  }


}
