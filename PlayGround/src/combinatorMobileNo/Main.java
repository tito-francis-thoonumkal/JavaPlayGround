package combinator.combinatorMobileNo;

//import static combinatorMobileNo.MobileNoValidator.mobilenoValidor;
import static combinator.combinatorMobileNo.Validate2.*;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

    	
//        System.out.println(new CustomerValidatorService().isValid(customer));

        // if valid we can store customer in db

        // Using combinator pattern
    	List<String> results = new ArrayList<String>();
        isPhoneNumberValid()
                .and(isPhoneNumberNull())
                .and(isPhoneNumberNumeric())
                .and(isPhoneNumberLength())
                .apply("9999aa",results);

//    	mobilenoValidor("111",results).apply("111",results);
        System.out.println(results);

    }
}