package main

import com.thinkaurelius.titan.core._
import com.thinkaurelius.titan.core.util.TitanCleanup
import org.apache.commons.configuration.BaseConfiguration

import com.tinkerpop.blueprints._
import com.tinkerpop.blueprints.impls.tg.TinkerGraph
import com.tinkerpop.blueprints.{Graph,Vertex,Edge,Direction}
import com.tinkerpop.blueprints.Direction._

import scala.collection.JavaConverters._
import scala.concurrent._

object LazyInsert {

  /** Parallel Read-Write **/
  def insertUpdate() {
    val insertEdgeFuture: Future[List[Edge]] = Future[List[Edge]] {

      val readFuture: Future[List[Vertex]] = Future[List[Vertex]] {
      }

      val insertVertFuture: Future[List[Vertex]] = Future[List[Vertex]] {
      }

      // Return list
      readFuture.await :: instertVertFuture.await :: List()
    }

    insertEdgeFuture.await
  }

  /** Show All Vertices **/
  def showAllVertices(g: TitanGraph) = {
    for (v <- g.query.vertices.asScala) {
      println(v)
    }
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

    // Show everything that exists
    // showAllVertices(x)

    // Create new transaction
    val t = x.newTransaction

    val a = t.addVertex(null)
    val b = t.addVertex(null)
    val c = t.addVertex(null)
    val d = t.addVertex(null)
    val X = t.addEdge(null, a, b, "whatever")

    t commit

    var i = 0
    
    for (e <- x.query.vertices.asScala) {
      i = i + 1
    }

    // Close connection
    println("count = " + i)

    // t.commit
    x.shutdown

    // Clear the graph
    TitanCleanup.clear(x)
  }
}
