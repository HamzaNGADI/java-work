package server;

/**
* server/familleHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public final class familleHolder implements org.omg.CORBA.portable.Streamable
{
  public server.famille value = null;

  public familleHolder ()
  {
  }

  public familleHolder (server.famille initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.familleHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.familleHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.familleHelper.type ();
  }

}
