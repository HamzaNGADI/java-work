package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ensemble.Comparateur;
import ensemble.ComparateurInteger;
import ensemble.TabEnsemble;

public class testmin {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testmin() {
		TabEnsemble t = new TabEnsemble();
		t.add(2);
		t.add(3);
		t.add(4);
		TabEnsemble ta = new TabEnsemble();
		t.add(2);
		ComparateurInteger c = new ComparateurInteger();
		Object x = t.min(c);
		
		assertEquals(2,x);
		
	}
	
	@Test
	public void testminnot() {
		TabEnsemble t = new TabEnsemble();
		t.add(2);
		t.add(3);
		t.add(4);
		TabEnsemble ta = new TabEnsemble();
		t.add(4);
		ComparateurInteger c = new ComparateurInteger();
		Object x = t.min(c);
		
		assertTrue(!((Integer)x==4));
		
	}

}
