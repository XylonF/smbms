package billTest;

import cn.smbms.dao.bill.BillDao;
import cn.smbms.pojo.Bill;
import cn.smbms.services.bill.BillServices;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class test {

    @Test
    public void test2(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BillDao billDao = (BillDao) context.getBean("billDao");
        Bill bill = new Bill();
        bill.setBillCode("TTTTT");
        bill.setProductName("RERERE");
        bill.setTotalPrice(new BigDecimal(1111.00));
        int i = billDao.addBill(bill);
        System.out.println(i);
    }

    @Test
    public void test3(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BillDao billDao = (BillDao) context.getBean("billDao");
        Bill cBill = new Bill();
        cBill.setBillCode("ASD");
        cBill.setProductName("TEST");
        cBill.setModifyBy(Long.parseLong("1"));
        cBill.setModifyDate(new Date());
        cBill.setTotalPrice(new BigDecimal(1111.00));
        cBill.setId(Long.parseLong("21"));
        billDao.updateBill(cBill);
    }

    @Test
    public void test4(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BillServices billServices = (BillServices) context.getBean("billServices");
        billServices.deleteBill(22);
    }

    @Test
    public void test5(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BillServices billServices = (BillServices) context.getBean("billServices");
        List<Bill> bills = billServices.findBills(null, null, null, 1, 5);
        System.out.println(bills.size());

    }
}
