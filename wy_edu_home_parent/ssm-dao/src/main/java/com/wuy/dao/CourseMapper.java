package com.wuy.dao;

import com.wuy.bean.course.Course;
import com.wuy.bean.course.CourseVo;
import com.wuy.bean.course.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    /**
     * 多条件课程列表查询
     */
    public List<Course> findCourseByCourseNameAndStatus(CourseVo courseVo);

    /**
     * 保存课程信息
     */
    public int saveCourse(Course course);

    /**
     * 保存讲师信息
     * */
    public void saveTeacher(Teacher teacher);

    /**
     * 修改课程信息
     * */
    public void updateCourse(Course course);
    /**
     * 修改讲师信息
     * */
    public void updateTeacher(Teacher teacher);
    /**
     * 根据id 获取课程信息
     * */
    public CourseVo findCourseById(int id);
    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(Course course);



}
