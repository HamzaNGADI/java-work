package CosNaming;

/**
* CosNaming/etudiantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CosNaming.idl
* lundi 12 f�vrier 2018 07 h 55 WET
*/

public final class etudiantHolder implements org.omg.CORBA.portable.Streamable
{
  public CosNaming.etudiant value = null;

  public etudiantHolder ()
  {
  }

  public etudiantHolder (CosNaming.etudiant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CosNaming.etudiantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CosNaming.etudiantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CosNaming.etudiantHelper.type ();
  }

}
