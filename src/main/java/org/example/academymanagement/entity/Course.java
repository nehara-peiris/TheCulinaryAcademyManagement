package org.example.academymanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "credits")
    private int credits;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>();

    public Course() {
    }

    public Course(Long id, String courseName, int credits, Set<Enrollment> enrollments) {
        this.id = id;
        this.courseName = courseName;
        this.credits = credits;
        this.enrollments = enrollments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", enrollments=" + enrollments +
                '}';
    }
}