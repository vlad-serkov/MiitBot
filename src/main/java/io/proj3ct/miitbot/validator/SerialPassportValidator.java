package io.proj3ct.miitbot.validator;

public class SerialPassportValidator {


    public static void validate( String serialPassport) throws IllegalSerialPassportException {
        if (serialPassport.charAt(4)!=' ' || serialPassport.length()==11) {
            throw new IllegalSerialPassportException("Данные введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalSerialPassportException extends Exception {
        public IllegalSerialPassportException(String message) {
            super(message);
        }
    }
}

