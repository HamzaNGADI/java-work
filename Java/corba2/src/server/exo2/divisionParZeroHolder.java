package server.exo2;

/**
* server/exo2/divisionParZeroHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* lundi 15 janvier 2018 17 h 57 WET
*/

public final class divisionParZeroHolder implements org.omg.CORBA.portable.Streamable
{
  public server.exo2.divisionParZero value = null;

  public divisionParZeroHolder ()
  {
  }

  public divisionParZeroHolder (server.exo2.divisionParZero initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.exo2.divisionParZeroHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.exo2.divisionParZeroHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.exo2.divisionParZeroHelper.type ();
  }

}
