package roleTest;

import cn.smbms.pojo.Role;
import cn.smbms.services.bill.BillServices;
import cn.smbms.services.role.RoleServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @org.junit.Test
    public void test1(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        RoleServices roleServices = (RoleServices) context.getBean("roleServices");
        Role role=new Role();
        role.setRoleCode("test");
        role.setRoleName("test");
        boolean b = roleServices.addRole(role);
        System.out.println(b);
    }


}
