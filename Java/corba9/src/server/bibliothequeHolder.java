package server;

/**
* server/bibliothequeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public final class bibliothequeHolder implements org.omg.CORBA.portable.Streamable
{
  public server.bibliotheque value = null;

  public bibliothequeHolder ()
  {
  }

  public bibliothequeHolder (server.bibliotheque initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.bibliothequeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.bibliothequeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.bibliothequeHelper.type ();
  }

}
