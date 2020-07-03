package observetrain;

public class stockprice implements observer{

	static int id=0;
	stockobs stk;
	int prii, ido;
	
	public stockprice(stockobs obs)
	{
		ido = ++id;
		
		stk = obs;
		
		stk.register(this);
	}
	
	@Override
	public void update(int price) {
		prii=price;
		System.out.println("updated : "+prii);
	}
	
	
}
