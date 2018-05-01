package by.bsuir.recourse.entity.support;

import by.bsuir.recourse.entity.model.Course;

import java.beans.PropertyEditorSupport;

public class CourseStatusEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Course.Status.valueOf(text.toUpperCase()));
    }
}
