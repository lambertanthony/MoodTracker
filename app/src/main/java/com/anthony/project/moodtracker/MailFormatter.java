package com.anthony.project.moodtracker;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class MailFormatter {





    /**
     * Mood  en int
     * 0 = triste
     * 1 = déçu
     * 2 = normal
     * 3 = heureux
     * 4 = super heureux
     * -1  = not existe
     */


    public static final String[] moodTab = new String[]{"Triste","Déçu", "Normal","Heureux","Super heureux"};
    public static  String mailMessage(ArrayList<Mood> moodsData) {

        StringBuilder emailBuilder = new StringBuilder();
        emailBuilder.append("Bonjour, ");
        emailBuilder.append("\n");
        emailBuilder.append("Les états émotionnels enregistrés du ");
        emailBuilder.append(moodsData.get(1).getRecordDate().toString());
        emailBuilder.append(" au ");
        emailBuilder.append(moodsData.get(6).getRecordDate().toString()+".");
        emailBuilder.append("\n");
        emailBuilder.append("Les états émotionnels sont ci-dessous: \n ");


        ArrayList<Mood> tempMood = new ArrayList<>();
        List<LocalDate> listOfDate = DateManipulator.getDatesBetween();
        Mood mood;

        for (int i = 0; i < moodsData.size(); i++) {
            mood =moodsData .get(i);

            if (mood.getMoodState() == -1 ) {
                emailBuilder.append(moodsData.get(i).getRecordDate().toString());
                emailBuilder.append(": Il n'y a pas d'enregistrement.\n");
            } else {
                emailBuilder.append(moodsData.get(i).getRecordDate().toString());
                emailBuilder.append(": "+moodTab[moodsData.get(i).getMoodState()]);
                emailBuilder.append(".\n");
            }
        }

      return  emailBuilder.toString();
    }





























}
