package io.proj3ct.miitbot.validator;

public class CourseValidator {


    public static void validate( String courseNumber) throws IllegalCourseException {
        int courseNumber1 = Integer.parseInt(courseNumber);
        if (courseNumber1<1 || courseNumber1>=6) {
            throw new IllegalCourseException("Данный введены не верно, попробуйте еще раз");
        }
    }

    public static class IllegalCourseException extends Exception {
        public IllegalCourseException(String message) {
            super(message);
        }
    }
}

