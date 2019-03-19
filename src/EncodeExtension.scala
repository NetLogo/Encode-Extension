package org.nlogo.extension.encode

import java.lang.{ Double => JDouble }
import java.util.Base64

import org.nlogo.api.{ Argument, Context, DefaultClassManager, ExtensionException, PrimitiveManager, Reporter }
import org.nlogo.core.{ LogoList, Syntax }

class EncodeExtension extends DefaultClassManager {

  override def load(manager: PrimitiveManager): Unit = {
    manager.addPrimitive("base64-to-bytes", Base64ToBytes)
    manager.addPrimitive("bytes-to-base64", BytesToBase64)
    manager.addPrimitive("bytes-to-string", BytesToString)
    manager.addPrimitive("string-to-bytes", StringToBytes)
  }

  private object Base64ToBytes extends Reporter {
    override def getSyntax = Syntax.reporterSyntax(right = List(Syntax.StringType), ret = Syntax.ListType)
    override def report(args: Array[Argument], context: Context): AnyRef = {
      val base64 = args(0).getString
      val vector = Base64.getDecoder.decode(base64).toVector
      val nums   = vector.map(x => Double.box(x.asInstanceOf[Double]))
      LogoList.fromVector(nums)
    }
  }

  private object BytesToBase64 extends Reporter {
    override def getSyntax = Syntax.reporterSyntax(right = List(Syntax.ListType), ret = Syntax.StringType)
    override def report(args: Array[Argument], context: Context): AnyRef = {
      reportFromBytes(args(0).getList, (bytes: Array[Byte]) => Base64.getEncoder.encodeToString(bytes), "bytes-to-base64")
    }
  }

  private object BytesToString extends Reporter {
    override def getSyntax = Syntax.reporterSyntax(right = List(Syntax.ListType), ret = Syntax.StringType)
    override def report(args: Array[Argument], context: Context): AnyRef =
      reportFromBytes(args(0).getList, (bytes: Array[Byte]) => new String(bytes, "UTF-8"), "bytes-to-string")
  }

  private object StringToBytes extends Reporter {
    override def getSyntax = Syntax.reporterSyntax(right = List(Syntax.StringType), ret = Syntax.ListType)
    override def report(args: Array[Argument], context: Context): AnyRef = {
      val vector = args(0).getString.getBytes("UTF-8").toVector
      val nums   = vector.map(x => Double.box(x.asInstanceOf[Double]))
      LogoList.fromVector(nums)
    }
  }

  private def reportFromBytes(logoList: LogoList, f: (Array[Byte]) => AnyRef, primName: String): AnyRef = {

    val isLegit = {
      x: AnyRef =>
        x.isInstanceOf[JDouble] &&
        x.asInstanceOf[JDouble] >= Byte.MinValue &&
        x.asInstanceOf[JDouble] <= Byte.MaxValue
    }

    val array = logoList.toVector.toArray

    if (array.forall(isLegit))
      f(array.map(_.asInstanceOf[JDouble].toByte))
    else
      throw new ExtensionException(s"All elements of the list argument to 'encode:$primName' must be numbers between ${Byte.MinValue} and ${Byte.MaxValue}")

  }

}
