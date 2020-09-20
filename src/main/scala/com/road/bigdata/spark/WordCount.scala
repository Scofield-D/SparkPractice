package com.road.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    // 使用idea开发word count

    // local模式
    // 创建SparkConf对象
    val config = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    val sc = new SparkContext(config)

    // 读取文件，将文件内容一行一行读取出来
    val lines: RDD[String] = sc.textFile("input")

    // 将一行数据分解成一个一个的单词
    val words: RDD[String] = lines.flatMap(_.split(" "))

    // 为了统计方便，将单词数据进行结构的变换
    val wordsToOne: RDD[(String, Int)] = words.map((_, 1))

    // 对转换结构后的数据进行分组聚合
    val wordToSum: RDD[(String, Int)] = wordsToOne.reduceByKey(_+_)

    // 将统计结果打印控制台
    val result: Array[(String, Int)] = wordToSum.collect()
    println(result)  // [Lscala.Tuple2;@736048ed 打印的是两元组数组的地址

    result.foreach(println)

//    println(sc)

  }

}
