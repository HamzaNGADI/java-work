package server.exo2;


/**
* server/exo2/calculHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* lundi 15 janvier 2018 17 h 57 WET
*/

abstract public class calculHelper
{
  private static String  _id = "IDL:server/exo2/calcul:1.0";

  public static void insert (org.omg.CORBA.Any a, server.exo2.calcul that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static server.exo2.calcul extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (server.exo2.calculHelper.id (), "calcul");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static server.exo2.calcul read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_calculStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, server.exo2.calcul value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static server.exo2.calcul narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof server.exo2.calcul)
      return (server.exo2.calcul)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      server.exo2._calculStub stub = new server.exo2._calculStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static server.exo2.calcul unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof server.exo2.calcul)
      return (server.exo2.calcul)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      server.exo2._calculStub stub = new server.exo2._calculStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}