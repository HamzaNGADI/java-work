package server.exo2;


/**
* server/exo2/_calculStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* lundi 15 janvier 2018 17 h 57 WET
*/

public class _calculStub extends org.omg.CORBA.portable.ObjectImpl implements server.exo2.calcul
{

  public double memoire ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_memoire", true);
                $in = _invoke ($out);
                double $result = $in.read_double ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return memoire (        );
            } finally {
                _releaseReply ($in);
            }
  } // memoire

  public void ajouteMemoire (double donnee)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("ajouteMemoire", true);
                $out.write_double (donnee);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                ajouteMemoire (donnee        );
            } finally {
                _releaseReply ($in);
            }
  } // ajouteMemoire

  public void soustraitMemoire (double donnee)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("soustraitMemoire", true);
                $out.write_double (donnee);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                soustraitMemoire (donnee        );
            } finally {
                _releaseReply ($in);
            }
  } // soustraitMemoire

  public void multiplieMemoire (double donnee)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("multiplieMemoire", true);
                $out.write_double (donnee);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                multiplieMemoire (donnee        );
            } finally {
                _releaseReply ($in);
            }
  } // multiplieMemoire

  public void miseAZero ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("miseAZero", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                miseAZero (        );
            } finally {
                _releaseReply ($in);
            }
  } // miseAZero

  public void diviseMemoire (double donnee) throws server.exo2.divisionParZero
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("diviseMemoire", true);
                $out.write_double (donnee);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:server/exo2/divisionParZero:1.0"))
                    throw server.exo2.divisionParZeroHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                diviseMemoire (donnee        );
            } finally {
                _releaseReply ($in);
            }
  } // diviseMemoire

  public void incrementer (org.omg.CORBA.IntHolder data)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("incrementer", true);
                $out.write_long (data.value);
                $in = _invoke ($out);
                data.value = $in.read_long ();
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                incrementer (data        );
            } finally {
                _releaseReply ($in);
            }
  } // incrementer

  public void decrementer (org.omg.CORBA.IntHolder data)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("decrementer", true);
                $out.write_long (data.value);
                $in = _invoke ($out);
                data.value = $in.read_long ();
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                decrementer (data        );
            } finally {
                _releaseReply ($in);
            }
  } // decrementer

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:server/exo2/calcul:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _calculStub