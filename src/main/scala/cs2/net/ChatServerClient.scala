package cs2.net

import java.net._
import java.io._
import java.lang.Thread

object ChatServerClient {

  def createInputThread(sock:Socket):Thread = {
    new Thread {
      override def run() {
        val dis = new DataInputStream(new BufferedInputStream(sock.getInputStream))
        var msg:String = ""
        while(!sock.isClosed && msg != "EXIT\n") {
          msg = ""
          while(!sock.isClosed && dis.available == 0) Thread.sleep(10)
          var lastChar = ' '
          while(!sock.isClosed && dis.available > 0 && lastChar != '\n') {
            lastChar = dis.readByte.toChar
            msg += lastChar
          }
          println("<< " + msg)
        }
        dis.close
      }
    }
  }
  
  def createOutputThread(sock:Socket):Thread = {
    new Thread {
      override def run() {
        val dos = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream))
        val sis = new DataInputStream(new BufferedInputStream(System.in))
        var msg:String = ""
        while(!sock.isClosed && msg != "EXIT\n") {
          msg = ""
          while(!sock.isClosed && sis.available == 0) Thread.sleep(10)
          var lastChar = ' '
          while(!sock.isClosed && sis.available > 0 && lastChar != '\n') {
            lastChar = sis.readByte.toChar
            msg += lastChar
          }
          println(">> " + msg)
          if(!sock.isClosed) {
            dos.writeBytes(msg)
            dos.flush
          }
        }
        sis.close
        dos.close
      }
    }
  }
  
  
  def main(args:Array[String]) {
    if(args.length < 1) {
      println("USAGE for ChatServerClient: scala cs1321.ChatServer <server/client> [<hostname>] <port>")
      println("\t<server/client> - mode of operation -- either 'server' or 'client'")
      println("\t[<hostname>] - if in client mode, specify name of server")
      println("\t<port> - port number for connection")
      sys.exit(0)
    }
    if(args(0) == "server") {
      val ss = new ServerSocket(args(1).toInt)
      val sock:Socket = ss.accept
      val inThread = createInputThread(sock)
      val outThread = createOutputThread(sock)
      inThread.start
      outThread.start
      inThread.join
      outThread.join
      sock.close
      ss.close
    } else if (args(0) == "client") {
      val sock:Socket = new Socket(args(1), args(2).toInt)
      val inThread = createInputThread(sock)
      val outThread = createOutputThread(sock)
      inThread.start
      outThread.start
      inThread.join
      outThread.join
      sock.close
    } else {
      println("Invalid server/client argument. Stopping execution.")
      sys.exit(0)
    }
  }
}
