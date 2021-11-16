package com.wuy.service;

import com.wuy.bean.course.Course;
import com.wuy.bean.course.CourseLesson;
import com.wuy.bean.course.CourseSection;

import java.util.List;

public interface CourseContentService {
    /**
     * 查询课程下的章节与课时信息
     * */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 回显章节对应的课程信息
     * */
    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节
     * */
    public void saveSection(CourseSection section);

    /**
     * 修改章节
     * */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     * */
    public void updateSectionStatus(int id,int status);

    /**
     * 保存课时
     * */
    public void saveLesson(CourseLesson lesson);

    /**
     * 修改课时
     * */
    public void updateLesson(CourseLesson lesson);

    /**
     * 修改可是状态
     * */
    public void updateLessonStatus(int id,int status);

}
