package cominator;

import static cominator.CustomerRegistrationValidator.isAnAdult;
import static cominator.CustomerRegistrationValidator.isEmailValid;
import static cominator.CustomerRegistrationValidator.isPhoneNumberValid;

import java.time.LocalDate;

import cominator.CustomerRegistrationValidator.ValidationResult;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(
                "Alice",
                "alice@gmail.com",
                "+0898787879878",
                LocalDate.of(2015, 1,1)
        );

//        System.out.println(new CustomerValidatorService().isValid(customer));

        // if valid we can store customer in db

        // Using combinator pattern
        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAnAdult())
                .apply(customer);

        System.out.println(result);

        if (result != ValidationResult.SUCCESS) {
            throw new IllegalStateException(result.name());
        }

    }
}