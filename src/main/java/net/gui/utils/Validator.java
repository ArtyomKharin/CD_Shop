package net.gui.utils;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EvSpirit on 22.12.2016.
 */
public class Validator {

    private static Pattern pattern;
    private static Matcher matcher;
    public static final String validationString = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String validationMailString = "abcdefghijklmnopqrstuvwxyz0123456789_@.";

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static boolean validate(final String hex) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
    public static boolean testLogin(String testString){
        Pattern p = Pattern.compile("^[a-z0-9]+");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
    public static boolean alphabetTest(String test){
        Pattern p = Pattern.compile("^[a-zа-я]+");
        Matcher m = p.matcher(test);
        return m.matches();
    }
    public static void alphabetConstraint(TextField field){
        if(field.getText().length()!=0&&!alphabetTest(field.getText())){
            String s = field.getText().substring(0, field.getText().length() - 1);
            field.setText(s);
        }
    }
    public static boolean nameConstraint(String testString){
        Pattern p = Pattern.compile("^[a-z0-9а-яА-ЯA-Z]+");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
    public static void idConstraint(TextField field1){
        if (field1.getText().length() > 11) {
            String s = field1.getText().substring(0, 11);
            field1.setText(s);
        }
        Validator.numberConstraint(field1);
    }
    public static void phoneConstraint(TextField field) {
        if (field.getText().length() == 1 && !field.getText().equals("+")) {
            field.clear();
        }
        if(field.getText().length()>1){
            numberConstraint(field,1);
        }
    }
    public static void lengthConstraint(TextField field1,int num){
        if (field1.getText().length() > num) {
            String s = field1.getText().substring(0, num);
            field1.setText(s);
        }
    }
    public static void numberConstraint(TextField absField,int a) {
        if (!org.apache.commons.lang3.math.NumberUtils.isNumber(absField.getText().substring(1))) {
            if (absField.getLength() >a) {
                String s = absField.getText().substring(0, absField.getText().length() - 1);
                absField.setText(s);
            } else {
                absField.clear();
            }
        }
        if (absField.getLength() >a) {
            if (!org.apache.commons.lang3.math.NumberUtils.isNumber(absField.getText(absField.getLength() - 1, absField.getLength()))) {
                String s = absField.getText().substring(0, absField.getText().length() - 1);
                absField.setText(s);
            }
        }
    }
    public static void numberConstraint(TextField absField) {
        if (!org.apache.commons.lang3.math.NumberUtils.isNumber(absField.getText())) {
            if (absField.getLength() != 0 || absField.getLength() != 0) {
                String s = absField.getText().substring(0, absField.getText().length() - 1);
                absField.setText(s);
            } else {
                absField.clear();
            }
        }
        if (absField.getLength() != 0 || absField.getLength() != 0) {
            if (!org.apache.commons.lang3.math.NumberUtils.isNumber(absField.getText(absField.getLength() - 1, absField.getLength()))) {
                String s = absField.getText().substring(0, absField.getText().length() - 1);
                absField.setText(s);
            }
        }
    }
}
