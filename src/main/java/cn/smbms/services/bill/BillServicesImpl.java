package cn.smbms.services.bill;

import cn.smbms.dao.bill.BillDao;
import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("billServices")
public class BillServicesImpl implements BillServices {
    @Autowired
    @Qualifier("billDao")
    private BillDao billDao;

    @Override
    public List<Bill> findBills( String productName,Long providerId,  Integer isPayment, int page, int size) {
        List<Bill> bills=null;
        bills=billDao.queryBill(productName,providerId,isPayment,(page-1)*size,size);
        return bills;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addBill(Bill bill) {
        bill.setCreationDate(new Date());
        int i = billDao.addBill(bill);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateBill(Bill cBill) {
        cBill.setModifyDate(new Date());
        int i = billDao.updateBill(cBill);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteBill(int id) {
        int i = billDao.deleteBill(id);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    public int getBillCount(String productName, Long providerId, Integer isPayment) {
        int t=0;
        Bill cBill=new Bill();
        cBill.setProductName(productName);
        cBill.setProviderId(providerId);
        cBill.setIsPayment(isPayment);
        t=billDao.countBill(cBill);
        return t;
    }
}
