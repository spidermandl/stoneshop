package org.goshop.users.service;

import org.goshop.base.service.SpringBaseTest;
import org.goshop.users.mapper.read.ReadGsPermissionMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Desmond on 19/12/2017.
 */
public class RolePermissionTest {

    @Test
    public void addDefaultPerm() throws  Exception{
        String sql = "insert into gs_role_permission values";
        for (int i=105;i<110;i++){
            sql+=" ('"+i+"','4'),";
        }
        System.out.println(sql);
    }
}
