package server;


/**
* server/famille.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* mercredi 31 janvier 2018 15 h 05 WET
*/

public class famille implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static server.famille[] __array = new server.famille [__size];

  public static final int _revue = 0;
  public static final server.famille revue = new server.famille(_revue);
  public static final int _livre = 1;
  public static final server.famille livre = new server.famille(_livre);
  public static final int _bd = 2;
  public static final server.famille bd = new server.famille(_bd);
  public static final int _cd = 3;
  public static final server.famille cd = new server.famille(_cd);
  public static final int _dvd = 4;
  public static final server.famille dvd = new server.famille(_dvd);

  public int value ()
  {
    return __value;
  }

  public static server.famille from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected famille (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class famille