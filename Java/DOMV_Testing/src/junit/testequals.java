package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ensemble.TabEnsemble;

public class testequals {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testequalsnull() {
		TabEnsemble t = new TabEnsemble();
		t.add(1);
		t.add(2);
		TabEnsemble tattendu = new TabEnsemble();
		boolean b = t.equals(tattendu);
		assertTrue(b==false);
	}
	@Test
	public void testequals() {
		TabEnsemble t = new TabEnsemble();
		t.add(1);
		t.add(2);
		TabEnsemble ta = new TabEnsemble();
		ta.add(1);
		ta.add(2);
		boolean b = t.equals(ta);
		assertTrue(b==true);
	}
	@Test
	public void testequalsnotensemble() {
		TabEnsemble t = new TabEnsemble();
		t.add(1);
		t.add(2);
		ArrayList<Integer> tl= new ArrayList<Integer>();
		boolean b = t.equals(tl);
		assertTrue(b==false);
	}


}
