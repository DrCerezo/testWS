package dom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTest {

	@Test
	public void testCase1Product1() {
		Product product = new Product("book", ProductType.BOOK, false, 1, 12.49);
		assertEquals(12.49, product.getTaxedPrice(),0.01);
	}
	
	@Test
	public void testCase1Product2() {
		Product product = new Product("music CD", ProductType.OTHER, false, 1, 14.99);
		assertEquals(16.49, product.getTaxedPrice(),0.01);
	}
	
	@Test
	public void testCase2Product1() {
		Product product = new Product("imported box of chocolates", ProductType.FOOD, true, 1, 10.0);
		assertEquals(10.50, product.getTaxedPrice(),0.01);
	}
}
