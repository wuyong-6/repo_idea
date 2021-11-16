package com.wuy.service;

import com.wuy.bean.course.Course;
import com.wuy.bean.course.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /**
     * 多条件课程列表查询
     */
    public List<Course> findCourseByCourseNameAndStatus(CourseVo courseVo);

    /**
     * 保存课程信息
     * */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 保存修改信息
     * */
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 根据id获取课程信息
     * */
    public CourseVo findCourseById(int id);

    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(int id,int status);



}
