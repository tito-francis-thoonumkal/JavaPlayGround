package combinator.product;

import java.util.List;
import java.util.function.BiFunction;

public interface PriceValidateAndCalculate extends BiFunction<Product, List<String>, List<String>> {

	static PriceValidateAndCalculate isPriceNull() {
		return (product, errors) -> {
			if (product.price==null)
				errors.add("Blank Error Price");
			return errors;
		};
	}

	static PriceValidateAndCalculate isTaxRateNull() {
		return (product, errors) -> {

			if (product.taxRate==null)
				errors.add("Blank Error tax");
			return errors;
		};
	}

	static PriceValidateAndCalculate isDiscountRateNull() {
		return (product, errors) -> {

			if (product.discountRate==null)
				errors.add("Blank Error discount");
			return errors;
		};
	}

	static PriceValidateAndCalculate calcDiscount() {
		return (product, errors) -> {
			product.discountPrice = product.price * product.discountRate/100;
			return errors;
		};
	}

	static PriceValidateAndCalculate calcTax() {
		return (product, errors) -> {
			product.taxPrice = product.price * product.taxRate/100;
			return errors;
		};
	}

	static PriceValidateAndCalculate calcNetPrice() {
		return (product, errors) -> {
			product.netPrice = product.price + product.taxPrice - product.discountPrice;
			return errors;
		};
	}

	default PriceValidateAndCalculate and(PriceValidateAndCalculate other) {
		return (product, errors) -> {
			this.apply(product, errors);
			return other.apply(product, errors);
		};
	}

//	default MobileNoValidator mobilenoValidor (String product, List<String>errors) {
//		List<String> results = new ArrayList<String>();
//		isPriceNull.and(isPhoneNumberNull).and(isPhoneNumberNumeric).apply("9999aa", results);
//		return null;
//	};
}