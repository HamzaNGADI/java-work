package observtrainn;

public class stockprice implements observer{

	static int id=0;
	stockobs stk;
	int prii, ido;
	
	public stockprice(event ev, stockobs obs)
	{
		ido = ++id;
		
		stk = obs;
		
		stk.register(ev,this);
	}
	
	@Override
	public void update(int price) {
		if(prii==price) System.out.print("no change, ");
		prii=price;
		System.out.println("updated : "+prii);
	}
	
	
}
