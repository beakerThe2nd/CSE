package cse12pa1student;

import java.util.Collection;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BasketTest {

	public static Collection<Object[]> BAGNUMS =
			Arrays.asList(new Object[][] { {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12} });
	private int bagType;

	public BasketTest(int bagType) {
		super();
		this.bagType = bagType;
	}

	@Parameterized.Parameters(name = "Basket{index}")
	public static Collection<Object[]> bags() {
		return BAGNUMS;
	}
	
	private Basket makeBasket() {
		switch(this.bagType) {
			case 0: return new Basket0();
			case 1: return new Basket1();
			case 2: return new Basket2();
			case 3: return new Basket3();
			case 4: return new Basket4();
			case 5: return new Basket5();
			case 6: return new Basket6();
			case 7: return new Basket7();
			case 8: return new Basket8();
			case 9: return new Basket9();
			case 10: return new Basket10();
			case 11: return new Basket11();
			case 12: return new Basket12();
		}
		return null;
	}
	
	@Test
	public void addedHasCount1() {
		Basket basketToTest = makeBasket();
		Item i = new Item("Shampoo", 5);
		basketToTest.addToBasket(i);
		basketToTest.addToBasket(null);
		assertEquals(2, basketToTest.count());
	}
	@Test
	public void  addDuplicate(){
		Basket basketToTest = makeBasket();
		Item d1 = new Item("meat", 10);
		Item d2 = new Item("sugar", 3);
		basketToTest.addToBasket(d1);
		basketToTest.addToBasket(d2);
		assertEquals(2, basketToTest.count());
		basketToTest.addToBasket(d1);
		basketToTest.addToBasket(d2);
		assertEquals(4, basketToTest.count());
		assertEquals(2, basketToTest.countItem(d1));
		assertEquals(2, basketToTest.countItem(d2));
	}
	@Test
	public void addMultiple(){
		Basket basketToTest = makeBasket();
		Item d1 = new Item("sugar", 50);
		Item d2 = new Item("egg white", 2);
		Item d3 = new Item("unsalted butter", 25);
		Item d4 = new Item("flavor", 75);
		Item d5 = new Item("salt", 2);
		Item d6 = new Item("baking powder", 2);
		basketToTest.addToBasket(d1);		
		basketToTest.addToBasket(d2);		
		basketToTest.addToBasket(d3);		
		basketToTest.addToBasket(d4);		
		basketToTest.addToBasket(d5);		
		basketToTest.addToBasket(d6);		
		assertEquals(6, basketToTest.count());
		assertEquals(1, basketToTest.countItem(d6));
	}
	@Test
	public void addALot(){
		Basket basketToTest = makeBasket();
		Item d = new Item("sugar", 50);
		for (int i = 0; i < 100; i ++){
			basketToTest.addToBasket(d);
		}
		assertEquals(100, basketToTest.count());
		Item d2 = new Item("beef", 100);
		basketToTest.addToBasket(d2);
		assertEquals(1, basketToTest.countItem(d2));
		basketToTest.removeAllFromBasket(d);
		assertEquals(1, basketToTest.count());
	}
	@Test 
	public void checkCount(){
		Basket basketToTest = makeBasket();
		Item d = new Item("meat", 10);
		basketToTest.addToBasket(d);
		basketToTest.addToBasket(d);
		basketToTest.addToBasket(new Item("meat", 10));
		assertEquals(3, basketToTest.countItem(d));
	}
	@Test
	public void checkPrice(){
		Basket basketToTest = makeBasket();
		Item d1 = new Item("meat", 10);
		Item d2 = new Item("egg", 5);
		basketToTest.addToBasket(d1);
		basketToTest.addToBasket(d2);
		assertEquals(15, basketToTest.totalCost());
		assertEquals(15, basketToTest.countItem(d1) * d1.priceInCents + 
						 basketToTest.countItem(d2) * d2.priceInCents);
	}
	@Test
	public void checkTotalCost(){
		Basket basketToTest = makeBasket();
		Item d1 = new Item("meat", 10);
		Item d2 = new Item("egg", 5);
		basketToTest.addToBasket(d1);
		basketToTest.addToBasket(d2);
		basketToTest.removeFromBasket(d2);
		assertEquals(10, basketToTest.totalCost());
	}
	@Test
	public void removeOne(){
		Basket basketToTest = makeBasket();
		Item i = new Item("egg", 20);
		Item f = new Item("caramel", 50);
		basketToTest.addToBasket(i);
		basketToTest.addToBasket(i);
		basketToTest.addToBasket(f);
		boolean boo1 = basketToTest.removeFromBasket(i);
		assertEquals(1, basketToTest.countItem(i));
		assertEquals(2, basketToTest.count());
		boolean boo2 = basketToTest.removeFromBasket(f);
		assertEquals(0, basketToTest.countItem(f));
		assertEquals(true, boo1);
		assertEquals(true, boo2);	
		basketToTest.addToBasket(null);	
		boolean boo3 = basketToTest.removeFromBasket(null);
		assertEquals(true, boo3);
	}
	@Test
	public void addRemoveSame(){
		Basket basketToTest = makeBasket();
		Item i = new Item("egg", 20);
		basketToTest.addToBasket(i);
		basketToTest.addToBasket(i);
		basketToTest.removeFromBasket(i);
		assertEquals(1, basketToTest.countItem(i));
	}
	@Test
	public void checkEmpty(){
		Basket basketToTest = makeBasket();
		Item i = new Item("book", 50);
		basketToTest.addToBasket(i);
		assertEquals(1, basketToTest.count());
		basketToTest.empty();
		assertEquals(0, basketToTest.count());
	}
	@Test
	public void checkEmpty2(){
		Basket basketToTest = makeBasket();
		assertEquals(0, basketToTest.count());
	}
	@Test
	public void  removeAll(){
		Basket basketToTest = makeBasket();
		Item d1 = new Item("meat", 10);
		Item d2 = new Item("egg", 5);
		basketToTest.addToBasket(d1);
		basketToTest.addToBasket(d2);
		basketToTest.addToBasket(d1);
		basketToTest.addToBasket(d2);
		basketToTest.addToBasket(null);
		boolean boo = basketToTest.removeAllFromBasket(d1);
		assertEquals(2, basketToTest.countItem(d2));
		assertEquals(0, basketToTest.countItem(d1));
		assertEquals(true, boo);
		assertEquals(true, basketToTest.removeAllFromBasket(null));


	}
}
