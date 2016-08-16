package uml.yangwenjing.wenjingandroidlibrary.libraryBean;

import java.io.Serializable;

public class Question implements Serializable {
	private int questionId;
	private int ownerId;
	private int answerCount;
	private String questionTitle;
	public Question(int ownerId, int answerCount, String questionTitle, String questionCreateTime,
					String questionContent) {
		super();
		this.ownerId = ownerId;
		this.answerCount = answerCount;
		this.questionTitle = questionTitle;
		this.questionCreateTime = questionCreateTime;
		this.questionContent = questionContent;
	}
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(String title, int replyCount, String createDate, int currentUserId, String content) {

		this.questionTitle = title;
		this.answerCount = replyCount;
		this.questionCreateTime = createDate;
		this.ownerId = currentUserId;
		this.questionContent = content;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", ownerId=" + ownerId + ", answerCount=" + answerCount
				+ ", questionTitle=" + questionTitle + ", questionCreateTime=" + questionCreateTime
				+ ", questionContent=" + questionContent + "]";
	}
	private String questionCreateTime;
	private String questionContent;

	
	
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionCreateTime() {
		return questionCreateTime;
	}
	public void setQuestionCreateTime(String questionCreateTime) {
		this.questionCreateTime = questionCreateTime;
	}
	
	
	
	
}
