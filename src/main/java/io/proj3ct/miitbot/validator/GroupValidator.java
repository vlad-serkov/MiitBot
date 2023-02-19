package io.proj3ct.miitbot.validator;

public class GroupValidator  {


    public static void validate( String group) throws IllegalGroupException {
        if (group.charAt(3)!='-' || group.length()!=7) {
            throw new IllegalGroupException("Данный введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalGroupException extends Exception {
        public IllegalGroupException(String message) {
            super(message);
        }
    }
}
