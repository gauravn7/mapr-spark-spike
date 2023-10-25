package io.acceldata.lab

import org.apache.hadoop.fs.Path
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object MapRTest extends App {
  val warehouse_location = "/user/hive/warehouse"

//  System.setProperty("java.security.auth.login.config", "/Users/gaurav/Downloads/mapr/mapr.login.conf")

  val conf = new SparkConf()
    .setAppName("Samples")
    .set("hive.metastore.execute.setugi", "false")
    .set("hive.metastore.uris", "thrift://mapr-10-90-3-71.nip.io:9083")
    .set("fs.defaultFS", "maprfs://mapr-10-90-3-71.nip.io:7222")
    .set("fs.maprfs.impl", "com.mapr.fs.MapRFileSystem")
    .set("spark.sql.warehouse.dir", "maprfs://mapr-10-90-3-71.nip.io:7222/spark-warehouse")
    .set("spark.eventLog.enabled","false")
    //.set("fs.mapr.bailout.on.library.mismatch","false")
    //.set("spark.sql.hive.metastore.version","2.3.0")
    .set("spark.hadoop.metastore.catalog.default","hive")
  //.set("spark.sql.catalogImplementation","in-memory")
  //.set("spark.sql.hive.metastore.jars","/Users/shubhamsharma/Documents/github/ad-lab/lib/*")

  val spark = SparkSession.builder().master("local[*]")
    .appName("SparkByExamples")
    .config(conf)
    //.config("fs.mapr.bailout.on.library.mismatch", "false")
    // .config("spark.hadoop.fs.defaultFS", "maprfs:///")
    //.config("fs.maprfs.impl", "com.mapr.fs.MapRFileSystem")
    .enableHiveSupport()
    .getOrCreate()

  //spark.sparkContext.hadoopConfiguration.addResource(new Path("file:///Users/shubhamsharma/Downloads/hive-site.xml"))


  println(spark.sparkContext.sparkUser)


  spark.sparkContext.hadoopConfiguration.set("fs.maprfs.impl", "com.mapr.fs.MapRFileSystem")
  spark.sparkContext.hadoopConfiguration.set("fs.AbstractFileSystem.maprfs.impl", "com.mapr.fs.MFS")

  spark.sql("show tables").show()
  spark.sql("select count(*) from employee").show(false)

}