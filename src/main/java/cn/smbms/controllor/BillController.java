package cn.smbms.controllor;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.services.bill.BillServices;
import cn.smbms.services.provider.ProviderServices;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("billServices")
    private BillServices billServices;

    @Autowired
    @Qualifier("providerServices")
    private ProviderServices providerServices;

    @RequestMapping("/billList")
    public String billList(Model model,@RequestParam(value = "queryProductName",required = false) String productName,
                                        @RequestParam(value = "queryProviderId",required = false) Long providerId,
                                        @RequestParam(value = "queryIsPayment",required = false)Integer isPayment,
                                        @RequestParam(value="pageIndex",required=false) String pageIndex
                           ){
        logger.info("getBillList ---- > productName: " + productName);
        logger.info("getBillList ---- > providerId: " + providerId);
        logger.info("getBillList ---- > isPayment: " + isPayment);
        logger.info("getBillList ---- > pageIndex: " + pageIndex);
        //设置页面容量
        int pageSize = Constants.pageSize;
        //当前页码
        int currentPageNo = 1;
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/user/syserror.html";
                //response.sendRedirect("syserror.jsp");
            }
        }
        //总数量（表）
        int totalCount	= billServices.getBillCount(productName,providerId,isPayment);
        //总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        List<Provider> providers=null;
        providers = providerServices.findProviders(null);
        List<Bill> bills=null;
        bills = billServices.findBills(productName,providerId,isPayment,currentPageNo,pageSize);
        model.addAttribute("billList", bills);
        model.addAttribute("providerList",providers);
        model.addAttribute("productName",productName);
        model.addAttribute("providerId",providerId);
        model.addAttribute("isPayment",isPayment);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "billlist";
    }

    @RequestMapping("toadd")
    public String addBill(){

        return "billadd";
    }
}
