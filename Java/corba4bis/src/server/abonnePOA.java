package server;


/**
* server/abonnePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 24 janvier 2018 18 h 17 WET
*/

public abstract class abonnePOA extends org.omg.PortableServer.Servant
 implements server.abonneOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_numero", new java.lang.Integer (0));
    _methods.put ("_get_nom_prenom", new java.lang.Integer (1));
    _methods.put ("_get_abonnement", new java.lang.Integer (2));
    _methods.put ("_set_abonnement", new java.lang.Integer (3));
    _methods.put ("_get_adresse", new java.lang.Integer (4));
    _methods.put ("_set_adresse", new java.lang.Integer (5));
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
       case 0:  // server/abonne/_get_numero
       {
         int $result = (int)0;
         $result = this.numero ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // server/abonne/_get_nom_prenom
       {
         String $result = null;
         $result = this.nom_prenom ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // server/abonne/_get_abonnement
       {
         server.type_abonnement $result = null;
         $result = this.abonnement ();
         out = $rh.createReply();
         server.type_abonnementHelper.write (out, $result);
         break;
       }

       case 3:  // server/abonne/_set_abonnement
       {
         server.type_abonnement newAbonnement = server.type_abonnementHelper.read (in);
         this.abonnement (newAbonnement);
         out = $rh.createReply();
         break;
       }

       case 4:  // server/abonne/_get_adresse
       {
         server.type_adresse $result = null;
         $result = this.adresse ();
         out = $rh.createReply();
         server.type_adresseHelper.write (out, $result);
         break;
       }

       case 5:  // server/abonne/_set_adresse
       {
         server.type_adresse newAdresse = server.type_adresseHelper.read (in);
         this.adresse (newAdresse);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:server/abonne:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public abonne _this() 
  {
    return abonneHelper.narrow(
    super._this_object());
  }

  public abonne _this(org.omg.CORBA.ORB orb) 
  {
    return abonneHelper.narrow(
    super._this_object(orb));
  }


} // class abonnePOA
