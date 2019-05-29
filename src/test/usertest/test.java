package usertest;

import cn.smbms.dao.user.UserDao;
import cn.smbms.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {
    @Test
    public void test1(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        List<User> users = userDao.queryUserByPage(null,null,0,5);
        for (User u :
                users) {
            System.out.println(u.getUserName()+u.getUserRoleName());
        }
    }

    @Test
    public void test2(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        User cUser = new User();
        cUser.setUserRole(2);
        int i= userDao.countUser(cUser);
        System.out.println(i);
    }
}
