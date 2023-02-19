package io.proj3ct.miitbot.validator;

public class SerialPassportValidator {


    public static void validate( String serialPassport) throws IllegalSerialPassportException {
        if (serialPassport.length()!=11 || serialPassport.charAt(4)!=' ') {
            throw new IllegalSerialPassportException("Данные введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalSerialPassportException extends Exception {
        public IllegalSerialPassportException(String message) {
            super(message);
        }
    }
}

