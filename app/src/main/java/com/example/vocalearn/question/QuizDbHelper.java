package com.example.vocalearn.question;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Question;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.MyApplication;

import java.util.ArrayList;
import java.util.Random;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private WordsDAO dbAccess;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions(String ChuDe) {
        //ChuDe ="Học tập";
        Random r = new Random();
        int answerNr;
        ArrayList<Question> questionList = new ArrayList<>();
        for (int i=0;i<10;i++)
        {
            answerNr=r.nextInt(2)+1;
            Question q1 = new Question("Listen and choose the answer",  getTuFromChuDe(ChuDe),
                                                                                getTuFromChuDe(ChuDe),
                                                                                getTuFromChuDe(ChuDe),
                                                                                answerNr, null);

            while ( q1.getOption1() == q1.getOption2()||
                    q1.getOption1() == q1.getOption3()||
                    q1.getOption3() == q1.getOption2())
            {
                Question temp =new Question("Listen and choose the answer",  getTuFromChuDe(ChuDe),
                        getTuFromChuDe(ChuDe),
                        getTuFromChuDe(ChuDe),
                        answerNr, null);
                q1 = temp;
            }
            switch (answerNr)
            {
                case 1 :
                    q1.setRightAnswer(q1.getOption1());
                    break;
                case 2 :
                    q1.setRightAnswer(q1.getOption2());
                    break;
                case 3 :
                    q1.setRightAnswer(q1.getOption3());
                    break;
            }

            questionList.add(q1);
        }
        return questionList;
    }
    private String getTuFromChuDe(String ChuDe)
    {
        Words temp;
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        temp = dbAccess.getRandom(ChuDe);
        dbAccess.closeDB();
        return temp.getTu();
    }
}
