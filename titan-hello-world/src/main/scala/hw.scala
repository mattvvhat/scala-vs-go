package main

import com.thinkaurelius.titan.core._
import org.apache.commons.configuration.BaseConfiguration

import com.tinkerpop.blueprints._
import com.tinkerpop.blueprints.impls.tg.TinkerGraph
import com.tinkerpop.blueprints.{Graph,Vertex,Edge,Direction}
import com.tinkerpop.blueprints.Direction._

import scala.collection.JavaConverters._

object Hi {

  def len(iter :Iterable[Any]): Integer = {
    var i = 0
    for (e <- iter) {
      i = i+1
    }
    i
  }

  def clearGraph(graph :TitanGraph) {
  }

  def createVertex(graph: TitanGraph, name: String): Vertex = {
    val v = graph.addVertex(null)
    v.setProperty("name", name)
    v
  }

  /**
   * Return Vertex from Graph, and attach an unconnected vertex, if it doesn't exist
   */
  def getVertex(graph :TitanGraph, name :String) = {
    val response = graph.query.has("name", name).limit(1).vertices
    if (len(response.asScala) > 0) {
      response.asScala.head
    } else {
      createVertex(graph, name)
    }
  }

  /** Return or Create a Session Vertex **/
  def getSessionVertex(graph :TitanGraph, id :String) = {
    val result = graph.query.has("sessionId", id).limit(1).vertices.asScala
    if (len(result) > 0) {
      result.head
    } else {
      val v = graph.addVertex(null)
      v.setProperty("sessionId", id)
      graph.commit
      v
    }
  }

  /**
   * Create a new Node attached to ID
   */
  def createVertexOn(graph: TitanGraph, name: String, id: String): Vertex = {
    val response = graph.query.has("name", name).limit(1).vertices
    val newVertex = graph.addVertex(null)
    newVertex.setProperty("name", name)

    val oldVertex = getSessionVertex(graph, id)

    graoh.addEdge(null, oldVertex, newVertex, "sessionized")

    newVertex
  }

  /** Main **/
  def main(args: Array[String]) = {

    // Setup configuration
    val conf = new BaseConfiguration()
    conf.setProperty("storage.backend", "cassandra")
    conf.setProperty("storage.hostname", "127.0.0.1")
    conf.setProperty("storage.cassandra.keyspace", "dev_whatever")

    // Create a titangraph
    val x = TitanFactory.open(conf)

    // Create vertices
    // val a = createVertex(x, "Xolotl")

    val result1 = x.query.has("sessionId", "XXX3").vertices.asScala
    println(len(result1))

    getSessionVertex(x, "XXX3")
    getSessionVertex(x, "XXX3")
    getSessionVertex(x, "XXX3")

    val result2 = x.query.has("sessionId", "XXX3").vertices.asScala
    println(len(result2))


    var i = 0;

    for (v <- x.query.has("name", "Quetzalcoatl").vertices().asScala) {
      i=i+1;
      // x.addEdge(null, a, v, "is")
    }

    for (v <- x.query().has("name", "Quetzalcoatl").vertices.asScala) {
      // println(v.getEdges(OUT, "is").asScala)
    }

    // Query graph
    // val results = x.query().labels("knows").vertices().asScala

    // Close connection
    // trans.commit
    // trans.shutdown
    x.shutdown
  }
}
