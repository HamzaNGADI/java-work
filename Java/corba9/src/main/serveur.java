package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import server.*;

public class serveur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			 //init ORB
            ORB orb = ORB.init(args, null);

            //init POA
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();

            ////////////////////////////////////////////////////////////////
            bibliohequeImp bimpl = new bibliohequeImp(poa);
            org.omg.CORBA.Object bialloc = poa.servant_to_reference(bimpl);

            try {
                String calc_ref = orb.object_to_string(bialloc);
                String refFile = "bib.ref";
                PrintWriter out = new PrintWriter(new FileOutputStream(refFile));
                out.println(calc_ref);
                out.close();
            } catch (IOException ex) {
                System.err.println(
                    "Impossible d'ecrire la reference dans bib.ref");
                System.exit(1);
            }

            System.out.println("Le serveur est pret ");

            orb.run();

            System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	

}
