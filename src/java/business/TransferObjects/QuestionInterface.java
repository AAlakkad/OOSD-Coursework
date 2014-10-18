package business.TransferObjects;

public interface QuestionInterface {

    Integer getId();

    void setId(Integer id);

    Integer getTopicId();

    void setTopicId(Integer topicId);

    Integer getDifficultyId();

    void setDifficultyId(Integer difficultyId);

    String getTitle();

    void setTitle(String title);

    Integer getCorrectAnswer();

    void setCorrectAnswer(Integer correctAnswer);

    String getAnswer_1();

    void setAnswer_1(String answer);

    String getAnswer_2();

    void setAnswer_2(String answer);

    String getAnswer_3();

    void setAnswer_3(String answer);

    String getAnswer_4();

    void setAnswer_4(String answer);
}
