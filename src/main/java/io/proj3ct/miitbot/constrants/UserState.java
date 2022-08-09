package io.proj3ct.miitbot.constrants;

/**Возможные состояния бота
 */

public enum BotState {
    ASK_NAME("name"),
    ASK_AGE("age"),
    ;


    private String message;

    private BotState(String message){
        this.message = message;
    }



    public String getMessage() {
        return message;
    }
}
