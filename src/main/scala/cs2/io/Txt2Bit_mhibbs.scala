package cs2.io

import scala.collection.parallel._
import java.io._

object Txt2Bit {
	
	def textTo2bitStreams(in:DataInputStream, out:DataOutputStream) {
	  try {
	  	var byte:Byte = 0
	  	var posCtr = 8
		  while(in.available > 0) {
		    val c = in.readByte.toChar
		    var bits:Byte = if(c=='T') 0 else if(c=='C') 1 else if(c=='A') 2 else if(c=='G') 3 else {
		      //println("ERROR: Unexpected character encountered: " + c + ", skipping")
		      -1
		    }
		    if(bits >= 0) {
//				println("writing at " + posCtr + ": " + bits + " shifted to : " + (byte + (bits << posCtr)).toByte)
			    posCtr -= 2
			    byte = (byte + (bits << posCtr)).toByte
			    if(posCtr == 0) {
			      out.writeByte(byte)
			      posCtr = 8
			      byte = 0
			    }
		    }
		  }
		  if(posCtr != 8) out.writeByte(byte)
	  } catch {
	    case ex:IOException => println("IO Error: " + ex)
	  }
	}
	
	def textTo2bitFiles(inname:String) {//, outname:String) {
	  var in:DataInputStream = null; var out = new DataOutputStream(System.out) //:DataOutputStream = null
	  try {
	    in = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(inname))))
	    //out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(outname))))
	    textTo2bitStreams(in,out)
	  } catch {
	    case ex:FileNotFoundException => println("File not found: " + ex)
	  } finally {
	    if(in != null) in.close
	    if(out != null) { out.flush; out.close }
	  }
	}

	def bitsToTextStreams(in:DataInputStream, out:DataOutputStream) {
	  try {
		  while(in.available > 0) {
		    val c = in.readByte
		    for(i <- 6 to 0 by -2) {
		      val bits = (c & (0x03 << i)) >> i
		      if(bits == 0) out.writeByte('T')
		      else if(bits == 1) out.writeByte('C')
		      else if(bits == 2) out.writeByte('A')
		      else if(bits == 3) out.writeByte('G')
		      else println("ERROR: Unexcepted bits found: " + bits)
		    }
		  }
	  } catch {
	    case ex:IOException => println("IO Error: " + ex)
	  }
	}
	
	def bitsToTextFiles(inname:String) {
	  var in:DataInputStream = null; var out = new DataOutputStream(System.out)// = null
	  try {
	    in = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(inname))))
	    //out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(outname))))
	    bitsToTextStreams(in,out)
	  } catch {
	    case ex:FileNotFoundException => println("File not found: " + ex)
	  } finally {
	    if(in != null) in.close
	    if(out != null) { out.flush; out.close }
	  }
	}	

	def main(args:Array[String]) {
		if(args(0).contains(".txt")) {
			textTo2bitFiles(args(0))
		} else if (args(0).contains(".2bit")) {
			bitsToTextFiles(args(0))
		}
	}
	
}
