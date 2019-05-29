package cn.smbms.dao.role;


import cn.smbms.pojo.Role;

import java.util.List;

public interface RoleDao {

     public List<Role> queryRole(Role cRole);

     public int addRole(Role role);

     public int updateRole(Role cRole);

     public int deleteRole(int id);
}
