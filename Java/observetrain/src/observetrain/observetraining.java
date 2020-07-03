package observetrain;

public class observetraining {

	public static void main(String[] args) {
		System.out.println("obs");
		
		stock s = new stock();
		stockprice p = new stockprice(s);
		s.setprice(14);
		s.setprice(11);
		s.setprice(11);
		s.setprice(10);
		
		String st = "fooby";
		String stt = "foo";
		stt+="by";
		System.out.println("st : "+st.equals(stt));
	}

}
