package org.mycompany.controller;

import org.mycompany.dao.EntityDao;
import org.mycompany.model.Group;
import org.mycompany.utils.StudentModelDependencyOnSpring;
import org.mycompany.utils.TxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
@RequestMapping(value = "/group")
public class GroupController
{
    @CrossOrigin
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public @ResponseBody
    Group getGroup(@RequestParam(name = "id") String id) throws Exception
    {
        final TxUtils txUtils = StudentModelDependencyOnSpring.getInstance().getTxUtils();
        final EntityDao<Group> groupEntityDao = StudentModelDependencyOnSpring.getInstance().getGroupDao();

        return txUtils.doInTransactionRequired(() -> groupEntityDao.getEntity(new BigInteger(id)));
    }

}
