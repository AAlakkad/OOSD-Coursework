package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface QuestionInterface {

    /**
     *
     * @return id
     */
    Integer getId();

    /**
     *
     * @param id id
     */
    void setId(Integer id);

    /**
     *
     * @return topic id
     */
    Integer getTopicId();

    /**
     *
     * @param topicId topic id
     */
    void setTopicId(Integer topicId);

    /**
     *
     * @return difficulty id
     */
    Integer getDifficultyId();

    /**
     *
     * @param difficultyId difficulty id
     */
    void setDifficultyId(Integer difficultyId);

    /**
     *
     * @return title
     */
    String getTitle();

    /**
     *
     * @param title title
     */
    void setTitle(String title);

    /**
     *
     * @return correct answer
     */
    Integer getCorrectAnswer();

    /**
     *
     * @param correctAnswer correct answer
     */
    void setCorrectAnswer(Integer correctAnswer);

    /**
     *
     * @return answer 1
     */
    String getAnswer_1();

    /**
     *
     * @param answer answer
     */
    void setAnswer_1(String answer);

    /**
     *
     * @return answer 2
     */
    String getAnswer_2();

    /**
     *
     * @param answer answer
     */
    void setAnswer_2(String answer);

    /**
     *
     * @return answer 3
     */
    String getAnswer_3();

    /**
     *
     * @param answer answer
     */
    void setAnswer_3(String answer);

    /**
     *
     * @return answer 4
     */
    String getAnswer_4();

    /**
     *
     * @param answer answer
     */
    void setAnswer_4(String answer);
}
