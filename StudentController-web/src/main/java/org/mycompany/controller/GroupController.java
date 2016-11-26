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
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getGroupsByIds(@RequestBody List<String> groupIds) throws Exception
    {
        return groupRepository.findByIds
                (
                        groupIds.stream()
                                .map(BigInteger::new)
                                .collect(Collectors.toList())
                );
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = RestUrlConst.ALL_GROUPS_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getAllGroups() throws Exception
    {
        return groupRepository.findAll();
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
