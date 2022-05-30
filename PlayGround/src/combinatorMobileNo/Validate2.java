package combinator.combinatorMobileNo;

import java.util.List;
import java.util.function.BiFunction;

public interface Validate2 extends BiFunction<String, List<String>, List<String>> {

	static Validate2 isPhoneNumberValid() {
		return (phNo, errors) -> {
			boolean a = (phNo.startsWith("+0") ? false : errors.add("+0 Validation"));
			return errors;
		};
	}

	static Validate2 isPhoneNumberNull() {
		return (phNo, errors) -> {
			boolean a = phNo.isBlank() ? errors.add("Blank Error") : false;
			return errors;
		};
	}

	static Validate2 isPhoneNumberNumeric() {
		return (phNo, errors) -> {
			boolean a = phNo.matches("(0/91)?[7-9][0-9]{9}") ? false : errors.add("Non numeric error");
			return errors;
		};
	}

	static Validate2 isPhoneNumberLength() {
		return (phNo, errors) -> {
			boolean a = phNo.length() == 10 ? false : errors.add("Length should be 10 digits");
			return errors;
		};
	}

	default Validate2 and(Validate2 other) {
		return (phNo, errors) -> {
			this.apply(phNo, errors);
			return other.apply(phNo, errors);
		};
	}
}
