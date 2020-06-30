package builder;
/**
 * Un utilisateur 
 * @author riviere
 *
 */
public class User {

	// paramètres obligatoires
	private final String firstName;
	private final String lastName;
	// paramètres optionnels
	private final int age;
	private final String phone;
	private final String address;
	
			
	private User(UserBuilder builder){
		this.firstName=builder.firstName;
		this.lastName=builder.lastName;
		this.address=builder.address;
		this.phone=builder.phone;
		this.age=builder.age;		
	}
	
	public String toString(){
		return firstName+" "+lastName+" "+age+" "+phone+" "+address;
	}
	
	/**
	 * Classe statique - DP Builder
	 * @author riviere
	 *
	 */
	public static class UserBuilder{
		private final String firstName;
		private final String lastName;
		private int age;
		private String phone;
		private String address;
		
		public UserBuilder(String firstName, String lastName){
			this.firstName=firstName;
			this.lastName=lastName;
		}
		
		public UserBuilder age(int age){
			this.age=age;
			return this;
		}
		
		public UserBuilder phone(String phone){
			this.phone=phone;
			return this;
		}
		
		public UserBuilder adr(String ad)
		{
			this.address = ad;
			return this;
		}
		public User build()
		{
			return new User(this);
		}
	}
	
}
