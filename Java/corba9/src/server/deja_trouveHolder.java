package server;

/**
* server/deja_trouveHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public final class deja_trouveHolder implements org.omg.CORBA.portable.Streamable
{
  public server.deja_trouve value = null;

  public deja_trouveHolder ()
  {
  }

  public deja_trouveHolder (server.deja_trouve initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.deja_trouveHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.deja_trouveHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.deja_trouveHelper.type ();
  }

}