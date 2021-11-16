package com.wuy.service.impl;

import com.wuy.bean.course.Course;
import com.wuy.bean.course.CourseLesson;
import com.wuy.bean.course.CourseSection;
import com.wuy.dao.CourseContentMapper;
import com.wuy.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        return courseContentMapper.findSectionAndLessonByCourseId(courseId);
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public void saveSection(CourseSection section) {
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        courseContentMapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection section) {
        Date date = new Date();
        section.setUpdateTime(date);
        courseContentMapper.updateSection(section);
    }

    @Override
    public void updateSectionStatus(int id,int status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveLesson(CourseLesson lesson) {
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        courseContentMapper.saveLesson(lesson);
    }

    @Override
    public void updateLesson(CourseLesson lesson) {
        Date date = new Date();
        lesson.setUpdateTime(date);
        courseContentMapper.updateLesson(lesson);
    }

    @Override
    public void updateLessonStatus(int id, int status) {
        CourseLesson courseLesson = new CourseLesson();
        courseLesson.setId(id);
        courseLesson.setStatus(status);
        Date date = new Date();
        courseLesson.setUpdateTime(date);
        courseContentMapper.updateLessonStatus(courseLesson);
    }
}
