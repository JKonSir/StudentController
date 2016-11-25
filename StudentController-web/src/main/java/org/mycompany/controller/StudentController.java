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
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

@Controller
@RequestMapping(value = RestUrlConst.BASE_URL)
public class StudentController
{
    @Inject
    private EntityRepository<Student> studentRepository;

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
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudentsByIds(@RequestBody List<String> studentIds)
    {
        return studentRepository.findByIds(
                studentIds.stream()
                        .map(BigInteger::new)
                        .collect(Collectors.toList())
        );
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.ALL_STUDENTS_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }


//    @CrossOrigin
//    @RequestMapping(value = RestUrlConst.STUDENT_URL + "/{studentId}", method = RequestMethod.DELETE)
//    public @ResponseBody void deleteStudent(@RequestParam(name = "studentId") String studentId) throws Exception
//    {
//        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
//        final EntityDao<Student> studentDao = StudentModelDependencyOnSpring.getInstance().getStudentDao();
//
//        txUtils.doInTransactionRequired((Callable<Student>) () -> {
//            Student student = studentDao.getEntity(new BigInteger(studentId));
//            studentDao.delete(student);
//            return null;
//        });
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = RestUrlConst.STUDENT_URL + "/{studentId}", method = RequestMethod.POST)
//    public @ResponseBody void updateStudent(@PathVariable(value = "studentId") String studentId, Student student) throws Exception
//    {
//        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
//        final EntityDao<Student> studentDao = StudentModelDependencyOnSpring.getInstance().getStudentDao();
//
//        if (studentId != null)
//        {
//            student.setId(new BigInteger(studentId));
//        }
//
//        txUtils.doInTransactionRequired((Callable<Student>) () -> {
//            studentDao.createOrUpdate(student);
//            return null;
//        });
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = RestUrlConst.STUDENT_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody void createStudent(@PathVariable(value = "groupId") String groupId,
//                                            @RequestBody Student student) throws Exception
//    {
//        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
//        final EntityDao<Student> studentDao = StudentModelDependencyOnSpring.getInstance().getStudentDao();
//        final EntityDao<Group> groupDao = StudentModelDependencyOnSpring.getInstance().getGroupDao();
//
//        System.out.println(student);
//
//        txUtils.doInTransactionRequired((Callable<Student>) () -> {
//            Group group = groupDao.getEntity(new BigInteger(groupId));
//            Student result = new Student(student.getFirstName(), student.getLastName(),
//                    student.getAge(), group);
//            studentDao.createOrUpdate(result);
//            return null;
//        });
//    }

}
