package cn.smbms.services.user;

import cn.smbms.pojo.User;

import java.util.List;

public interface UserServices {

    public List<User> findUsers(User cUser);

    public List<User>  queryUserByPage(String userName,Integer userRole,int currentPageNo, int pageSize);

    public boolean regist(User user);

    public User login(String userCode,String password);

    public boolean updateUser(User cUser);

    public boolean deleteUser(int id);

    public boolean changePassword(String password, int id);

    public int getUserCount(String userName,int userRole);
}
