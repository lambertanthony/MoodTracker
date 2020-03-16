package com.anthony.project.moodtracker;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

public class Mood {

    /**
     * Mood  en int
     * 0 = triste
     * 1 = déçu
     * 2 = normal
     * 3 = heureux
     * 4 = super heureux
     * -1  = not existe
     */

    private int moodState;
    private String comment;
    private LocalDate recordDate;
    private final String NOT_EXISTE = "Not Existe";


    public Mood(int moodState, String comment, LocalDate recordDate) {
        this.moodState = moodState;
        this.comment = comment;
        this.recordDate = recordDate;
    }


    /*
      Constructor for create  a mood not recorded by user
      for historic of mood
     */
    public Mood(LocalDate recordDate) {

        this.moodState = -1;
        this.comment = NOT_EXISTE;
        this.recordDate = recordDate;

    }


    public boolean isNotExistMood() {
        return this.moodState == -1;

    }


    public int getMoodState() {
        return moodState;
    }

    public void setMoodState(int moodState) {
        this.moodState = moodState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "moodState=" + moodState +
                ", comment='" + comment + '\'' +
                ", recordDate=" + recordDate.toString() +
                '}';
    }

    public static ArrayList<Mood> getData() {
        ArrayList<Mood> moodsList = new ArrayList<Mood>();
        int moodsScore[] = {2, 1, 1, 4 ,0,1,2,3};
        String[] moodComment = {"Ca va", "Bof", "test etat"," ae eaea aeae ei","je suis bien","Ca avance","je me sens bien","Ca va"};
        LocalDate[] recordDates = new LocalDate[]{LocalDate.of(2020, 3, 15), LocalDate.of(2020, 3, 14),LocalDate.of(2020, 3, 13),LocalDate.of(2020, 3, 12),LocalDate.of(2020, 3, 11),LocalDate.of(2020, 3, 10)
                ,LocalDate.of(2020, 3, 9),LocalDate.of(2020, 3, 8)

        };
        for (int i = 0; i < moodsScore.length; i++) {
            Mood temp = new Mood(moodsScore[i],moodComment[i],recordDates[i]);
            moodsList.add(temp);
        }
        return moodsList;
    }




}