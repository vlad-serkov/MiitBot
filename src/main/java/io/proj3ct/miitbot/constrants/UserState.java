package io.proj3ct.miitbot.constrants;

/**Возможные состояния бота
 */

public enum UserState {
    ASK_NAME("name"),
    ASK_AGE("age"),
    ASK_LOL("LOOL"),
    ASK_INSTITUTE("institute"),
    ASK_MATPOMOCH("matPomoch"),
    FINISH("finish");

    private final String message;

    UserState(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
