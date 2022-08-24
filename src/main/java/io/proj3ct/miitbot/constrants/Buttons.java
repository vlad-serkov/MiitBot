package io.proj3ct.miitbot.constrants;

import lombok.Getter;

@Getter
public enum Buttons {

    BUTTON_ORPHAN("1 Cироты", "buttonOrphan", "path", "message"),
    BUTTON_DISABLED_PERSON("2 Инвалиды", "buttonDisabledPerson", "path", "message"),
    BUTTON_RAISING_CHILD("3 Воспитывающий ребенка", "buttonRaisingChild", "path", "message"),
    BUTTON_SINGLE_PARENT("4 Родитель одиночка", "buttonSingleParent", "path", "message"),
    BUTTON_LOSS_OF_SURVIVOR("5 Потеря кормильца ", "buttonLossOfSurvivor", "path", "message"),
    BUTTON_DISABLED_PARENTS_AND_PENSIONERS(
            "6 Родители-инвалиды и пенсионеры ",
            "buttonDisabledParentsAndPensioner",
            "src/main/resources/6_Roditeli-invalidy_i_pensionery_Versia_1 (1).doc",
            "Поздравляем вас с заполнением заявления на получение материальной помощи. Теперь вам осталось подготовить следующие копии документов:\n" +
            "- Копия паспорта с пропиской\n" +
            "- Скриншот подтверждения об оплате членских взносов\n" +
            "- Справка о доходах ИЛИ 2-НДФЛ ИЛИ Справка, подтверждающая факт установления пенсии\n" +
            "- Копия пенсионного удостоверения родителей\n" +
            "- Снимок экрана с указанными реквизитами: БИК-банка и номер лицевого счета\n" +
            "- Копия свидительства о рождении ребёнка(студента)\n" +
            "Эти копии документов вам нужно будет принести в кабинет вашего ФПОС, у каждого института они разные. ИУЦТ-1522, ИЭФ-3602, ИТТСУ-2323, ЮИ-6004, ИПСС-7719, ИМТК-12307\n"),
    BUTTON_LARGE_FAMILIES("7 Многодетные семьи ", "buttonLargeFamilies", "path", "message"),
    BUTTON_PARTICIPANTS_IN_HOSTILITIES("8 Участники военных действий", "buttonParticipantsInHostilities", "path", "message"),
    BUTTON_DISPENSER_REGISTRATION("9 Диспансерный учёт", "buttonDispenserRegistration", "path", "message"),
    BUTTON_CHERNOBYL_STUDENT("10 Студент чернобылец ", "buttonChernobylStudent", "path", "message"),
    BUTTON_DIVORCE_OF_PARENTS("11 Развод родителей", "buttonDivorceOfParent", "path", "message"),
    BUTTON_LIVING_IN_HOSTEL("12 Проживающий в общежитии", "buttonLivingInHostel", "path", "message"),
    BUTTON_NOT_RECEIVING_SCHOLARSHIP("13 Не получающие стипендию ", "buttonNotReceivingScholarship", "path", "message");



    private final String text;
    private final String callback;
    private final String path;
    private final String message;

    Buttons(String text, String callback, String path, String message) {
        this.text = text;
        this.callback = callback;
        this.path = path;

        this.message = message;
    }
    }
