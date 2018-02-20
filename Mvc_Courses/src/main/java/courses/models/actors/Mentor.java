package courses.models.actors;

import courses.models.entities.Course;

import java.util.HashSet;
import java.util.Set;

public class Mentor extends Employee {

    private Set<Course> courses = new HashSet<>();
}
