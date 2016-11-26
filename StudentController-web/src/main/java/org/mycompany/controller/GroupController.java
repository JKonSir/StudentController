package org.mycompany.controller;

import org.mycompany.model.Group;
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
public class GroupController
{
    private final EntityRepository<Group> groupRepository;

    @Inject
    GroupController(EntityRepository<Group> groupRepository)
    {
        this.groupRepository = groupRepository;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.GROUP_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Group getGroupById(@PathVariable(value = "groupId") String groupId) throws Exception
    {
        return groupRepository.findById(new BigInteger(groupId), Boolean.FALSE);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.ALL_GROUPS_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getAllStudents(@RequestParam(name = "pageNumber") Integer pageNumber,
                                      @RequestParam(name = "limit") Integer limit)
    {
        List<Group> groups = groupRepository.findAll();
        int from = limit * (pageNumber - 1);
        int to = groups.size() <= limit * pageNumber ? groups.size() : limit * pageNumber;
        return groups.subList(from, to);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.ALL_GROUPS_URL,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Group createGroup(@RequestBody Group group) throws Exception
    {
        groupRepository.save(group);

        return group;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.GROUP_URL,
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Group updateGroup(@PathVariable(value = "groupId") String groupId,
                             @RequestBody Group group) throws Exception
    {
        return groupRepository.update(new BigInteger(groupId), group);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.GROUP_URL,
            method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable(value = "groupId") String groupId) throws Exception
    {
        groupRepository.delete(new BigInteger(groupId));
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.ALL_STUDENTS_OF_GROUP_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudentsOfGroup(@PathVariable(value = "groupId") String groupId) throws Exception
    {
        return groupRepository.findById(new BigInteger(groupId), Boolean.TRUE).getStudents();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.STUDENT_OF_GROUP_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudentOfGroup(@PathVariable(value = "groupId") String groupId,
                                     @PathVariable(value = "studentId") String studentId) throws Exception
    {
        List<Student> students = groupRepository.findById(new BigInteger(groupId), Boolean.TRUE).getStudents();
        for (Student student : students)
        {
            if (new BigInteger(studentId).equals(student.getId()))
            {
                return student;
            }
        }
        return null;
    }

}
