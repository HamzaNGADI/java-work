package server;


/**
* server/liste_ouvrageHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public final class liste_ouvrageHolder implements org.omg.CORBA.portable.Streamable
{
  public server.ouvrage value[] = null;

  public liste_ouvrageHolder ()
  {
  }

  public liste_ouvrageHolder (server.ouvrage[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.liste_ouvrageHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.liste_ouvrageHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.liste_ouvrageHelper.type ();
  }

}
