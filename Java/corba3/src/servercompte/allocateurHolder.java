package servercompte;

/**
* servercompte/allocateurHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from servercompte.idl
* lundi 15 janvier 2018 19 h 17 WET
*/

public final class allocateurHolder implements org.omg.CORBA.portable.Streamable
{
  public servercompte.allocateur value = null;

  public allocateurHolder ()
  {
  }

  public allocateurHolder (servercompte.allocateur initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servercompte.allocateurHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servercompte.allocateurHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servercompte.allocateurHelper.type ();
  }

}
