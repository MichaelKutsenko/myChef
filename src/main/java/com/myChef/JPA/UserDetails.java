package com.myChef.JPA;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart on 07-Mar-17.
 */
@Entity
@Table(name = "user_details", schema = "my_chef_db")
public class UserDetails {
    private long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private Date lastOnline;
    private Integer grade;
    private Integer gradeCounter;
//    private boolean isChef;
    private User user;
    private City city;

    private List<Event> events = new ArrayList<>();
    private List<Feedback> feedbacksFromUser = new ArrayList<>();
    private List<Feedback> feedbacksToUser = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "last_online")
    public Date getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Date lastOnline) {
        this.lastOnline = lastOnline;
    }

    @Basic
    @Column(name = "grade")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "grade_counter")
    public Integer getGradeCounter() {
        return gradeCounter;
    }

    public void setGradeCounter(Integer gradeCounter) {
        this.gradeCounter = gradeCounter;
    }

//    @Basic
//    @Column(name = "is_chef")
//    public boolean isChef() {
//        return isChef;
//    }
//
//
//    public void setChef(boolean chef) {
//        isChef = chef;
//    }

    @OneToMany(mappedBy = "user")
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @OneToMany(mappedBy = "fromUser")
    public List<Feedback> getFeedbacksFromUser() {
        return feedbacksFromUser;
    }

    public void setFeedbacksFromUser(List<Feedback> feedbacksFromUser) {
        this.feedbacksFromUser = feedbacksFromUser;
    }

    @OneToMany(mappedBy = "toUser")
    public List<Feedback> getFeedbacksToUser() {
        return feedbacksToUser;
    }

    public void setFeedbacksToUser(List<Feedback> feedbacksToUser) {
        this.feedbacksToUser = feedbacksToUser;
    }

    @OneToMany(mappedBy = "user")
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (userId != that.userId) return false;
//        if (isChef != that.isChef) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (lastOnline != null ? !lastOnline.equals(that.lastOnline) : that.lastOnline != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (gradeCounter != null ? !gradeCounter.equals(that.gradeCounter) : that.gradeCounter != null) return false;
//        if (isChef != null ? !isChef.equals(that.isChef) : that.isChef != null)
//            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (lastOnline != null ? lastOnline.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (gradeCounter != null ? gradeCounter.hashCode() : 0);
//        result = 31 * result + (discriminator != null ? discriminator.hashCode() : 0);
//        result = 31 * result + (isChef ? 1 : 0);
        return result;
    }
}
