package cn.smbms.dao.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillDao {
    /**
     * 根据商品名、供应商id、是否已支付来查询订单
     * */
    public List<Bill> queryBill(@Param("productName") String productName, @Param("providerId") Long providerId, @Param("isPayment") Integer isPayment, @Param("start") int start, @Param("size") int size);
    /**
     * 添加订单
     * */
    public int addBill(Bill bill);
    /**
     *修改订单
     * */
    public int updateBill(Bill cBill);
    /**
     * 删除订单
     * */
    public int deleteBill(int id);

    public int countBill(Bill cBill);
}
