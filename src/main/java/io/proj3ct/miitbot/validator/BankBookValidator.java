package io.proj3ct.miitbot.validator;

public class BankBookValidator {
    public static void validate( String bankBook) throws BankBookValidator.IllegalBankBookException {
        if (bankBook.length()!=20) {
            throw new BankBookValidator.IllegalBankBookException("Данный введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalBankBookException extends Exception {
        public IllegalBankBookException(String message) {
            super(message);
        }
    }
}
