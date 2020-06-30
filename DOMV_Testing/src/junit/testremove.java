package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ensemble.TabEnsemble;

public class testremove {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testremovefalse() {
		TabEnsemble t = new TabEnsemble();
		t.add(1);
		t.add(2);
		t.add(3);
		t.remove(2);
		TabEnsemble tra = new TabEnsemble();
		tra.add(1);
		tra.add(4);
		boolean b = t.equals(tra);
		assertTrue(b==false);
	}
	@Test
	public void testremove() {
		TabEnsemble t = new TabEnsemble();
		t.add(1);
		t.add(2);
		t.add(3);
		t.remove(2);
		TabEnsemble tr = new TabEnsemble();
		tr.add(1);
		tr.add(3);
		boolean b = t.equals(tr);
		assertTrue(b==true);
	}
	
}
