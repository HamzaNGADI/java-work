package server;

/**
* server/dejaExistantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 24 janvier 2018 07 h 20 WET
*/

public final class dejaExistantHolder implements org.omg.CORBA.portable.Streamable
{
  public server.dejaExistant value = null;

  public dejaExistantHolder ()
  {
  }

  public dejaExistantHolder (server.dejaExistant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.dejaExistantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.dejaExistantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.dejaExistantHelper.type ();
  }

}