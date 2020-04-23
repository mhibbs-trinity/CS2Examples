package cs2.net

import java.io._
import java.net._
import java.lang.Thread
import scala.collection.mutable.Buffer

object NetworkingExamples {

  def simpleServerSocket() {
    val ss = new ServerSocket(50000)
    val sock = ss.accept
    val dos = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream))
    for(i <- 0 until 1000 by 25) {
      println(">>Sending: " + i)
    	dos.writeInt(i)
    }
    dos.flush
    dos.close
    sock.close
    println(">>Closed")
    ss.close
  }
  
  def simpleClientSocket() {
    val sock = new Socket("localhost",50000)
    val dis = new DataInputStream(new BufferedInputStream(sock.getInputStream))
    while(dis.available == 0) Thread.sleep(10) //Waits for connection to begin
    //Once data packets are available, read until the stream empties
    while(dis.available > 0) {
      println("<<Received: " + dis.readInt)
    }
    dis.close
    sock.close
    println("<<Closed")
  }
  
  
  def getPageSource(url:String):String = {
  	val trin = new URL(url)
    val bis = new BufferedInputStream(trin.openStream)
    var result = ""
    while(bis.available > 0) {
      result += bis.read.toChar
    }
    result
  }
  
  def main(args:Array[String]) {
    
    (new Thread { override def run() { simpleServerSocket }}).start
    Thread.sleep(1000)
    (new Thread { override def run() { simpleClientSocket }}).start
    
    println(getPageSource("http://www.trinity.edu"))
    
  }
  
}
