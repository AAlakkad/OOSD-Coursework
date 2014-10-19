package business.TransferObjects;

/**
 *
 * @author ammar
 */
public final class Question implements QuestionInterface {

    private Integer id, topicId, difficultyId, correctAnswer;
    private String title, answer_1, answer_2, answer_3, answer_4;

    /**
     * Default constructor
     */
    public Question() {

    }

    /**
     *
     * @param id
     * @param topicId
     * @param difficultyId
     * @param correctAnswer
     * @param title
     * @param answer_1
     * @param answer_2
     * @param answer_3
     * @param answer_4
     */
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

    /**
     *
     * @return
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getTopicId() {
        return this.topicId;
    }

    /**
     *
     * @param topicId
     */
    @Override
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getDifficultyId() {
        return this.difficultyId;
    }

    /**
     *
     * @param difficultyId
     */
    @Override
    public void setDifficultyId(Integer difficultyId) {
        this.difficultyId = difficultyId;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @param title
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
     *
     * @param correctAnswer
     */
    @Override
    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAnswer_1() {
        return this.answer_1;
    }

    /**
     *
     * @param answer
     */
    @Override
    public void setAnswer_1(String answer) {
        this.answer_1 = answer;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAnswer_2() {
        return this.answer_2;
    }

    /**
     *
     * @param answer
     */
    @Override
    public void setAnswer_2(String answer) {
        this.answer_2 = answer;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAnswer_3() {
        return this.answer_3;
    }

    /**
     *
     * @param answer
     */
    @Override
    public void setAnswer_3(String answer) {
        this.answer_3 = answer;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAnswer_4() {
        return this.answer_4;
    }

    /**
     *
     * @param answer
     */
    @Override
    public void setAnswer_4(String answer) {
        this.answer_4 = answer;
    }

}
