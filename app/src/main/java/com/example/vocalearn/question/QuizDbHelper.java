package com.example.vocalearn.question;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Option;
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
        Random r = new Random();
        int answerNr;
        ArrayList<Question> questionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            answerNr = (r.nextInt(3) + 1);
            Question q1 = new Question("Listen and choose the answer", getTuFromChuDe(ChuDe),
                    getTuFromChuDe(ChuDe),
                    getTuFromChuDe(ChuDe),
                    answerNr, null);
            while (q1.getOption1().equals(q1.getOption2()) ||
                    q1.getOption2().equals(q1.getOption3()) ||
                    q1.getOption1().equals(q1.getOption3())) {
                Question temp = new Question("Listen and choose the answer", getTuFromChuDe(ChuDe),
                        getTuFromChuDe(ChuDe),
                        getTuFromChuDe(ChuDe),
                        answerNr, null);
                q1 = temp;
            }
            switch (answerNr) {
                case 1:
                    q1.setRightAnswer(q1.getOption1());
                    break;
                case 2:
                    q1.setRightAnswer(q1.getOption2());
                    break;
                case 3:
                    q1.setRightAnswer(q1.getOption3());
                    break;
            }
            questionList.add(q1);
        }
        return questionList;
    }

    public ArrayList<Question> getAllQuestions(String ChuDe, int FAV) {
        Random r = new Random();
        int answerNr;
        ArrayList<Question> questionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            answerNr = (r.nextInt(3) + 1);
            Question q1 = new Question("Listen and choose the answer", getTuFromChuDe(ChuDe, FAV),
                    getTuFromChuDe(ChuDe, FAV),
                    getTuFromChuDe(ChuDe, FAV),
                    answerNr, null);
            if (q1.getOption1() != null) {
                while (q1.getOption1().equals(q1.getOption2()) ||
                        q1.getOption2().equals(q1.getOption3()) ||
                        q1.getOption1().equals(q1.getOption3())) {
                    Question temp = new Question("Listen and choose the answer", getTuFromChuDe(ChuDe, FAV),
                            getTuFromChuDe(ChuDe, FAV),
                            getTuFromChuDe(ChuDe, FAV),
                            answerNr, null);
                    q1 = temp;
                }
            }
            switch (answerNr) {
                case 1:
                    q1.setRightAnswer(q1.getOption1());
                    break;
                case 2:
                    q1.setRightAnswer(q1.getOption2());
                    break;
                case 3:
                    q1.setRightAnswer(q1.getOption3());
                    break;
            }
            questionList.add(q1);
        }
        return questionList;
    }
    //cho nghĩa chọn từ
    public ArrayList<Question> getAllQuestionsByMean(String ChuDe, int f)
    {
        Random r = new Random();
        int answerNr;
        ArrayList<Question> questionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            answerNr = (r.nextInt(3) + 1);
            String questionName = "Chose the correct word of:";
            Option opt1, opt2, opt3 = new Option();
            opt1 = getOption(ChuDe,f);
            opt2 = getOption(ChuDe,f);
            opt3 = getOption(ChuDe,f);
            while (opt1.getWord().equals(opt2.getWord()) ||
                    opt1.getWord().equals(opt3.getWord()) ||
                    opt2.getWord().equals(opt3.getWord()))
            {
                opt1 = getOption(ChuDe,f);
                opt2 = getOption(ChuDe,f);
                opt3 = getOption(ChuDe,f);
            }
            Question q = new Question(questionName,
                    opt1.getWord(),
                    opt2.getWord(),
                    opt3.getWord(),
                    answerNr,null);
            switch (answerNr) {
                case 1:
                    q.setRightAnswer(q.getOption1());
                    q.setQuestion(q.getQuestion()+" "+opt1.getMean());
                    break;
                case 2:
                    q.setRightAnswer(q.getOption2());
                    q.setQuestion(q.getQuestion()+" "+opt2.getMean());
                    break;
                case 3:
                    q.setRightAnswer(q.getOption3());
                    q.setQuestion(q.getQuestion()+" "+opt3.getMean());
                    break;
            }
            questionList.add(q);
        }
        return questionList;
    }
    //cho  chọn từ nghĩa
    public ArrayList<Question> getAllQuestionsByWord(String ChuDe, int f)
    {
        Random r = new Random();
        int answerNr;
        ArrayList<Question> questionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            answerNr = (r.nextInt(3) + 1);
            String questionName = "Chose the correct word of:";
            Option opt1, opt2, opt3 = new Option();
            opt1 = getOption(ChuDe,f);
            opt2 = getOption(ChuDe,f);
            opt3 = getOption(ChuDe,f);
            while (opt1.getWord().equals(opt2.getWord()) ||
                    opt1.getWord().equals(opt3.getWord()) ||
                    opt2.getWord().equals(opt3.getWord()))
            {
                opt1 = getOption(ChuDe,f);
                opt2 = getOption(ChuDe,f);
                opt3 = getOption(ChuDe,f);
            }
            Question q = new Question(questionName,
                    opt1.getMean(),
                    opt2.getMean(),
                    opt3.getMean(),
                    answerNr,null);
            switch (answerNr) {
                case 1:
                    q.setRightAnswer(q.getOption1());
                    q.setQuestion(q.getQuestion()+" "+opt1.getWord());
                    break;
                case 2:
                    q.setRightAnswer(q.getOption2());
                    q.setQuestion(q.getQuestion()+" "+opt2.getWord());
                    break;
                case 3:
                    q.setRightAnswer(q.getOption3());
                    q.setQuestion(q.getQuestion()+" "+opt3.getWord());
                    break;
            }
            questionList.add(q);
        }
        return questionList;
    }
    private String getTuFromChuDe(String ChuDe, int f) {
        Words temp;
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        temp = dbAccess.getRandom(ChuDe, f);
        dbAccess.closeDB();
        return temp.getTu();
    }

    private String getTuFromChuDe(String ChuDe) {
        Words temp;
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        temp = dbAccess.getRandom(ChuDe);
        dbAccess.closeDB();
        return temp.getTu();
    }

    private Option getOption(String ChuDe,int fav) {
        Option temp;
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        temp = dbAccess.getOption(ChuDe,fav);
        dbAccess.closeDB();
        return temp;
    }
}
