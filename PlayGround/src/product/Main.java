package combinator.product;

import java.util.ArrayList;
import java.util.List;
import static combinator.product.PriceValidateAndCalculate.isPriceNull;;

public class Main {

	public static void main(String[] args) {

		PriceValidateAndCalculateClass calculateClass = new PriceValidateAndCalculateClass();
		Product product = new Product();
		product.price = 110.00;
		product.discountRate = 10.00;
		product.taxRate = 10.00;
		List<String> errors = new ArrayList<String>();
//    	 PriceValidateAndCalculate.isPriceNull(product,errors).apply(product,errors);
		calculateClass.calc(product, errors);
		System.out.println(product.netPrice);
		System.out.println(product.discountPrice);
		System.out.println(product.taxPrice);
		System.out.println(errors.toString());

		product = new Product();
		product.price = 110.00;
//		product.discountRate = 10.00;
//		product.taxRate = 10.00;
		errors = new ArrayList<String>();
		calculateClass.isTaxRateNullBi.andThen(calculateClass.isPriceNullBi)
				.andThen(calculateClass.isDiscountRateNullBi).accept(product, errors);
		if(errors.isEmpty())
			calculateClass.calcDiscountBi.andThen(calculateClass.calcTaxBi)
				.andThen(calculateClass.calcNetPriceBi).accept(product, errors);
		System.out.println(product.netPrice);
		System.out.println(product.discountPrice);
		System.out.println(product.taxPrice);
		System.out.println(errors.toString());
	}
}