# Unit 3 KMeans exam

> 1. Import a simple Spark session.

```
import org.apache.spark.sql.SparkSession
```

> 2. Use the lines of code to minimize errors
```
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

> 3. Create an instance of the Spark session
```
val spark = SparkSession.builder().getOrCreate()
```

> 4. Import the Kmeans library for the grouping algorithm.
```
import org.apache.spark.ml.clustering.KMeans
```

> 5. Load the Wholesale Customers Data dataset
```
val df = spark.read.option("header","true").option("inferSchema","true").format("csv").load("Wholesale customers data.csv")
```
```
df.show()
```
![Alt text](images/df.show().png "Columns")

> 6. Select the following columns: Fres, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set feature_data
```
val feature_data = df.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen")
```
```
feature_data.show()
```
![Alt text](images/df.show().png "Feature Data")

> 7. Import Vector Assembler and Vectors
```
import org.apache.spark.ml.feature.VectorAssembler
```
```
import org.apache.spark.ml.linalg.Vectors
```

> 8. Create a new Vector Assembler object for feature columns as an input set, remembering that there are no labels

```
val assembler = new VectorAssembler().setInputCols(Array("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features")
```

> 9. Use the assembler object to transform feature_data
```
val training_data = assembler.transform(feature_data).select("features")
```

> 10.Create a Kmeans model with K = 3
```
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(training_data)
```

> 11. Evaluate the groups using Within Set Sum of Squared Errors WSSSE and print the centroids.
```
val WSSSE = model.computeCost(training_data)
println("Within Set Sum of Squared Errors = " + WSSSE)
```

> 12.Results
```
println("Cluster Centers").
model.clusterCenters.foreach(println)
```

