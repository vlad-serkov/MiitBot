package io.proj3ct.miitbot.validator;

public class PhoneNumberValidator {


    public static void validate( String phoneNumber) throws IllegalPhoneNumberException {
        if (phoneNumber.charAt(0)!='+' || phoneNumber.charAt(1)!='7' || phoneNumber.charAt(2)!='9' || phoneNumber.length()!=12) {
            throw new IllegalPhoneNumberException("Данные введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalPhoneNumberException extends Exception {
        public IllegalPhoneNumberException(String message) {
            super(message);
        }
    }
}

