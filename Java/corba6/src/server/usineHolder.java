package server;

/**
* server/usineHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* lundi 12 f�vrier 2018 08 h 36 WET
*/

public final class usineHolder implements org.omg.CORBA.portable.Streamable
{
  public server.usine value = null;

  public usineHolder ()
  {
  }

  public usineHolder (server.usine initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.usineHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.usineHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.usineHelper.type ();
  }

}
