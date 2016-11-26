package org.mycompany.controller;

public class RestUrlConst
{
    public static final String BASE_URL = "/srs/manager/1.0";

    public static final String GROUP_ID_URL = "/{groupId}";
    public static final String ALL_GROUPS_URL = "/groups";
    public static final String GROUP_URL = ALL_GROUPS_URL + GROUP_ID_URL;

    public static final String STUDENT_ID_URL = "/{studentId}";
    public static final String ALL_STUDENTS_URL = "/students";
    public static final String STUDENT_URL = ALL_STUDENTS_URL + STUDENT_ID_URL;
    public static final String ALL_STUDENTS_OF_GROUP_URL = GROUP_URL + ALL_STUDENTS_URL;
    public static final String STUDENT_OF_GROUP_URL = ALL_STUDENTS_OF_GROUP_URL + STUDENT_ID_URL;
}
