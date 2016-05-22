package org.mycompany.controller;

import org.mycompany.dao.EntityDao;
import org.mycompany.model.Student;
import org.mycompany.utils.StudentModelDependencyOnSpring;
import org.mycompany.utils.TxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.concurrent.Callable;

@Controller
@RequestMapping(value = "/student")
public class StudentController
{
    @CrossOrigin
    @RequestMapping(value = "/get_student", method = RequestMethod.GET)
    public @ResponseBody Student getStudent(@RequestParam(name = "id") String id) throws Exception
    {
        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
        final EntityDao<Student> studentDao = StudentModelDependencyOnSpring.getInstance().getStudentDao();

        return txUtils.doInTransactionRequired(() -> studentDao.getEntity(new BigInteger(id)));
    }

    @CrossOrigin
    @RequestMapping(value = "/delete_student", method = RequestMethod.DELETE)
    public @ResponseBody void deleteStudent(Student student) throws Exception
    {
        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
        final EntityDao<Student> studentDao = StudentModelDependencyOnSpring.getInstance().getStudentDao();

        txUtils.doInTransactionRequired((Callable<Student>) () -> {
            studentDao.delete(student);
            return null;
        });
    }

    @CrossOrigin
    @RequestMapping(value = "/update_student", method = RequestMethod.POST)
    public @ResponseBody void updateStudent(Student student) throws Exception
    {
        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
        final EntityDao<Student> studentDao = StudentModelDependencyOnSpring.getInstance().getStudentDao();

        txUtils.doInTransactionRequired((Callable<Student>) () -> {
            studentDao.createOrUpdate(student);
            return null;
        });
    }

    @CrossOrigin
    @RequestMapping(value = "/add_student", method = RequestMethod.POST)
    public @ResponseBody void addStudent(Student student) throws Exception
    {
        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
        final EntityDao<Student> studentDao = StudentModelDependencyOnSpring.getInstance().getStudentDao();

        txUtils.doInTransactionRequired((Callable<Student>) () -> {
            studentDao.createOrUpdate(student);
            return null;
        });
    }

}
