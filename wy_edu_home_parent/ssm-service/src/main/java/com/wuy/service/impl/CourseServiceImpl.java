package com.wuy.service.impl;

import com.wuy.bean.course.Course;
import com.wuy.bean.course.CourseVo;
import com.wuy.bean.course.Teacher;
import com.wuy.dao.CourseMapper;
import com.wuy.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCourseNameAndStatus(CourseVo courseVo) {
        return courseMapper.findCourseByCourseNameAndStatus(courseVo);
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        Course course=new Course();
        //BeanUtils.copyProperties(a,b); 把被b拷贝到a里面
        BeanUtils.copyProperties(course,courseVo);

        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        courseMapper.saveCourse(course);

        int id = course.getId();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        courseMapper.saveTeacher(teacher);
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        Course course=new Course();
        //BeanUtils.copyProperties(a,b); 把被b拷贝到a里面
        BeanUtils.copyProperties(course,courseVo);

        Date date = new Date();
        course.setUpdateTime(date);

        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        teacher.setUpdateTime(date);

        courseMapper.updateTeacher(teacher);
    }

    @Override
    public CourseVo findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseStatus(int id, int status) {
        try {
            Course course = new Course();
            course.setId(id);
            course.setUpdateTime(new Date());
            course.setStatus(status);

            courseMapper.updateCourseStatus(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
