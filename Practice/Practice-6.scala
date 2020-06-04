
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("P2-Mispriced-Diamonds.csv")

//1.sumDistinct
df.select(sumDistinct("price")).show()

//2.last
df.select(last("price")).show()

//3.first
df.select(first("price")).show()

//4.var_pop
df.select(var_pop("price")).show()

//5.avg
df.select(avg("price")).show()

//6.collect_list
df.select(collect_list("clarity")).show()

//7.var_samp
df.select(var_samp("clarity")).show()

//8.sum
df.select(sum("clarity")).show()

//9.stddev_pop
df.select(stddev_pop("clarity")).show()

//10.skewness
df.select(skewness("clarity")).show()

//11.min
df.select(min("price")).show()

//12.kurtosis
df.select(kurtosis("price")).show()

//13.collect_set
df.select(collect_set("price")).show()

//14.approx_count_distinct
df.select(approx_count_distinct("price")).show()

//15.mean
df.select(mean("price")).show()\

//16.variance
df.select(variance ("clarity")).show()

//17.trunc
df.select(trunc("clarity")).show()

//18.bin
df.select(bin("clarity")).show()

//19.shuffle
df.select(shuffle("clarity")).show()

//20.abs
df.select(abs("clarity")).show()

