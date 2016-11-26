package org.mycompany.controller;

import org.mycompany.model.Student;
import org.mycompany.repositories.EntityRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

@Controller
@RequestMapping(value = RestUrlConst.BASE_URL)
public class StudentController
{
    private final EntityRepository<Student> studentRepository;

    @Inject
    StudentController(EntityRepository<Student> studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.STUDENT_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudentById(@PathVariable(value = "studentId") String studentId) throws Exception
    {
        return studentRepository.findById(new BigInteger(studentId), true);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.ALL_STUDENTS_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents(@RequestParam(name = "pageNumber") Integer pageNumber,
                                        @RequestParam(name = "limit") Integer limit)
    {
        List<Student> students = studentRepository.findAll();
        int from = limit * (pageNumber - 1);
        int to = students.size() <= limit * pageNumber ? students.size() : limit * pageNumber;
        return students.subList(from, to);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.ALL_STUDENTS_URL,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student) throws Exception
    {
        studentRepository.save(student);

        return student;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.STUDENT_URL,
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@PathVariable(value = "studentId") String studentId,
                                 @RequestBody Student student) throws Exception
    {
        return studentRepository.update(new BigInteger(studentId), student);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.STUDENT_URL,
            method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable(value = "studentId") String studentId) throws Exception
    {
        studentRepository.delete(new BigInteger(studentId));
    }

}
