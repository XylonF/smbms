package cn.smbms.services.user;

import cn.smbms.dao.user.UserDao;
import cn.smbms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("userServices")
public class UserServicesImpl implements UserServices {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<User> findUsers(User cUser) {
        List<User> users= null;
        users=userDao.queryUser(cUser);
        return users;
    }

    @Override
    public List<User> queryUserByPage(String userName, Integer userRole, int currentPageNo, int pageSize) {
        List<User> users=null;
        if (userRole.equals(Integer.parseInt("0"))){
            userRole=null;
        }
        users=userDao.queryUserByPage(userName,userRole,(currentPageNo-1)*pageSize,pageSize);
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean regist(User user) {
        user.setCreationDate(new Date());
        int i = userDao.addUser(user);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    public User login(String userCode, String password) {
        User cUser= new User();
        cUser.setUserCode(userCode);
        List<User> users = userDao.queryUser(cUser);
        if (users.size()==1){
            return users.get(0);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateUser(User cUser) {
        cUser.setModifyDate(new Date());
        int i = userDao.updateUser(cUser);
        if (i==1){
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteUser(int id) {
        int i = userDao.deleteUser(id);
        if (i==1){
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean changePassword(String password, int id) {
        User cUser = new User();
        cUser.setId(id);
        cUser.setModifyDate(new Date());
        cUser.setUserPassword(password);
        int i = userDao.updateUser(cUser);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    public int getUserCount(String userName, int userRole) {
        int i =0;
        User cUser = new User();
        cUser.setUserName(userName);
        if (userRole!=0)
        {
            cUser.setUserRole(userRole);
        }

        i= userDao.countUser(cUser);
        return i;
    }
}
