ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "mapr-test"
  )


val sparkVersion = "3.3.1"

resolvers +="maven repository " at "https://repository.mapr.com/maven/"
resolvers +="mapr repository " at "https://repository.mapr.com/nexus/content/repositories/releases/"
resolvers +="mapr-public repository " at "https://repository.mapr.com/nexus/content/groups/mapr-public"
resolvers +="jboss " at "https://repository.jboss.org/nexus/content/repositories/thirdparty-releases/"
resolvers +="conjars " at "https://repository.mapr.com/nexus/content/groups/mapr-public/conjars"
resolvers +="atlas " at "https://packages.atlassian.com/maven-3rdparty/"
libraryDependencies += ("net.java.dev.eval" % "eval" % "0.5-mapr" from "https://repository.mapr.com/nexus/content/groups/mapr-public/net/java/dev/eval/eval/0.5-mapr/eval-0.5-mapr.jar")
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.10"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.10"

libraryDependencies += ("org.apache.spark" %% "spark-sql" % sparkVersion).exclude("org.apache.hadoop", "hadoop-client-api")
libraryDependencies += ("org.apache.spark" %% "spark-core" % sparkVersion).exclude("org.apache.hadoop", "hadoop-client-api")
libraryDependencies += ("org.apache.spark" %% "spark-hive" % sparkVersion).exclude("org.apache.hadoop", "hadoop-client-api")
libraryDependencies += "org.apache.iceberg" %% "iceberg-spark-runtime-3.2" % "1.3.1"
//libraryDependencies += "org.apache.hive" % "hive-metastore" % "1.2.0-mapr-spark-MEP-6.3.2-2101"
//libraryDependencies += "org.apache.hive" % "hive-serde" % "1.2.0-mapr-spark-MEP-6.3.2-2101"
libraryDependencies += "com.mapr.hadoop" % "maprfs" % "6.1.1-mapr"
libraryDependencies += "org.apache.hadoop" % "hadoop-yarn-common" % "2.7.0-mapr-1808"
libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.11-mapr-1808"
libraryDependencies += "org.apache.hadoop" % "hadoop-auth" % "2.7.0-mapr-1808"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.7.0-mapr-1808"
libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.7.0-mapr-1808"

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  artifact.name + "_" + sv.binary + "-" + sparkVersion + "_" + module.revision + "." + artifact.extension
}