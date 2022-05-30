package combinator.combinatorMobileNo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public interface MobileNoValidator extends BiFunction<String, List<String>, Void> {

	static MobileNoValidator isPhoneNumberValid = (phNo, errors) -> {
		if (phNo.startsWith("+0"))
			errors.add("+0 Validation");
		return null;
	};

	static MobileNoValidator isPhoneNumberNull = (phNo, errors) -> {
		if (phNo.isBlank())
			errors.add("Blank Error");
		return null;
	};

	static MobileNoValidator isPhoneNumberNumeric = (phNo, errors) -> {
		if (phNo.matches("(0/91)?[7-9][0-9]{9}"))
			errors.add("Non numeric error");
		return null;
	};

	default MobileNoValidator and(MobileNoValidator other) {
		return (phNo, errors) -> {
			return other.apply(phNo, errors);
		};
	}

	default MobileNoValidator mobilenoValidor (String phNo, List<String>errors) {
		List<String> results = new ArrayList<String>();
		isPhoneNumberValid.and(isPhoneNumberNull).and(isPhoneNumberNumeric).apply("9999aa", results);
		return null;
	};
}