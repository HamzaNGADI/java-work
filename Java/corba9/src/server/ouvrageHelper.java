package server;


/**
* server/ouvrageHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

abstract public class ouvrageHelper
{
  private static String  _id = "IDL:server/ouvrage:1.0";

  public static void insert (org.omg.CORBA.Any a, server.ouvrage that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static server.ouvrage extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (server.ouvrageHelper.id (), "ouvrage");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static server.ouvrage read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ouvrageStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, server.ouvrage value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static server.ouvrage narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof server.ouvrage)
      return (server.ouvrage)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      server._ouvrageStub stub = new server._ouvrageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static server.ouvrage unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof server.ouvrage)
      return (server.ouvrage)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      server._ouvrageStub stub = new server._ouvrageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
