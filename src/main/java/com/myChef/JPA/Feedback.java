package com.myChef.JPA;

import javax.persistence.*;

/**
 * Created by Mocart on 07-Mar-17.
 */
@Entity
@Table(name = "feedbacks", schema = "my_chef_db")
public class Feedback {
    private long feedbackId;
    private String description;
    private int grade;
    private UserDetails fromUser;
    private UserDetails toUser;

    @Id
    @Column(name = "feedback_id")
    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "grade")
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @ManyToOne
    @JoinColumn(name = "from_user_id", referencedColumnName = "user_id", nullable = false)
    public UserDetails getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserDetails fromUser) {
        this.fromUser = fromUser;
    }

    @ManyToOne
    @JoinColumn(name = "to_user_id", referencedColumnName = "user_id", nullable = false)
    public UserDetails getToUser() {
        return toUser;
    }

    public void setToUser(UserDetails toUser) {
        this.toUser = toUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (feedbackId != feedback.feedbackId) return false;
        if (grade != feedback.grade) return false;
        if (description != null ? !description.equals(feedback.description) : feedback.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (feedbackId ^ (feedbackId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + grade;
        return result;
    }
}
