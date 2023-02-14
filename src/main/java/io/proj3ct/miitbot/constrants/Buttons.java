package io.proj3ct.miitbot.constrants;

import lombok.Getter;

@Getter
public enum Buttons {
    
   
    BUTTON_ORPHAN("1 Cироты", "buttonOrphan", "src/main/resources/Ворд заявления/1_Sirota.doc"),
    BUTTON_DISABLED_PERSON("2 Инвалиды", "buttonDisabledPerson", "src/main/resources/Ворд заявления/2_Invalidy.doc"),
    BUTTON_RAISING_CHILD("3 Воспитывающий ребенка", "buttonRaisingChild", "src/main/resources/Ворд заявления/3_Vospityvayuschiy_rebenka.doc"),
    BUTTON_SINGLE_PARENT("4 Родитель одиночка", "buttonSingleParent", "src/main/resources/Ворд заявления/4_Roditel_odinochka.doc"),
    BUTTON_LOSS_OF_SURVIVOR("5 Потеря кормильца ", "buttonLossOfSurvivor", "src/main/resources/Ворд заявления/5_Poterya_kormiltsa.doc"),
    BUTTON_DISABLED_PARENTS_AND_PENSIONERS(
            "6 Родители-инвалиды и пенсионеры ",
            "buttonDisabledParentsAndPensioner",
            "src/main/resources/Ворд заявления/6_Roditeli_invalidy_pensionery.doc"
            
            ),
    BUTTON_LARGE_FAMILIES("7 Многодетные семьи ", "buttonLargeFamilies", "src/main/resources/Ворд заявления/7_Mnogodetnye_semi.doc"),
    BUTTON_PARTICIPANTS_IN_HOSTILITIES("8 Участники военных действий", "buttonParticipantsInHostilities", "src/main/resources/Ворд заявления/8_uchastnik_voennykh_deystviy.doc"),
    BUTTON_DISPENSER_REGISTRATION("9 Диспансерный учёт", "buttonDispenserRegistration", "src/main/resources/Ворд заявления/9_Disp_uchet.doc"),
    BUTTON_CHERNOBYL_STUDENT("10 Студент чернобылец ", "buttonChernobylStudent", "src/main/resources/Ворд заявления/10_student-chernobylets.doc"),
    BUTTON_DIVORCE_OF_PARENTS("11 Развод родителей", "buttonDivorceOfParent", "src/main/resources/Ворд заявления/11_razvod.doc"),
    BUTTON_LIVING_IN_HOSTEL("12 Проживающий в общежитии", "buttonLivingInHostel", "src/main/resources/Ворд заявления/12_obschezhitia.doc"),
    BUTTON_NOT_RECEIVING_SCHOLARSHIP("13 Не получающие стипендию ", "buttonNotReceivingScholarship", "src/main/resources/Ворд заявления/13_ne_poluchayuschiy_stipendiyu.doc"),

    BUTTON_AGREEMENT("Далее", "buttonAgreement", ""),
    BUTTON_NOT_AGREEMENT("Исправить","buttonNotAgreement","" );

    private final String text;
    private final String callback;
    private final String path;
    private final String message;

    Buttons(String text, String callback, String path) {
        this.text = text;
        this.callback = callback;
        this.path = path;

        this.message = "Поздравляем вас с заполнением заявления на получение материальной помощи. Теперь вам осталось подготовить следующие копии документов:\n" +
                "- Копия паспорта с пропиской\n" +
                "- Скриншот подтверждения об оплате членских взносов\n" +
                "- Справка о доходах ИЛИ 2-НДФЛ ИЛИ Справка, подтверждающая факт установления пенсии\n" +
                "- Копия пенсионного удостоверения родителей\n" +
                "- Снимок экрана с указанными реквизитами: БИК-банка и номер лицевого счета\n" +
                "- Копия свидительства о рождении ребёнка(студента)\n" +
                "Эти копии документов вам нужно будет принести в кабинет вашего ФПОС, у каждого института они разные. ИУЦТ-1522, ИЭФ-3602, ИТТСУ-2323, ЮИ-6004, ИПСС-7719, ИМТК-12307\n";
    }
    }
