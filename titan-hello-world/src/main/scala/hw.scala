package main

import com.thinkaurelius.titan.core._
import org.apache.commons.configuration.BaseConfiguration

import com.tinkerpop.blueprints._
import com.tinkerpop.blueprints.impls.tg.TinkerGraph
import com.tinkerpop.blueprints.{Graph,Vertex,Edge,Direction}
import com.tinkerpop.blueprints.Direction._

import scala.collection.JavaConverters._

object Hi {

  def main(args: Array[String]) = {

    // Setup configuration
    val conf = new BaseConfiguration()
    conf.setProperty("storage.backend", "cassandra")
    conf.setProperty("storage.hostname", "127.0.0.1")
    conf.setProperty("storage.cassandra.keyspace", "dev_whatever")

    // Create a titangraph
    val x = TitanFactory.open(conf)
    // val trans = TitanFactory.open(conf).newTransaction

    // Create vertices
    val a = x.addVertex(null);

    // Set properties
    a.setProperty("name", "Quetzalcoatl")
    a.setProperty("n", 0)

    var i = 0;

    for (v <- x.query().has("name", "Quetzalcoatl").vertices().asScala) {
      i=i+1;
      x.addEdge(null, a, v, "is")
    }

    for (v <- x.query().has("name", "Quetzalcoatl").vertices.asScala) {
      println(v.getEdges(OUT, "is").asScala)
    }

    println("count = " + i);

    // Query graph
    // val results = x.query().labels("knows").vertices().asScala

    // Close connection
    // trans.commit
    // trans.shutdown
    x.shutdown
  }
}
