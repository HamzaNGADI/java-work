package server;


/**
* server/deja_trouve.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public final class deja_trouve extends org.omg.CORBA.UserException
{

  public deja_trouve ()
  {
    super(deja_trouveHelper.id());
  } // ctor


  public deja_trouve (String $reason)
  {
    super(deja_trouveHelper.id() + "  " + $reason);
  } // ctor

} // class deja_trouve
