package server;


/**
* server/table_abonneHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 24 janvier 2018 18 h 17 WET
*/

public final class table_abonneHolder implements org.omg.CORBA.portable.Streamable
{
  public server.abonne value[] = null;

  public table_abonneHolder ()
  {
  }

  public table_abonneHolder (server.abonne[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.table_abonneHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.table_abonneHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.table_abonneHelper.type ();
  }

}
