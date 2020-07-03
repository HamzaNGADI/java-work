package aplimeth;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class base {
	
	Connection cn;
	Statement stm;
	ResultSet mres;
	PreparedStatement ps;
	public base()
	{
		
	}
	public void ouvrir()
	{
		try {
	//		ResourceBundle rb = ResourceBundle.getBundle("config");
			Class.forName("com.mysql.jdbc.Driver");
			cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mybasetp","root","");
			stm = (Statement) cn.createStatement();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void fermer()
	{
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void enregistrer(livre l)
	{
		try {
		
		int ls = stm.executeUpdate("insert into t_livre values(null,'"+l.gettitre()+"','"+l.getauteur()+"', "+l.getannee()+")");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void afficher()
	{
		try {
			mres = stm.executeQuery("select * from t_livre");
		
		while(mres.next())
		{
			System.out.println("id : "+ mres.getInt("idLivre") +" titre : "+ mres.getString("titre") +" auteur : "+mres.getString("auteur")+" année : "+ mres.getInt("annee"));
		}
			mres.close();   stm.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void enregistrerobj(Object o)
	{
		int ib=0;
		if(o instanceof livre){
		try {
			Class c = o.getClass();
			Method[] f = c.getDeclaredMethods();
			Method[] mi = new Method[f.length];
			for(int i=0;i<f.length;i++)
			{
				if(!f[i].getName().contains("set"))
					{System.out.println(f[i].getName());
				mi[i]=c.getMethod(f[i].getName()); ib++;
					}
			}
			/*Method m = c.getMethod("gettitre");
			Method mm = c.getMethod("getauteur");
			Method mmm = c.getMethod("getannee");*/
			
			System.out.println(mi.length+"--"+ib);
		
			Annotation an = c.getAnnotation(table.class);
			table ta = (table)an;
			String qr="insert into "+ta.name()+" values(null,";
			for(int i=0;i<ib;i++)
			{
				if(mi[i].getReturnType()==String.class)
				{
					qr+="'"+mi[i].invoke(o)+"'";
				}
				else qr+=mi[i].invoke(o);
				System.out.println(mi[i].getName());
				
				if(i!=ib-1)  qr+=",";
			}
			qr+=")";
			System.out.println(qr);
			int ls = stm.executeUpdate(qr);
			
		} catch (Exception e) {System.out.println(e.getMessage());}
		
	  }
		
	}
	public void modifierrobj(Object o)
	{
		if(o instanceof livre){
		try {
			int ib=0;
			
			Class c = o.getClass();
			Method[] f = c.getDeclaredMethods();
			Method[] mi = new Method[f.length];
			for(int i=0;i<f.length;i++)
			{
				if(!f[i].getName().contains("set"))
				{System.out.println(f[i].getName());
			mi[i]=c.getMethod(f[i].getName()); ib++;
				}
			}
        			String l="modified";

			Annotation an = c.getAnnotation(table.class);
			table ta = (table)an;
			String qr="update "+ta.name()+" set ";
			String tdb[] ={ta.titre(),ta.auteur(),ta.annee()}; 
			for(int i=0;i<ib;i++)
			{
				if(mi[i].getReturnType()==String.class)
				{
					qr+=tdb[i]+"='"+mi[i].invoke(o)+l+"'";
				}
				else qr+= tdb[i]+"="+mi[i].invoke(o);
				
				if(i!=ib-1)  qr+=",";
			}
			System.out.println(qr);

			qr+=" where "+tdb[0]+"='"+mi[0].invoke(o)+"'";
			mres = stm.executeQuery("select * from "+ta.name()+" where "+ta.titre()+"='"+mi[0].invoke(o)+"' and "+ta.auteur()+"='"+mi[1].invoke(o)+"'");
			if(mres.next())
			{
				System.out.println(qr);
			int ls = stm.executeUpdate(qr);
			}
		} catch (Exception e) {System.out.println(e.getMessage());}
		
	  }
		
	}
	public void supprobj(Object o)
	{
		if(o instanceof livre){
		try {
			int ib=0;
			Class c = o.getClass();
			Method[] f = c.getDeclaredMethods();
			Method[] mi = new Method[f.length];
			for(int i=0;i<f.length;i++)
			{
			//	System.out.println(f[i].getName());
			//	mi[i]=c.getMethod(f[i].getName());
				if(!f[i].getName().contains("set"))
				{System.out.println(f[i].getName());
			mi[i]=c.getMethod(f[i].getName()); ib++;
				}
			}

			Annotation an = c.getAnnotation(table.class);
			table ta = (table)an;
			String qr="delete from "+ta.name()+" where ";
			String tdb[] ={ta.titre(),ta.auteur(),ta.annee()}; 
			for(int i=0;i<ib-1;i++)
			{
				if(mi[i].getReturnType()==String.class)
				{
//					qr+=tdb[i]+"='"+mi[i].invoke(o)+"'";
					qr+=tdb[i]+"=?";
				}
	//			else qr+= tdb[i]+"="+mi[i].invoke(o);
				
				if(i!=ib-2)  qr+=" and ";
			}

			mres = stm.executeQuery("select * from "+ta.name()+" where "+ta.titre()+"='"+mi[0].invoke(o)+"' and "+ta.auteur()+"='"+mi[1].invoke(o)+"'");

			if(mres.next())
			{
				System.out.println(qr);
			ps = cn.prepareStatement(qr);
			ps.setString(1, mi[0].invoke(o).toString());
			ps.setString(2, mi[1].invoke(o).toString());
			ps.executeUpdate();
			}
		} catch (Exception e) {System.out.println(e.getMessage());}
		
	  }
		
	}
	
	public ArrayList<livre> lister(Class c, String qury)
	{
		ArrayList<livre> tb = new ArrayList<livre>();
		try {
		String ll[] = qury.split(" ");
		int idx=java.util.Arrays.asList(ll).indexOf("from");
		String al = ll[idx+1];
		
		Class cc = c;
		Annotation an = cc.getAnnotation(table.class);
		table ta = (table)an;
		if(ta.name().equals(al)){
			mres = stm.executeQuery(qury);
		
		while(mres.next())
		{
		tb.add(new livre(mres.getString("titre"),mres.getString("auteur"),mres.getInt("annee")));

		}
		
		return tb;
		  }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tb;
	}

}
