package CosNaming;


/**
* CosNaming/etudiantHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CosNaming.idl
* lundi 12 f�vrier 2018 07 h 55 WET
*/

abstract public class etudiantHelper
{
  private static String  _id = "IDL:CosNaming/etudiant:1.0";

  public static void insert (org.omg.CORBA.Any a, CosNaming.etudiant that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CosNaming.etudiant extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (CosNaming.etudiantHelper.id (), "etudiant");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CosNaming.etudiant read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_etudiantStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CosNaming.etudiant value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static CosNaming.etudiant narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CosNaming.etudiant)
      return (CosNaming.etudiant)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CosNaming._etudiantStub stub = new CosNaming._etudiantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static CosNaming.etudiant unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CosNaming.etudiant)
      return (CosNaming.etudiant)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CosNaming._etudiantStub stub = new CosNaming._etudiantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
