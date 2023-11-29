package me.ooify.api.controller;


import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import me.ooify.api.entity.Student;
import me.ooify.api.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * @author jonsan
 * @since 2023-11-27
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentServiceImpl studentService;
//    @Resource
//    private Student student;
//    @GetMapping("/t1")
//    public SaResult t1() {
//        student.setId(1L);
//        student.setName("1");
//        student.setAge(1);
//        student.setGender(1);
//        return SaResult.ok("t1").setData(student);
//    }
//    @GetMapping("/t2")
//    public SaResult t2() {
//        student.setAge(2);
//        return SaResult.ok("t2").setData(student);
//    }
    @GetMapping("/list")
    public SaResult getStudents(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "gender", required = false) Integer gender,
                                @RequestParam(value = "grade", required = false) Double grade,
                                @RequestParam(value = "major", required = false) String major,
                                @RequestParam(value = "age", required = false) Integer age) {

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setGender(gender);
        student.setGrade(grade);
        student.setMajor(major);
        Page<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(student.getName())) {
            queryWrapper.like("name", student.getName());
        }
        return SaResult.ok("查询成功").setData(studentService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public SaResult getStudentById(@PathVariable("id") Long id) {
        Student author = studentService.getById(id);
        return SaResult.ok("查询成功").setData(author);
    }

    @PostMapping
    public SaResult saveStudent(@RequestBody Student student) {
        if (studentService.save(student)) {
            return SaResult.ok("添加成功");
        } else {
            return SaResult.error("添加失败");
        }
    }

    @PutMapping
    public SaResult updateStudent(@RequestBody Student student) {
        if (studentService.updateById(student)) {
            return SaResult.ok("更新成功");
        } else {
            return SaResult.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public SaResult deleteStudent(@PathVariable("id") Long id) {
        if (studentService.removeById(id)) {
            return SaResult.ok("删除成功");
        } else {
            return SaResult.error("删除失败");
        }
    }

}
