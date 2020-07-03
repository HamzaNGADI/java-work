import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RobiTest {

	@Test
	public void test() {
		ArrayList<String> prog = new ArrayList<String>();
		//prog.add("beep high");
		prog.add("moveright");
		prog.add("movetop");
		prog.add("beep medium");
		prog.add("move 54 9");
		prog.add("beep"); 
		
		//prog.add("move 11 4");
		Robi robi = new Robi();
		robi.run(prog);
		assertTrue((robi.trace()).equals("[MOVERIGHT][MOVETOP][BEEP][MOVE][BEEP]"));
	}

}
  