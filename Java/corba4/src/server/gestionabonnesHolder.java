package server;

/**
* server/gestionabonnesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 24 janvier 2018 07 h 20 WET
*/

public final class gestionabonnesHolder implements org.omg.CORBA.portable.Streamable
{
  public server.gestionabonnes value = null;

  public gestionabonnesHolder ()
  {
  }

  public gestionabonnesHolder (server.gestionabonnes initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.gestionabonnesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.gestionabonnesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.gestionabonnesHelper.type ();
  }

}
