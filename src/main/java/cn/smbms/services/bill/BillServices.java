package cn.smbms.services.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillServices {

    public List<Bill> findBills(String productName, Long providerId,  Integer isPayment, int page, int size);

   public boolean addBill(Bill bill);

   public boolean updateBill(Bill cBill);

   public boolean deleteBill(int id);

   public int getBillCount(String productName,Long providerId,Integer isPayment);
}
