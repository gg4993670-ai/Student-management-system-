package com.example.SMS.controller;

import com.example.SMS.entity.Student;
import com.example.SMS.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*") // Enables frontend to communicate with backend seamlessly
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Pagination & Sorting endpoint (e.g., /api/students/paginated?page=0&size=5&sortBy=firstName)
    // Pagination & Sorting endpoint
    @GetMapping("/paginated")
    public Page<Student> getStudentsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return studentService.getStudentsPaginated(page, size, sortBy);
    }

    // Filter by Department endpoint (e.g., /api/students/department/CSE)
    @GetMapping("/department/{department}")
    public List<Student> getStudentsByDepartment(@PathVariable String department) {
        return studentService.getStudentsByDepartment(department);
    }

    // Add a new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully with id: " + id;
    }
}