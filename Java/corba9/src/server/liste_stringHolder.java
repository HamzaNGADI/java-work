package server;


/**
* server/liste_stringHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public final class liste_stringHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public liste_stringHolder ()
  {
  }

  public liste_stringHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.liste_stringHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.liste_stringHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.liste_stringHelper.type ();
  }

}