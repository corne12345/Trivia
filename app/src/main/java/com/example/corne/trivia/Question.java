package com.example.corne.trivia;

// Create a question as a question, 4 answers and the index of the correct answer
public class Question {

    private String mQuestion;
    private String mAnswerA;
    private String mAnswerB;
    private String mAnswerC;
    private String mAnswerD;
    private String mCorrectAnswer;

    public Question(String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer){
        mQuestion = question;
        mAnswerA = answerA;
        mAnswerB = answerB;
        mAnswerC = answerC;
        mAnswerD = answerD;
        mCorrectAnswer = correctAnswer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswerA() {
        return mAnswerA;
    }

    public String getAnswerB() {
        return mAnswerB;
    }

    public String getAnswerC() {
        return mAnswerC;
    }

    public String getAnswerD() {
        return mAnswerD;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void setAnswerA(String answerA) {
        mAnswerA = answerA;
    }

    public void setAnswerB(String answerB) {
        mAnswerB = answerB;
    }

    public void setAnswerC(String answerC) {
        mAnswerC = answerC;
    }

    public void setAnswerD(String answerD) {
        mAnswerD = answerD;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }
}
