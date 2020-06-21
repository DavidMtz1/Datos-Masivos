///Multilayer Perceptron Classifier
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.DateType
import org.apache.spark.sql.{SparkSession, SQLContext}
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Transformer
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.log4j._

// Minimiza errores
Logger.getLogger("org").setLevel(Level.ERROR)

// creamos una sesion simple de spark
val spark = SparkSession.builder().getOrCreate()

// creamos nuestro dataset
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

// transformamos datos binarios
val col1 = df.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val col2 = col1.withColumn("y",when(col("y").equalTo("no"),2).otherwise(col("y")))
val newcol = col2.withColumn("y",'y.cast("Int"))

// Generamos un vector donde se almacenara las caracteristicas del dataset a evaluar  se guardan mediante la columna features
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val featur = assembler.transform(newcol)


// transformamos la columnoa "y" en etiquetas
val colchange = featur.withColumnRenamed("y", "label")
val feat = colchange.select("label","features")

// preparamos datos  de entrenamiento y de prueba
// el conjunto de prueba: entrenamiento => 60%, prueba => 40% y semilla => 1234L 
val split = feat.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = split(0)
val test = split(1)

//Especificamos las capas de nuestra red neuronal, especificando las capas de entrada(5), capas intermedias(2,2) y capa de salida(4)
val layers = Array[Int](5, 2, 2, 4)

// creamos el entrenador MultilayerPerceptronClassifier y establezca sus parámetros
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Entrenamos el modelo
val model = trainer.fit(train)

// imprimimos los resultados
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
val accuracy = evaluator.evaluate(predictionAndLabels)
println(s"Test Error = ${(1.0 - accuracy)}")

// mostramos el tiempo de ejecucion
val time = System.nanoTime
val duration = (System.nanoTime - time) / 1e9d
