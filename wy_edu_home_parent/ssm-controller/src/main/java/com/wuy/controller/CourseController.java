package com.wuy.controller;

import com.wuy.bean.course.Course;
import com.wuy.bean.course.CourseVo;
import com.wuy.bean.ResponseResult;
import com.wuy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /*
      http://localhost:8080/ssm_controller/course/findCourseByCourseNameAndStatus?courseName=秒杀11&
      查询课程信息&条件查询 接口
     */
    @RequestMapping("/findCourseByCourseNameAndStatus")
    public ResponseResult findCourseByCourseNameAndStatus(@RequestBody CourseVo courseVo){
        List<Course> courseList = courseService.findCourseByCourseNameAndStatus(courseVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功",courseList);
        return result;
    }

    /**
     * 课程图片上传
     * */
    @RequestMapping("/courseUpload")
    public ResponseResult courseUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        try {
            if(file.isEmpty()){
                throw  new RuntimeException("文件不能为空!!");
            }
            //D:\Code\idea\project\wy_edu_home_parent\ssm-controller\target\ssm-controller-1.0-SNAPSHOT\
            String realPath = request.getServletContext().getRealPath("/");
            //D:\Code\idea\project\wy_edu_home_parent\
            String webPath = realPath.substring(0, realPath.indexOf("ssm-controller"));

            String originalFilename = file.getOriginalFilename();
            String newName=System.currentTimeMillis()+originalFilename.substring(originalFilename.indexOf("."));

            String uploadPath=webPath+"upload\\";
            File filePath = new File(uploadPath, newName);
            if(! filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录: " + filePath);
            }
            file.transferTo(filePath);

            Map<String,String> map = new HashMap<>();
            map.put("fileName",newName);
            map.put("filePath",webPath+"/upload/"+newName);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 保存 修改课程信息
     * */

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo){
        try {
            if(courseVo.getId() ==null) {
                courseService.saveCourseOrTeacher(courseVo);
                ResponseResult result = new ResponseResult(true,200,"响应成功",null);
                return result;
            }else{
                courseService.updateCourseOrTeacher(courseVo);
                ResponseResult result = new ResponseResult(true,200,"响应成功",null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id查找课程信息
     * */
    @RequestMapping("/findCourseByID")
    public ResponseResult findCourseByID(int id){
        CourseVo courseVO = courseService.findCourseById(id);
        return new ResponseResult(true,200,"响应成功",courseVO);
    }

    /**
     * 修改课程状态
     * */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        try {
            courseService.updateCourseStatus(id, status);
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
