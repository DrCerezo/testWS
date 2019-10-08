package dom;

import java.util.Arrays;
import java.util.List;

public enum ProductType {
	FOOD("Food", true, "chocolate"),
	BOOK("Book", true, "book"),
	MED("Medical", true, "pills"),
	OTHER("Other", false);
	
	
	private String name;
	private boolean exempt;
	private List<String> productNames;
	
	ProductType(String name, boolean exempt, String... productNames) {
		this.name = name;
		this.exempt = exempt;
		this.productNames = Arrays.asList(productNames);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isExempt() {
		return exempt;
	}

	public void setExempt(boolean exempt) {
		this.exempt = exempt;
	}
	
	public boolean checkProductType(String name) {
		return productNames.stream().anyMatch(product -> name.contains(product));
	}
	
}
