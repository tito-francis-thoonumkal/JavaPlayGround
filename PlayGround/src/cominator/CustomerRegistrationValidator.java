package combinator.cominator;

import static combinator.cominator.CustomerRegistrationValidator.ValidationResult.EMAIL_NOT_VALID;
import static combinator.cominator.CustomerRegistrationValidator.ValidationResult.IS_NOT_AN_ADULT;
import static combinator.cominator.CustomerRegistrationValidator.ValidationResult.PHONE_NUMBER_NOT_VALID;
import static combinator.cominator.CustomerRegistrationValidator.ValidationResult.SUCCESS;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

import combinator.cominator.CustomerRegistrationValidator.ValidationResult;

public interface CustomerRegistrationValidator extends Function<Customer, ValidationResult> {

	static CustomerRegistrationValidator isEmailValid() {
		return customer -> {
			System.out.println("running email validation");
			return customer.getEmail().contains("@") ? SUCCESS : EMAIL_NOT_VALID;
		};
	}

	static CustomerRegistrationValidator isPhoneNumberValid() {
		return customer -> customer.getPhoneNumber().startsWith("+0") ? SUCCESS : PHONE_NUMBER_NOT_VALID;
	}

	static CustomerRegistrationValidator isAnAdult() {
		return customer -> Period.between(customer.getDob(), LocalDate.now()).getYears() > 16 ? SUCCESS
				: IS_NOT_AN_ADULT;
	}

	default CustomerRegistrationValidator and(CustomerRegistrationValidator other) {
		return customer -> {
			ValidationResult result = this.apply(customer);
			return result.equals(SUCCESS) ? other.apply(customer) : result;
		};
	}

	enum ValidationResult {
		SUCCESS, PHONE_NUMBER_NOT_VALID, EMAIL_NOT_VALID, IS_NOT_AN_ADULT
	}
}