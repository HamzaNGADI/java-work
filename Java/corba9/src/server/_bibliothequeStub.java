package server;


/**
* server/_bibliothequeStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public class _bibliothequeStub extends org.omg.CORBA.portable.ObjectImpl implements server.bibliotheque
{

  public server.ouvrage[] tous_les_ouvrages ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_tous_les_ouvrages", true);
                $in = _invoke ($out);
                server.ouvrage $result[] = server.liste_ouvrageHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return tous_les_ouvrages (        );
            } finally {
                _releaseReply ($in);
            }
  } // tous_les_ouvrages

  public server.ouvrage ajouter_ouvrage (String titre) throws server.deja_trouve
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("ajouter_ouvrage", true);
                $out.write_string (titre);
                $in = _invoke ($out);
                server.ouvrage $result = server.ouvrageHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:server/deja_trouve:1.0"))
                    throw server.deja_trouveHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return ajouter_ouvrage (titre        );
            } finally {
                _releaseReply ($in);
            }
  } // ajouter_ouvrage

  public void rechercher_ouvrage (server.liste_ouvrageHolder l, String mot) throws server.non_trouve
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("rechercher_ouvrage", true);
                server.liste_ouvrageHelper.write ($out, l.value);
                $out.write_string (mot);
                $in = _invoke ($out);
                l.value = server.liste_ouvrageHelper.read ($in);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:server/non_trouve:1.0"))
                    throw server.non_trouveHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                rechercher_ouvrage (l, mot        );
            } finally {
                _releaseReply ($in);
            }
  } // rechercher_ouvrage

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:server/bibliotheque:1.0"};

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
} // class _bibliothequeStub
