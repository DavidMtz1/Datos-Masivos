// Importe de librerias 
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

// Minimizar Errores
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Creacion sesion spark
val spark = SparkSession.builder().getOrCreate()

// Carga del Data
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

// Creacion de label indexer para comprarar 
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(data)

// Generamos un vector donde se almacenara las caracteristicas del dataset a evaluar y se aguardan mediante la columna featuresLabel
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("featuresLabel")
val featuresLabel = assembler.transform(data)

// Identifica categoricamente nuestro dataset en vector 
val featureIndexer = new VectorIndexer().setInputCol("featuresLabel").setOutputCol("indexedFeatures").setMaxCategories(4).fit(featuresLabel)

val Array(trainingData, testData) = featuresLabel.randomSplit(Array(0.7, 0.3))

// Creacion objeto DecisionTree
val DecisionTree = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// Prediccion
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Juntamos los datos en un pipeline
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, DecisionTree, labelConverter))

// Creacion modelo de entrenamiento
val model = pipeline.fit(trainingData)

//Transformacion de datos en el modelo
val predictions = model.transform(testData)

//Desplegamos predicciones
predictions.select("predictedLabel", "y", "featuresLabel").show(5)

//Evaluacion de la exactitud
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")

// imprimimos el tiempo de ejecucion
val TimeEjec = System.nanoTime
val duration = (System.nanoTime - TimeEjec) / 1e9d
