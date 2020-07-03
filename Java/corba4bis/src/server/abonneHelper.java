package server;


/**
* server/abonneHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 24 janvier 2018 18 h 17 WET
*/

abstract public class abonneHelper
{
  private static String  _id = "IDL:server/abonne:1.0";

  public static void insert (org.omg.CORBA.Any a, server.abonne that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static server.abonne extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (server.abonneHelper.id (), "abonne");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static server.abonne read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_abonneStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, server.abonne value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static server.abonne narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof server.abonne)
      return (server.abonne)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      server._abonneStub stub = new server._abonneStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static server.abonne unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof server.abonne)
      return (server.abonne)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      server._abonneStub stub = new server._abonneStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}