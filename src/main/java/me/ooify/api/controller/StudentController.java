package me.ooify.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import me.ooify.api.entity.Student;
import me.ooify.api.service.impl.StudentServiceImpl;
import me.ooify.api.utils.Result;
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

    @GetMapping("/list")
    public Result getStudents(@RequestParam(value = "pageNum", required = false) Integer pageNum,
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
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1; // 默认为第一页
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10; // 默认每页显示10条数据
        }
        Page<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(student.getName())) {
            queryWrapper.like("name", student.getName());
        }
        if (student.getAge() != null) {
            queryWrapper.eq("age", student.getAge());
        }
        if (student.getGender() != null) {
            queryWrapper.eq("gender", student.getGender());
        }
        if (student.getGrade() != null) {
            queryWrapper.eq("grade", student.getGrade());
        }
        if (StringUtils.isNotBlank(student.getMajor())) {
            queryWrapper.like("major", student.getMajor());
        }
        return Result.ok("查询成功").setData(studentService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Long id) {
        Student author = studentService.getById(id);
        return Result.ok("查询成功").setData(author);
    }

    @PostMapping
    public Result saveStudent(@RequestBody Student student) {
        if (studentService.save(student)) {
            return Result.ok("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @PutMapping
    public Result updateStudent(@RequestBody Student student) {
        if (studentService.updateById(student)) {
            return Result.ok("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable Long id) {
        if (studentService.removeById(id)) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

}
