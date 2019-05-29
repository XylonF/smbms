package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /***
     *     提供根据id、用户名、模糊的姓名、性别、生日、手机号查询用户列表
     */
    public List<User> queryUser(User cUser);
    /**
     * 添加用户
     * */
    public int addUser(User user);
    /**
     * 修改用户信息
     * */
    public int updateUser(User cUser);
    /**
     * 删除用户
     * */
    public int deleteUser(int id);

    public int countUser(User cUser);

    public List<User> queryUserByPage(@Param("userName") String userName, @Param("userRole") Integer userRole, @Param("start") int start, @Param("pageSize") int pageSize);
}
