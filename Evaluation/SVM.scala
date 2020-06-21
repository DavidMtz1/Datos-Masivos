// Import of Libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.{Pipeline, PipelineStage}
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{SparkSession, SQLContext}
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler

// Minimization of errors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Creation sision de spark
val spark = SparkSession.builder().getOrCreate()

// Dataset loading
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

// Creation label indexerval labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(data)

// Vector assambler
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("featuresLabel")
val featuresLabel = assembler.transform(data)

val featureIndexer = new VectorIndexer().setInputCol("featuresLabel").setOutputCol("indexedFeatures").setMaxCategories(4).fit(featuresLabel)

val Array(trainingData, testData) = featuresLabel.randomSplit(Array(0.7, 0.3))

// Create DecisionTree objectval dt = new LinearSVC().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// Prediction branch
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

//Pipeline
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

val model = pipeline.fit(trainingData)


// Data transformation in the modelval predictions = model.transform(testData)

// display predictions
predictions.select("predictedLabel", "y", "featuresLabel").show(5)

// Accuracy evaluation
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")


val t1 = System.nanoTime
val duration = (System.nanoTime - t1) / 1e9d
