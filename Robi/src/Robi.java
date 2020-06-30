import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Robi {
	static HashMap<String, Commande> CommandesUtilisables = new HashMap<String, Commande>();
	String trace = "";
	HashMap<String, Commande> CommandesUtilisables1 = new HashMap<String, Commande>();
	static {
		CommandesUtilisables = new HashMap<String, Commande>();
		CommandesUtilisables.put("beep", new beep());
		CommandesUtilisables.put("moveright", new moveright());
		CommandesUtilisables.put("movetop", new movetop());
	/*	CommandesUtilisables.put("beep x", new beep("medium"));
		CommandesUtilisables.put("move", new move(10,2));*/



	}

	String trace() {
		return trace;
	}

	void run(Commande cmd) {
		int i=0,ii=0;
		for(String k:CommandesUtilisables1.keySet())
		{
		//	System.out.println(k+" "+ Class.class.(cmd));
			//String st[] = cmd.getClass().getName().toString().split(" ");
			if(cmd.getClass().getName().toString().equals(k))
			{
				String s="";
				if(cmd instanceof beep && k.contains("x")) {s=((beep) cmd).getS();    }
				if(cmd instanceof move) s=((move) cmd).getx()+" "+((move) cmd).gety();

				System.out.println(k+" "+s+"//"+cmd.run());//+"    k:"+k);
				trace +="["+k.toString().toUpperCase()+"]";  
			}
			
		}
		
		//trace +=(CommandesUtilisables.keySet()).toString().toUpperCase();  
	}

	void exp(ArrayList<String> prog)
	{
			String k[];
			System.out.println(CommandesUtilisables1.size());

		for(int i=0;i<prog.size();i++)
		{
			k = prog.get(i).split(" ");
//			System.out.println(k.length+"++++"+k[0]);
			if(k.length ==3 && k[0].equals("move")){
     			CommandesUtilisables1.put("move", new move( Integer.parseInt(k[1]),  Integer.parseInt(k[2])));
     			System.out.println("done1"); 			System.out.println(k.length+"++++"+k[2]);

			}
     	    if(k.length ==2 && k[0].toLowerCase().equals("beep")){
     			CommandesUtilisables1.put("beep x", new beep(k[1]));
     			System.out.println("done2"); System.out.println(k.length+"++++");

     	    }
			
		}
		System.out.println(CommandesUtilisables1.size()+" "+prog.size());
		HashMap<String, Integer> cl = new HashMap<String, Integer>();
		cl.put("a", 1);
		cl.put("b", 20);
		cl.put("a", 74); cl.put("a", 5); cl.put("a", 84);
		cl.put("c", 4);
		System.out.println(cl.size());




		
}
	void run(ArrayList<String> prog) {
		CommandesUtilisables1 =  (HashMap<String, Commande>) CommandesUtilisables.clone();
		int i=0;
		exp(prog);
			for (String key : prog)  {
				// key est la clé de la commande à exécuter
				// Recuperation de la commandes
				Commande cmd;
				String k[] = key.split(" ");
				if(k.length==1)   cmd = CommandesUtilisables1.get(key);
				else if(k.length==2)   cmd = CommandesUtilisables1.get(k[0]+" x");
				else cmd = CommandesUtilisables1.get(k[0]);
				
//				System.out.println(key+" -------");
				// execution de la commande
				this.run(cmd);
				
			}
			
		}
	
}


