package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface QuestionInterface {

    /**
     *
     * @return
     */
    Integer getId();

    /**
     *
     * @param id
     */
    void setId(Integer id);

    /**
     *
     * @return
     */
    Integer getTopicId();

    /**
     *
     * @param topicId
     */
    void setTopicId(Integer topicId);

    /**
     *
     * @return
     */
    Integer getDifficultyId();

    /**
     *
     * @param difficultyId
     */
    void setDifficultyId(Integer difficultyId);

    /**
     *
     * @return
     */
    String getTitle();

    /**
     *
     * @param title
     */
    void setTitle(String title);

    /**
     *
     * @return
     */
    Integer getCorrectAnswer();

    /**
     *
     * @param correctAnswer
     */
    void setCorrectAnswer(Integer correctAnswer);

    /**
     *
     * @return
     */
    String getAnswer_1();

    /**
     *
     * @param answer
     */
    void setAnswer_1(String answer);

    /**
     *
     * @return
     */
    String getAnswer_2();

    /**
     *
     * @param answer
     */
    void setAnswer_2(String answer);

    /**
     *
     * @return
     */
    String getAnswer_3();

    /**
     *
     * @param answer
     */
    void setAnswer_3(String answer);

    /**
     *
     * @return
     */
    String getAnswer_4();

    /**
     *
     * @param answer
     */
    void setAnswer_4(String answer);
}
