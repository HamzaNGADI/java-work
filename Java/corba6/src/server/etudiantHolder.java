package server;

/**
* server/etudiantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* lundi 12 f�vrier 2018 08 h 36 WET
*/

public final class etudiantHolder implements org.omg.CORBA.portable.Streamable
{
  public server.etudiant value = null;

  public etudiantHolder ()
  {
  }

  public etudiantHolder (server.etudiant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = server.etudiantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    server.etudiantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return server.etudiantHelper.type ();
  }

}
