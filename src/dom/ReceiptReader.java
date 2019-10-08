package dom;

import java.util.Arrays;
import java.util.Scanner;

public class ReceiptReader{
	public static final String SPACE = " ";
	public static final String AT = "at";

	public static Receipt read(String rawInfo){
		Scanner scanner = new Scanner(rawInfo);
		Receipt result = new Receipt();
		while (scanner.hasNextLine()) {
		  String line = scanner.nextLine();
		  Scanner lineScanner = new Scanner(line);
		  // Read quantity
		  int quantity = 0;
		  if(lineScanner.hasNextBigInteger()) {
			  quantity = lineScanner.nextInt();
		  }else{
			  lineScanner.close();
			  continue;
		  }		  
		  // Read product description
		  String description = "";		  
		  while(lineScanner.hasNext()) {
			  description += SPACE + lineScanner.next();
			  if(description.endsWith(" at")) {
				  break;
			  }
		  }
		// Price buffer
		  Double price = null;
		  if(lineScanner.hasNext()) {
			  price = Double.valueOf(lineScanner.next());
		  }
		  if(description.isEmpty() || price == null) {
			  lineScanner.close();
			  continue;
		  }
		  String name = extractName(description);
		  Product product = new Product(name, extracType(name), description.contains(Product.IMPORTED), quantity, price);
		  result.addProduct(product);
		  lineScanner.close();
		}
		scanner.close();
		return result;		
	}

	private static ProductType extracType(String name) {
		return Arrays.asList(ProductType.values()).stream()
				.filter(type -> type.checkProductType(name))
				.findFirst()
				.orElse(ProductType.OTHER);
	}

	private static String extractName(String description) {
		description = description.substring(1);
		int endInd = description.lastIndexOf(AT);
		description = description.substring(0, endInd-1);
		if(description.contains(Product.IMPORTED)) {
			return description.replace(Product.IMPORTED+" ", "");
		}else {
			return description;
		}
	}
}
