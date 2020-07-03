package main;

import java.sql.*;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		System.out.println("done...");
		/*Connection cn;
		Statement stm;
		ResultSet mres;
		PreparedStatement ps;*/
	try{/*
			Class.forName("com.mysql.jdbc.Driver");
			
			cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mybasetp","root","");
			stm = (Statement) cn.createStatement();
			mres = stm.executeQuery("select * from t_livre");
			while(mres.next())
			{
				System.out.println("id : "+ mres.getInt("idLivre") +" titre : "+ mres.getString("titre") +" auteur : "+mres.getString("auteur")+" année : "+ mres.getInt("annee"));
			}
			Scanner sc = new Scanner(System.in); 
			System.out.println("entrer les valeur titre - auteur - année : ");
			String t=sc.nextLine();
			String au=sc.nextLine();
			int ann = sc.nextInt();
			
			int l = stm.executeUpdate("insert into t_livre values(null,'"+t+"','"+au+"', "+ann+")");
			mres.close();   stm.close();
			
			ps = cn.prepareStatement("insert into t_livre values(null,?,?,?)");
			System.out.println("re-entrer les valeur titre - auteur - année : ");
			
			ps.setString(1, "jquery");
			ps.setString(2, "w3c");
			ps.setInt(3, 2016);
			ps.executeUpdate();   */
		
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("entrer les valeur titre - auteur - année : ");
	/*	String t=sc.nextLine();
		String au=sc.nextLine();
		int ann = sc.nextInt();
			livre li = new livre(t,au,ann);*/
			base b = new base();
			b.ouvrir();
	//	b.enregistrerobj(li);
//			b.modifierrobj(new livre("java","sun",1995));
	//		b.supprobj(new livre("python","pylab",2001));
			ArrayList<livre> tv= b.lister(livre.class, "select * from t_livre");
			for(int i=0;i<tv.size();i++)
			{	
			System.out.println("titre : "+tv.get(i).gettitre()+" auteur : "+tv.get(i).getauteur()+" année : "+ tv.get(i).getannee());	
			}
			//b.afficher();
			
		}
		catch(Exception e)
		{
		}
	}


}
