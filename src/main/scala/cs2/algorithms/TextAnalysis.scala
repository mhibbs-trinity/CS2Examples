package cs2.algorithms

import scala.io.Source

object TextAnalysis {
  def wordsInFile(name: String): Unit = {
    val input = Source.fromFile(name)
    val lines = input.getLines()
    val wordSet = scala.collection.mutable.Set[String]()
    for (line <- lines) {
      var words = line.split("\\s+")
      words = words.map(_.filter(_.isLetter).toLowerCase)
      wordSet ++= words
    }
    for (word <- wordSet) {
      println(word)
    }
    println(wordSet.size)
  }

  def countWordsInFile(name: String): Unit = {
    val input = Source.fromFile(name)
    val lines = input.getLines()
    val wordMap = scala.collection.mutable.Map[String, Int]()
    for (line <- lines) {
      var words = line.split("\\s+")
      words = words.map(_.filter(_.isLetter).toLowerCase)
      for (word <- words) {
        if (wordMap.contains(word)) {
          wordMap(word) += 1
        } else {
          wordMap(word) = 1
        }
      }
    }
    for (word <- wordMap) {
      println(word)
    }
    println(wordMap.size)
  }

  def main(args: Array[String]): Unit = {
    val path = getClass.getResource("/tempest.txt")
    println(path.toExternalForm())
    Source.fromURL(path)
    //countWordsInFile(path.toExternalForm())
  }
}
