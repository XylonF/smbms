package cn.smbms.services.role;

import cn.smbms.dao.role.RoleDao;
import cn.smbms.dao.user.UserDao;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("roleServices")
public class RoleServicesImpl implements RoleServices {
    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;


    @Override
    public List<Role> findRoles(Role cRole) {
        List<Role> roles = null;
        roles=roleDao.queryRole(cRole);
        return roles;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addRole(Role role) {
        role.setCreationDate(new Date());
        int i = roleDao.addRole(role);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateRole(Role cRole){
        cRole.setModifyDate(new Date());
        int i = roleDao.updateRole(cRole);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteRole(int id) {
        User cUser = new User();
        cUser.setId(id);
        if (userDao.countUser(cUser)==0){
            int i = roleDao.deleteRole(id);
            if (i==1){
                return true;
            }
        }
        return false;
    }
}
