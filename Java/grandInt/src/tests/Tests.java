package tests;

import org.junit.*;

import grandsentiers.*;

public class Tests {
	
	public GrandEntier g0;
	public GrandEntier g1;
	public GrandEntier g6;
	public GrandEntier g65;
	public GrandEntier g45978;
	public GrandEntier g45978b;
	public GrandEntier g45878;
	public GrandEntier g458781;
	public GrandEntier g5878;
	
	@Before
	public void initVal() {
		this.g0      = new ListeGrandEntier();
		this.g1      = new ListeGrandEntier(1);
		this.g6      = new ListeGrandEntier(6);
		this.g65     = new ListeGrandEntier(65);
		this.g45978  = new ListeGrandEntier(45978);
		this.g45978b = new ListeGrandEntier(45978);
		this.g45878  = new ListeGrandEntier(45878);
		this.g458781 = new ListeGrandEntier(458781);
		this.g5878   = new ListeGrandEntier(5878);
	}

	@Test
	public void testCompareToZero() {
		Assert.assertTrue(g0.compareTo(g1) < 0);
		Assert.assertTrue(g1.compareTo(g0) > 0);
		Assert.assertTrue(g0.compareTo(g45978) < 0);
		Assert.assertTrue(g45978.compareTo(g0) > 0);
	}

	@Test
	public void testCompareTo45978() {
		Assert.assertTrue(g45978.compareTo(g45978b) == 0);
		Assert.assertTrue(g45978.compareTo(g45878) > 0);
		Assert.assertTrue(g45978.compareTo(g458781) < 0);
		Assert.assertTrue(g45978.compareTo(g5878) > 0);
	}
	
	@Test
	public void testAddToZero() {
		Assert.assertEquals(g0.add(g45978).toString(), "45978");
		Assert.assertEquals(g45978.add(g0).toString(), "45978");
	}

	@Test
	public void testAddTo458781() {
		Assert.assertEquals(g458781.add(g5878).toString(), "464659");
		Assert.assertEquals(g5878.add(g458781).toString(), "464659");
	}

	@Test
	public void testMultToZero() {
		Assert.assertEquals(g0.mult(g45978).toString(), "0");
		Assert.assertEquals(g45978.mult(g0).toString(), "0");
	}

	@Test
	public void testMultToUn() {
		Assert.assertEquals(g1.mult(g45978).toString(), "45978");
		Assert.assertEquals(g45978.mult(g1).toString(), "45978");
	}

	@Test
	public void testMultTo458781() {
		Assert.assertEquals(g458781.mult(g5878).toString(), "2696714718");
		Assert.assertEquals(g5878.mult(g458781).toString(), "2696714718");
	}

	@Test
	public void testFactorielle() {
		Assert.assertEquals(ListeGrandEntier.factorielle(g0).toString(), "1");
		Assert.assertEquals(ListeGrandEntier.factorielle(g1).toString(), "1");
		Assert.assertEquals(ListeGrandEntier.factorielle(g6).toString(), "720");
		Assert.assertEquals(ListeGrandEntier.factorielle(g65).toString(), "8247650592082470666723170306785496252186258551345437492922123134388955774976000000000000000");
	}  

}
