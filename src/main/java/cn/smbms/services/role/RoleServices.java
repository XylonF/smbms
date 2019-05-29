package cn.smbms.services.role;

import cn.smbms.pojo.Role;

import java.util.List;

public interface RoleServices {
    public List<Role> findRoles(Role cRole);

    public boolean addRole(Role role);

    public boolean updateRole(Role cRole);

    public boolean deleteRole(int id);
}
