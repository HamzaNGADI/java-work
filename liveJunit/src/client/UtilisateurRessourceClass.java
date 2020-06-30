package client;
import java.util.ArrayList;
import java.util.List;

import reservation.*;
import org.junit.*;

public class UtilisateurRessourceClass {
	
	public static void main(String[] args) {
		System.out.println("okk !!");
		
		Utilisateur user = new Utilisateur("hamza", "ngadi", "info", "hamza@ngadi.info");
		
		System.out.println("user : "+user.getPrenom()+" "+user.getPrenom()+" grade : "+user.getGrade()+" email : "+user.getEmail());
		user.setGrade(" ");
		System.out.println("user : "+user.getPrenom()+" "+user.getPrenom()+" grade : "+user.getGrade()+" email : "+user.getEmail());
		user.setGrade("prog");
		System.out.println("user : "+user.getPrenom()+" "+user.getPrenom()+" grade : "+user.getGrade()+" email : "+user.getEmail());
		
	    UtilisateurRessource urc = new UtilisateurRessource();
		System.out.println(urc.createUtilisateur("hamza", "ngadi", "etuduant", "hamza@brest.info"));
		System.out.println(urc.createUtilisateur("hamza", "ngadi", "etudiant_ubo", "hamza@ubo.info"));
		
		Utilisateur user2 = urc.getUtilisateur("anais", "ngadi");
		if(user2 != null)
     		System.out.println("user : "+user2.getPrenom()+" "+user2.getPrenom()+" grade : "+user2.getGrade()+" email : "+user2.getEmail());
		else System.out.println("null");
		user2 = urc.getUtilisateur("hamza", "ngadi");
		System.out.println("user : "+user2.getPrenom()+" "+user2.getPrenom()+" grade : "+user2.getGrade()+" email : "+user2.getEmail());
		
		List<Utilisateur> auc = ReservationBDD.getUtilisateurs();
		
		if(auc.size() != 0)
		{
			for(int i=0 ;i<auc.size();i++)
			{
				Utilisateur ulist = auc.get(i);
				
				System.out.println("list ! user : "+ulist.getPrenom()+" "+ulist.getPrenom()+" grade : "+ulist.getGrade()+" email : "+ulist.getEmail());

			}
		}
		else System.out.println("array null");
		
		Salle salle = new Salle("salle J400","DOMV");
		System.out.println("salle : "+salle.getNom()+" "+salle.getType());
		
        List<Salle> sacl = ReservationBDD.getSalles();
		
		if(sacl.size() != 0)
		{
			for(int i=0 ;i<sacl.size();i++)
			{
				Salle ulist = sacl.get(i);
				
				System.out.println("list ! salle : "+ulist.getNom()+" "+ulist.getType());

			}
		}
		else System.out.println("array salle null");
		
		ReservationRessource rs = new  ReservationRessource();
		System.out.println(rs.createReservation("reservation1", "7juin","14:00", (double)8, "TP", "I005","Mounir", "Mounir","MdC", "mounir.lallali@univ-brest.fr"));
		System.out.println(rs.createReservation("reservation2", "22juin","11:00", (double)10, "TP", "I005","Hamza", "ngadi","etudiant", "hamza@brest.fr"));
		System.out.println(rs.createReservation("reservation1", "7juin","14:00", (double)8, "TP", "I05","Mounir", "Mounir","MdC", "mounir.lallali@univ-brest.fr"));
		
		 List<Reservation> res = ReservationBDD.getReservations();
			String num="";
			if(res.size() != 0)
			{
				for(int i=0 ;i<res.size();i++)
				{
					Reservation ulist = res.get(i);
					if(ulist.getDescription().equals("reservation2")) num=ulist.getNumReservation();
					System.out.println("list ! res : "+ulist.getDescription()+" "+ulist.getDate()+" "+ulist.getHeure()+" "+ulist.getNumReservation());

				}
			}
			else System.out.println("array res null");
			
			System.out.println(rs.removeReservation(num));
			
			if(res.size() != 0)
			{
				for(int i=0 ;i<res.size();i++)
				{
					Reservation ulist = res.get(i);
					if(ulist.getDescription().equals("reservation2")) num=ulist.getNumReservation();
					System.out.println("list ! res : "+ulist.getDescription()+" "+ulist.getDate()+" "+ulist.getHeure()+" "+ulist.getNumReservation());

				}
			}
			else System.out.println("array res null");


		
	}
	
}
