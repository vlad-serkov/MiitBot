package io.proj3ct.miitbot.validator;

public class InnValidator {


    public static void validate( String inn) throws IllegalInnException {
        if (inn.length()!=12) {
            throw new IllegalInnException("Данные введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalInnException extends Exception {
        public IllegalInnException(String message) {
            super(message);
        }
    }
}

