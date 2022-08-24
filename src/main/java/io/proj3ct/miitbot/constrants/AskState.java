package io.proj3ct.miitbot.constrants;

/**Возможные состояния бота
 */

public enum AskState {
    ASK_FULL_NAME("Введите ваше ФИО в именительном падеже (Может содержать только русские буквы и знак «-». Например, Иванов Иван Иванович)"),
    ASK_DATE_OF_BIRTHDAY("Введите дату вашего рождения (Например, 2002-10-13, ГОД-МЕСЯЦ-ДЕНЬ)"),
    ASK_MATPOMOCH("Выберите тип матпомощи"),

    ASK_INSTITUTE("Введите название вашего института (Например, ИУЦТ)"),
    ASK_GROUP("Введите название группы(Например УВА-311)"),
    ASK_COURSE_NUMBER("Введите номер курса"),
    ASK_ADDRESS("Введите ваш адрес регистрации по месту жительства "),

    ASK_PHONE_NUMBER("Введите ваш номер телефона (Например, +79279999999)"),
    ASK_SERIAL_OF_PASSPORT("Введите серию и номер паспорта (Может содержать только цифры. ЗАПИСЫВАЙТЕ БЕЗ ПРОБЕЛА)"),
    ASK_PASSPORT_ISSUED("Введите кем и когда был выдан паспорт "),
    ASK_INN("Введите ваш ИНН"),
    ASK_BANK_BOOK("Введите номер вашего лицевого счёта "),
    ASK_BANK_BIK("Введите БИК банка "),
    ASK_UNION_CARD("Введите номер профсоюзного билета"),
    FINISH("finish");

    private final String message;

    AskState(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
