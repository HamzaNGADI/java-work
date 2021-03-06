package server;


/**
* server/abonnePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 24 janvier 2018 07 h 20 WET
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
