/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mokiductions.classes;

/**
 *
 * @author Miquel Ginés Borràs
 * @mail ginesborrasm@gmail.com
 */
public class QuestionCorrection {

    private String question;
    private String userAnswer;
    private String correctAnswer;
    private float score;

    public QuestionCorrection(String question, String userAnswer, String correctAnswer, float score) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public float getScore() {
        return score;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuestionCorrection{" + "question=" + question + ", userAnswer=" + userAnswer + ", correctAnswer=" + correctAnswer + ", score=" + score + '}';
    }
    
}
