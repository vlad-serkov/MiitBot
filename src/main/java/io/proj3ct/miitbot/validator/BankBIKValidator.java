package io.proj3ct.miitbot.validator;

public class BankBIKValidator {
    public static void validate( String bankBIK) throws BankBIKValidator.IllegalBankBIKException {
        if (bankBIK.length()!=9) {
            throw new BankBIKValidator.IllegalBankBIKException("Данный введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalBankBIKException extends Exception {
        public IllegalBankBIKException(String message) {
            super(message);
        }
    }
}
