package main

import com.tinkerpop.blueprints._
import com.tinkerpop.blueprints.impls.tg.TinkerGraph
import com.tinkerpop.blueprints.Graph
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.Edge
import com.tinkerpop.blueprints.Direction

// import scala.collection.JavaConversions._
// import scala.language.implicitConversions
// import scala.collection.convert.WrapAsScala.enumerationAsScalaIterator
import scala.collection.JavaConverters._

object Hi {
  def main(args: Array[String]) = {
    val g = new TinkerGraph()

    val a = g.addVertex(null);
    val b = g.addVertex(null);
    val c = g.addVertex(null);

    a.setProperty("name", "Quetzalcoatl")
    b.setProperty("name", "Tlaloc")
    c.setProperty("name", "Xolotl")

    val e = g.addEdge(null, a, b, "knows")
    val f = g.addEdge(null, a, c, "knows")

    val results = a.query().labels("knows").vertices().asScala

    for (result <- results) {
      println(a.getProperty("name"), "knows", result.getProperty("name"))
    }
  }
}
