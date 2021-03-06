package servercompte;


/**
* servercompte/comptePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from servercompte.idl
* lundi 15 janvier 2018 19 h 17 WET
*/

public abstract class comptePOA extends org.omg.PortableServer.Servant
 implements servercompte.compteOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_numero_compte", new java.lang.Integer (0));
    _methods.put ("_get_titulaire", new java.lang.Integer (1));
    _methods.put ("_get_solde", new java.lang.Integer (2));
    _methods.put ("nombre_operations", new java.lang.Integer (3));
    _methods.put ("debiter", new java.lang.Integer (4));
    _methods.put ("crediter", new java.lang.Integer (5));
    _methods.put ("prelevement", new java.lang.Integer (6));
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
       case 0:  // servercompte/compte/_get_numero_compte
       {
         int $result = (int)0;
         $result = this.numero_compte ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // servercompte/compte/_get_titulaire
       {
         String $result = null;
         $result = this.titulaire ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // servercompte/compte/_get_solde
       {
         double $result = (double)0;
         $result = this.solde ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 3:  // servercompte/compte/nombre_operations
       {
         org.omg.CORBA.IntHolder nombre = new org.omg.CORBA.IntHolder ();
         this.nombre_operations (nombre);
         out = $rh.createReply();
         out.write_long (nombre.value);
         break;
       }

       case 4:  // servercompte/compte/debiter
       {
         double montant = in.read_double ();
         this.debiter (montant);
         out = $rh.createReply();
         break;
       }

       case 5:  // servercompte/compte/crediter
       {
         double montant = in.read_double ();
         this.crediter (montant);
         out = $rh.createReply();
         break;
       }

       case 6:  // servercompte/compte/prelevement
       {
         double montant = in.read_double ();
         servercompte.compte destination = servercompte.compteHelper.read (in);
         this.prelevement (montant, destination);
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
    "IDL:servercompte/compte:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public compte _this() 
  {
    return compteHelper.narrow(
    super._this_object());
  }

  public compte _this(org.omg.CORBA.ORB orb) 
  {
    return compteHelper.narrow(
    super._this_object(orb));
  }


} // class comptePOA
