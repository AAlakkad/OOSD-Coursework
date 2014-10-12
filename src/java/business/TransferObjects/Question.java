/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.TransferObjects;

/**
 * @author ammar
 */
public final class Question implements QuestionInterface {

    private Integer id, topicId, difficultyId, correctAnswer;
    private String title, answer_1, answer_2, answer_3, answer_4;

    public Question() {

    }

    public Question(Integer id, Integer topicId, Integer difficultyId, Integer correctAnswer, String title, String answer_1, String answer_2, String answer_3, String answer_4) {
        this.setId(id);
        this.setTopicId(topicId);
        this.setDifficultyId(difficultyId);
        this.setCorrectAnswer(correctAnswer);
        this.setTitle(title);
        this.setAnswer_1(answer_1);
        this.setAnswer_2(answer_2);
        this.setAnswer_3(answer_3);
        this.setAnswer_4(answer_4);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getTopicId() {
        return this.topicId;
    }

    @Override
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Override
    public Integer getDifficultyId() {
        return this.difficultyId;
    }

    @Override
    public void setDifficultyId(Integer difficultyId) {
        this.difficultyId = difficultyId;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getCorrectAnswer() {
        return this.correctAnswer;
    }

    @Override
    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getAnswer_1() {
        return this.answer_1;
    }

    @Override
    public void setAnswer_1(String answer) {
        this.answer_1 = answer;
    }

    @Override
    public String getAnswer_2() {
        return this.answer_2;
    }

    @Override
    public void setAnswer_2(String answer) {
        this.answer_2 = answer;
    }

    @Override
    public String getAnswer_3() {
        return this.answer_3;
    }

    @Override
    public void setAnswer_3(String answer) {
        this.answer_3 = answer;
    }

    @Override
    public String getAnswer_4() {
        return this.answer_4;
    }

    @Override
    public void setAnswer_4(String answer) {
        this.answer_4 = answer;
    }

}
