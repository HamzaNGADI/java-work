package server;


/**
* server/non_trouve.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public final class non_trouve extends org.omg.CORBA.UserException
{

  public non_trouve ()
  {
    super(non_trouveHelper.id());
  } // ctor


  public non_trouve (String $reason)
  {
    super(non_trouveHelper.id() + "  " + $reason);
  } // ctor

} // class non_trouve
