package observtrainn;

public class observetraining {

	public static void main(String[] args) {
		System.out.println("obs 2");
		 
		stock s = new stock();
		stockprice p = new stockprice(new evprice(s),s);
	//	s.remove(new evprice(s), p);
		s.setprice(14);
		s.setprice(11);
		s.setprice(11);
		s.setprice(10);
	}

}
