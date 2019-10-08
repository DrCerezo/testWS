package dom;

import java.util.Locale;

/**
 * Product representation that keeps the name, the product type,
 * if is imported, the amount of products, and the price
 * @author Dani
 *
 */
public class Product {
	public static final String IMPORTED = "imported";
	
	private String name;
	private ProductType productType;
	private boolean imported;
	private int amount;
	private double totalPrice;
	
	public Product(String name, ProductType productType, boolean imported, int amount, double totalPrice) {
		super();
		this.name = name;
		this.productType = productType;
		this.imported = imported;
		this.amount = amount;
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	private double getTaxPercent() {
		double result = 0.0;
		if(!productType.isExempt()) {
			result += 0.10;
		}
		if(imported) {
			result += 0.05;
		}
		return result;
	}
	
	/**
	 * Calculate the total price with the applied taxes, round it to the nearest 0.05
	 * @return total price with the applied taxes
	 */
	public double getTaxedPrice() {		
		return roundToFraction(totalPrice*amount*(getTaxPercent()+1.0));		
	}
	
	/**
	 * Calculate the taxes part from the total price with the applied taxes, round it to the nearest 0.05
	 * @return taxes part of the price
	 */
	public double getTaxedPart() {
		return roundToFraction(totalPrice*amount*getTaxPercent());
	}
	
	public static double roundToFraction(double x) {
		Locale.setDefault(Locale.US);
		String formated = String.format("%.2f", x);
		if(formated.matches(".*[1-4]$")) {
			return Double.parseDouble(formated.substring(0,formated.length()-1).concat("5"));
		}
		return Double.parseDouble(formated);
	}
}
