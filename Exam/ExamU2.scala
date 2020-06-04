import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.types._
import spark.implicits._
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.sql.DataFrame

// Load the dfdataset stored in LIBSVM format as a DataFrame.
val Spark= SparkSession.builder()getOrCreate()
val dfdataset = spark.read.format("csv").option("inferSchema","true").option("header","true").csv("iris.csv")
dfdataset.show()


dfdataset.printSchema()
//5. Usa el método describe () para aprender más sobre los datos del DataFrame. 
dfdataset.describe().show()

//4. Imprime las primeras 5 columnas. 
dfdataset.columns

//Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar. 
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
val label = new StringIndexer().setInputCol("species").setOutputCol("label")

val featureSet = assembler.transform(dfdataset)
val dataindex = label.fit(featureSet).transform(featureSet)

// Split the dfdataset into train and test
val splits = dataindex.randomSplit(Array(0.6,0.4), seed=1234L)
val train = splits(0)
val test = splits(1)

// specify layers for the neural network:   input layer of size 4 (features), two intermediate of size 5 and 4 and output of size 3 (classes)
val layers = Array[Int](4, 5, 4, 3)

// create the trainer and set its parameters
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// train the model
val model = trainer.fit(train)
val result = model.transform(test)
result.show()

//Mostramos el resultado
val predictions = model.transform(test)

// compute accuracy on the test set
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")

