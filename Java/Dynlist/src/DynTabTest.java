import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DynTabTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testadd() {
		DynTab<String> t = new DynTab<String>();
		assertTrue(t.size()==1);
		
		t.add("a");
		assertTrue(t.get(0).equals("a"));
		t.add("b");
		assertTrue(t.get(1).equals("b"));
		t.add("c");
		assertTrue(t.get(2).equals("c"));
		assertTrue(t.size()==3); 
		
		t.add(0, "x");
		assertTrue(t.get(0).equals("x")&& t.get(1).equals("a"));
		t.add(2, "y");
		assertTrue(t.get(2).equals("y") && t.get(3).equals("b"));
		t.add(4, "z");
		assertTrue(t.get(4).equals("z") && t.get(5).equals("c"));
		t.add(6, "w");
		assertTrue(t.get(6).equals("w") && t.get(5).equals("c"));
		t.aff();		
		
	}
	@Test
	public void testremove() {
		DynTab<String> t = new DynTab<String>();
		assertTrue(t.size()==1);
		
		t.add("a");
		assertTrue(t.get(0).equals("a"));
		t.add("b");
		assertTrue(t.get(1).equals("b"));
		t.add("c");
		assertTrue(t.get(2).equals("c"));
		assertTrue(t.size()==3); 
		
		t.add(0, "x");
		assertTrue(t.get(0).equals("x")&& t.get(1).equals("a"));
		t.add(2, "y");
		assertTrue(t.get(2).equals("y") && t.get(3).equals("b"));
		t.add(4, "z");
		assertTrue(t.get(4).equals("z") && t.get(5).equals("c"));
		t.aff();		
		
		t.remove(0);
		assertTrue(t.get(0).equals("a") && t.get(t.size()-1) == null);
		t.remove(2);
		assertTrue(t.get(2).equals("z") && t.get(t.size()-1) == null);
		t.remove(3);
		assertTrue(t.get(3)==null && t.get(t.size()-1) == null);
	
		t.aff();	  
	}
	@Test
	public void testremoveobj() {
		DynTab<String> t = new DynTab<String>();
		assertTrue(t.size()==1);
		
		t.add("a");
		assertTrue(t.get(0).equals("a"));
		t.add("b");
		assertTrue(t.get(1).equals("b"));
		t.add("c");
		assertTrue(t.get(2).equals("c"));
		assertTrue(t.size()==3); 
		
		t.add(0, "x");
		assertTrue(t.get(0).equals("x")&& t.get(1).equals("a"));
		t.add(2, "y");
		assertTrue(t.get(2).equals("y") && t.get(3).equals("b"));
		t.add(4, "z");
		assertTrue(t.get(4).equals("z") && t.get(5).equals("c"));
		t.aff();		
		
		t.remove("x");
		assertTrue(t.get(0).equals("a") && t.get(t.size()-1) == null);
		t.remove("b");
		assertTrue(t.get(2).equals("z") && t.get(t.size()-1) == null);
		t.remove("c");
		assertTrue(t.get(3)==null && t.get(t.size()-1) == null);
	
		t.aff();	  
		t.add("x");
		assertTrue(t.get(3).equals("x"));
		t.aff();	  

		t.add(3, "f");
		assertTrue(t.get(3).equals("f") );
		t.add(0, "l");
		assertTrue(t.get(0).equals("l") );
		t.aff();
		t.add(6, "y");
		assertTrue(t.get(6).equals("y") );
		
		t.aff();
		assertTrue(t.indexOf("y")==2 && t.lastIndexOf("y")==6);

	}
	@Test
	public void testclearcap() {
		DynTab<String> t = new DynTab<String>();
		assertTrue(t.isEmpty());
		assertTrue(t.size()==1);
		t.add("a");
		
		assertTrue(t.size()==1);
		assertTrue(!t.isEmpty());
		
		t.add("b");
		assertTrue(t.size()==2);
		assertTrue(t.contains("a")&& t.contains("b"));
		assertTrue(!t.contains("z"));
		
		Object[] tt=t.toArray();
		assertTrue(tt[0].equals("a")&& tt[1].equals("b") && tt.length == t.size());

		//t.remove(0); t.remove(1);
		t.clear();
		assertTrue(t.isEmpty());
		 
	}

}
  