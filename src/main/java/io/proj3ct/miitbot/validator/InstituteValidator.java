package io.proj3ct.miitbot.validator;

public class InstituteValidator {


    public static void validate( String institute) throws IllegalInstituteException {
        if (!(institute.equals("ИУЦТ") || institute.equals("ЮИ") || institute.equals("ИПСС") || institute.equals("ИТТСУ") || institute.equals("ИЭФ") || institute.equals("ИМТК"))) {
            throw new IllegalInstituteException("Данные введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalInstituteException extends Exception {
        public IllegalInstituteException(String message) {
            super(message);
        }
    }
}

