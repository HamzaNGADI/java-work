package server;


/**
* server/bibliothequePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public abstract class bibliothequePOA extends org.omg.PortableServer.Servant
 implements server.bibliothequeOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_tous_les_ouvrages", new java.lang.Integer (0));
    _methods.put ("ajouter_ouvrage", new java.lang.Integer (1));
    _methods.put ("rechercher_ouvrage", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // server/bibliotheque/_get_tous_les_ouvrages
       {
         server.ouvrage $result[] = null;
         $result = this.tous_les_ouvrages ();
         out = $rh.createReply();
         server.liste_ouvrageHelper.write (out, $result);
         break;
       }

       case 1:  // server/bibliotheque/ajouter_ouvrage
       {
         try {
           String titre = in.read_string ();
           server.ouvrage $result = null;
           $result = this.ajouter_ouvrage (titre);
           out = $rh.createReply();
           server.ouvrageHelper.write (out, $result);
         } catch (server.deja_trouve $ex) {
           out = $rh.createExceptionReply ();
           server.deja_trouveHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // server/bibliotheque/rechercher_ouvrage
       {
         try {
           server.liste_ouvrageHolder l = new server.liste_ouvrageHolder ();
           l.value = server.liste_ouvrageHelper.read (in);
           String mot = in.read_string ();
           this.rechercher_ouvrage (l, mot);
           out = $rh.createReply();
           server.liste_ouvrageHelper.write (out, l.value);
         } catch (server.non_trouve $ex) {
           out = $rh.createExceptionReply ();
           server.non_trouveHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:server/bibliotheque:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public bibliotheque _this() 
  {
    return bibliothequeHelper.narrow(
    super._this_object());
  }

  public bibliotheque _this(org.omg.CORBA.ORB orb) 
  {
    return bibliothequeHelper.narrow(
    super._this_object(orb));
  }


} // class bibliothequePOA