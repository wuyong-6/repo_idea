package com.wuy.controller;

import com.wuy.bean.ResponseResult;
import com.wuy.bean.course.Course;
import com.wuy.bean.course.CourseLesson;
import com.wuy.bean.course.CourseSection;
import com.wuy.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;
    /**
     * 查询课程内容
     * */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam int courseId){
        try {
            List<CourseSection> sectionList = contentService.findSectionAndLessonByCourseId(courseId);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", sectionList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 回显章节对应的课程信息
     * */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int courseId){
        try {
            Course course = contentService.findCourseByCourseId(courseId);
            ResponseResult result = new ResponseResult(true,200,"响应成功",course);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存章节
     * */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section){
        try {
            if(section.getId()==null){
                contentService.saveSection(section);
                return new ResponseResult(true,200,"响应成功",null);
            }else{
                contentService.updateSection(section);
                return new ResponseResult(true,200,"响应成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改章节状态
     * 状态，0:隐藏；1：待更新；2：已发布
     * */
    @RequestMapping("updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id,@RequestParam int status){
        try {
            contentService.updateSectionStatus(id, status);
            //封装最新的状态信息
            Map<String,Integer> map = new HashMap<>();
            map.put("status",status);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存&修改课时
     * */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson){
        try {
            if(lesson.getId() == null){
                contentService.saveLesson(lesson);
            }else{
                contentService.updateLesson(lesson);
            }
            return new ResponseResult(true,200,"响应成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
        课时状态,0-隐藏，1-未发布，2-已发布
     */
    @RequestMapping("/updateLessonStatus")
    public ResponseResult updateLessonStatus(@RequestParam int id,@RequestParam int status){
        try {
            contentService.updateLessonStatus(id, status);
            //封装最新的状态信息
            Map<String,Integer> map = new HashMap<>();
            map.put("status",status);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
