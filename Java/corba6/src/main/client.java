package main;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.*;

import server.etudiant;
import server.usine;
import server.usineHelper;

public class client {

	public static void main(String[] args) throws AlreadyBound, NotFound, CannotProceed, InvalidName {
		// TODO Auto-generated method stub
		ORB orb = ORB.init(args, null);

		org.omg.CORBA.Object obj = null;

 		try
			{
			obj = orb.resolve_initial_references("NameService");
			}
		catch(org.omg.CORBA.ORBPackage.InvalidName ex)
		{
			System.out.println("'NameService' inaccessible");
			System.exit(1);
		}

		if(obj == null)
			{
			System.out.println("Reference nil sur `NameService'");
			System.exit(1);
			}

		NamingContext nc = NamingContextHelper.narrow(obj);
		if(nc == null)
			{
			System.out.println("Reference type nil sur `NameService'");
			System.exit(1);
			}

		try
			{
			NameComponent[] aName = new NameComponent[1];
			aName[0] = new NameComponent();
			aName[0].id = "formations professionnelles";
			aName[0].kind = "usine";
			obj=nc.resolve(aName);

			//------------
			
	/*		NameComponent[] frtcon = new NameComponent[1];
			frtcon[0] = new NameComponent();
			frtcon[0].id = "DESS";
			frtcon[0].kind = "";
			org.omg.CORBA.Object f = nc.resolve(frtcon);
			NamingContext nc1 = NamingContextHelper.narrow(f);
			
			NameComponent[] frt = new NameComponent[1];
			frt[0] = new NameComponent();
			frt[0].id = "e1";
			frt[0].kind = "e1";
			e1 = nc1.resolve(frt);
			frt[0].id = "e2";
			frt[0].kind = "e2";
			e2 = nc1.resolve(frt);
			nc.bind(frt, arg1);*/

			
			
			 //--------------
			/*NameComponent[] iup = new NameComponent[3];
			iup[0] = new NameComponent();
			iup[0].id = "IUP";
			iup[0].kind = "";
			iup[1] = new NameComponent();
			iup[1].id = "IUP1";
			iup[1].kind = "1";
			iup[2] = new NameComponent();
			iup[2].id = "e3";
			iup[2].kind = "e3";
			e3 = nc.resolve(iup);
			iup[2] = new NameComponent();
			iup[2].id = "e4";
			iup[2].kind = "e4";
			e4 = nc.resolve(iup);
			
			iup[1] = new NameComponent();
			iup[1].id = "IUP2";
			iup[1].kind = "2";
			iup[2] = new NameComponent();
			iup[2].id = "e5";
			iup[2].kind = "e5";
			e5 = nc.resolve(iup);
			
			iup[1] = new NameComponent();
			iup[1].id = "IUP3";
			iup[1].kind = "3";
			iup[2] = new NameComponent();
			iup[2].id = "e6";
			iup[2].kind = "e6";
			e6 = nc.resolve(iup);
			*/
			
			//-------
		/*	NameComponent[] eff = new NameComponent[2];
			eff[0] = new NameComponent();
			eff[0].id = "EFFECTIF";
			eff[0].kind = "";
			eff[1] = new NameComponent();
			eff[1].id = "e1";
			eff[1].kind = "e1a";
			e1 = nc.resolve(eff);
			eff[1].id = "e2";
			eff[1].kind = "e2a";
			e2 = nc.resolve(eff);
			
			eff[1].id = "e3";
			eff[1].kind = "e3a";
			e3 = nc.resolve(eff);
			eff[1].id = "e4";
			eff[1].kind = "e4a";
			e4 = nc.resolve(eff);
			
			eff[1].id = "e5";
			eff[1].kind = "e5a";
			e5 = nc.resolve(eff);
			eff[1].id = "e6";
			eff[1].kind = "e6a";
			e6 = nc.resolve(eff);
			*/
			}
		catch(CannotProceed ex)
				{
				System.out.println("CannotProceed sur resolve");
				System.exit(1);
				}
		catch(InvalidName ex)
				{
				System.out.println("InvalidName sur resolve");
				System.exit(1);
				}
		catch(NotFound ex)
				{
				System.out.println("NotFound sur resolve");
				System.exit(1);
				}

		usine us = usineHelper.narrow(obj);
		if(us==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}


		// Creation des objets et publication vers le serveur 
		// de nom
		//
		etudiant e1=us.inscription_d_un_etudiant("e1-nom", "e1-prenom");
		etudiant e2=us.inscription_d_un_etudiant("e2-nom", "e2-prenom");
		etudiant e3=us.inscription_d_un_etudiant("e3-nom", "e3-prenom");
		etudiant e4=us.inscription_d_un_etudiant("e4-nom", "e4-prenom");
		etudiant e5=us.inscription_d_un_etudiant("e5-nom", "e5-prenom");
		etudiant e6=us.inscription_d_un_etudiant("e6-nom", "e6-prenom");
		
		NameComponent[] nc1Name = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "dess";
		nc1Name[0].kind = "";
		NamingContext nc1 = nc.bind_new_context(nc1Name);
// ya deux param on 
		NameComponent[] e1c = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "e1";
		nc1Name[0].kind = "";
		NameComponent[] e2c = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "e2";
		nc1Name[0].kind = "";
		NameComponent[] e3c = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "e3";
		nc1Name[0].kind = "";
		NameComponent[] e4c = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "e4";
		nc1Name[0].kind = "";
		NameComponent[] e5c = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "e5";
		nc1Name[0].kind = "";
		NameComponent[] e6c = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "e6";
		nc1Name[0].kind = "";
		nc1.bind(e1c, e1);
		nc1.bind(e2c, e2);
		
		NameComponent[] nc2Name = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "IUP";
		nc1Name[0].kind = "";
		NamingContext nc2 = nc.bind_new_context(nc2Name);
		
		NameComponent[] nc2Namea = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "IUP1";
		nc1Name[0].kind = "";
		NamingContext nc3 = nc2.bind_new_context(nc2Namea);
		nc3.bind(e3c, e3);
		nc3.bind(e4c, e4);

		NameComponent[] nc2Nameb = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "IUP2";
		nc1Name[0].kind = "";
		NamingContext nc4 = nc2.bind_new_context(nc2Nameb);
		nc4.bind(e5c, e5);
		

		NameComponent[] nc2Namec = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "IUP3";
		nc1Name[0].kind = "";
		NamingContext nc5 = nc2.bind_new_context(nc2Namec);
		nc5.bind(e6c, e6);
		
		NameComponent[] nc3Name = new NameComponent[1];
		nc1Name[0] = new NameComponent();
		nc1Name[0].id = "Effectif";
		nc1Name[0].kind = "";
		NamingContext nc6 = nc.bind_new_context(nc3Name);
		nc6.bind(e1c, e1);
		nc6.bind(e2c, e2);
		nc6.bind(e3c, e3);
		nc6.bind(e4c, e4);
		nc6.bind(e5c, e5);
		nc6.bind(e6c, e6);



 
		System.exit(0);
		}
	}

// j'ai pas bien assimiler comment on construit le graphe .. est ce que tout les objets noeuds sont des namecomponent ?