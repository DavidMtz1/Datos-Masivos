
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Sales.csv")

//1.sumDistinct
df.select(sumDistinct("Sales")).show()

//2.last
df.select(last("Company")).show()

//3.first
df.select(first("Person")).show()

//4.var_pop
df.select(var_pop("Sales")).show()

//5.avg
df.select(avg("Sales")).show()

//6.collect_list
df.select(collect_list("Sales")).show()

//7.var_samp
df.select(var_samp("Sales")).show()

//8.sum
df.select(sum("Sales")).show()

//9.stddev_pop
df.select(stddev_pop("Sales")).show()

//10.skewness
df.select(skewness("Sales")).show()

//11.min
df.select(min("Sales")).show()

//12.kurtosis
df.select(kurtosis("Sales")).show()

//13.collect_set
df.select(collect_set("Sales")).show()

//14.approx_count_distinct
df.select(approx_count_distinct("Company")).show()

//15.mean
df.select(mean("Sales")).show()\

//16.variance
df.select(variance ("Sales")).show()

//17.trunc
df.select(trunc("Sales")).show()

//18.bin
df.select(bin("Sales")).show()

//19.shuffle
df.select(shuffle("Sales")).show()

//20.abs
df.select(abs("Sales")).show()
