package main

import com.thinkaurelius.titan.core._
import org.apache.commons.configuration.BaseConfiguration

import com.tinkerpop.blueprints._
import com.tinkerpop.blueprints.impls.tg.TinkerGraph
import com.tinkerpop.blueprints.{Graph,Vertex,Edge,Direction}

import com.tinkerpop.rexster.client.{RexsterClientFactory,RexsterClient}

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

    // Create vertices
    val a = x.addVertex(null);
    val b = x.addVertex(null);
    val c = x.addVertex(null);

    // Set properties
    a.setProperty("name", "Quetzalcoatl")
    b.setProperty("name", "Tlaloc")
    c.setProperty("name", "Xolotl")

    // Create edges
    val e = x.addEdge(null, a, b, "knows")
    val f = x.addEdge(null, a, c, "knows")

    // Query graph
    val results = a.query().labels("knows").vertices().asScala

    // Close connection
    x.shutdown
  }
}
