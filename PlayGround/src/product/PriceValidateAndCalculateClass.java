package combinator.product;

import static combinator.product.PriceValidateAndCalculate.calcDiscount;
import static combinator.product.PriceValidateAndCalculate.calcNetPrice;
import static combinator.product.PriceValidateAndCalculate.calcTax;
import static combinator.product.PriceValidateAndCalculate.isPriceNull;
import static combinator.product.PriceValidateAndCalculate.isTaxRateNull;
import static combinator.product.PriceValidateAndCalculate.isDiscountRateNull;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class PriceValidateAndCalculateClass {

	public void calc(Product t, List<String> u) {
		isPriceNull().and(isTaxRateNull()).and(isDiscountRateNull()).apply(t, u);
		if(u.isEmpty()) {
			calcTax().and(calcDiscount()).and(calcNetPrice()).apply(t, u);		
		}
		
	}
	
	Predicate<Double> isNUllCheck=doubNum-> (doubNum==null);
	
	BiConsumer<Product, List<String>> isTaxRateNullBi = (product, errors) -> {
		if (isNUllCheck.test(product.taxRate))
			errors.add("Blank Error tax");
	};
	BiConsumer<Product, List<String>> isPriceNullBi = (product, errors) -> {
		if (product.price==null)
			errors.add("Blank Error Price");
	};
	BiConsumer<Product, List<String>> isDiscountRateNullBi = (product, errors) -> {
		if (product.discountRate==null)
			errors.add("Blank Error discount");
	};
	BiConsumer<Product, List<String>> calcDiscountBi = (product, errors) -> {
		product.discountPrice = product.price * product.discountRate/100;
	};
	BiConsumer<Product, List<String>> calcTaxBi = (product, errors) -> {
		product.taxPrice = product.price * product.taxRate/100;
	};
	BiConsumer<Product, List<String>> calcNetPriceBi = (product, errors) -> {
		product.netPrice = product.price + product.taxPrice - product.discountPrice;
	};
	
	public void isTaxRateClass(Product product, List<String> errors){
			if (product.taxRate==null)
	
			errors.add("Blank Error tax");
	}
	
	public void isPriceNullClass(Product product, List<String> errors){
		if (product.price==null)
			errors.add("Blank Error Price");
	}
	public void isDiscountRateNullClass(Product product, List<String> errors){
		if (product.discountRate==null)
			errors.add("Blank Error discount");
	}
	
	
	public void  calcDiscountClass(Product product, List<String> errors) {
			product.discountPrice = product.price * product.discountRate/100;
	}

	public void  calcTaxClass(Product product, List<String> errors) {
			product.taxPrice = product.price * product.taxRate/100;
	}

	public void  calcNetPriceClass(Product product, List<String> errors) {
			product.netPrice = product.price + product.taxPrice - product.discountPrice;
	}
}