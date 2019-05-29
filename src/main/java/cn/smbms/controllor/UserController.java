package cn.smbms.controllor;


import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.services.role.RoleServices;
import cn.smbms.services.user.UserServices;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServices")
    private UserServices userServices;

    @Autowired
    @Qualifier("roleServices")
    private RoleServices roleServices;

    @RequestMapping(value="/login")
    public String login(){
        logger.debug("UserController welcome SMBMS==================");
        return "login";
    }

    @RequestMapping(value="/adduser")
    public String adduser(Model model){
        logger.debug("UserController welcome SMBMS==================");
        List<Role> roleList = null;
        roleList = roleServices.findRoles(null);
        model.addAttribute("roleList", roleList);
        return "useradd";
    }

    @RequestMapping(value="/dologin.html",method= RequestMethod.POST)
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session){
        logger.debug("doLogin====================================");
        //调用service方法，进行用户匹配
        User user = userServices.login(userCode,userPassword);
        if(null != user){//用户名存在
            if(userPassword.equals(user.getUserPassword())){
                //放入session
                session.setAttribute(Constants.USER_SESSION, user);
                return "frame";
            }else {
                //页面跳转（login.jsp）带出提示信息--转发
                request.setAttribute("error", "密码错误");
                return "login";
            }
        }else{
            //页面跳转（login.jsp）带出提示信息--转发
            request.setAttribute("error", "用户名不存在");
            return "login";
        }
    }

    @RequestMapping(value="/logout.html")
    public String logout(HttpSession session){
        session.removeAttribute(Constants.USER_SESSION);
        return "redirect:/user/login.html";
    }

    @RequestMapping(value="/userlist.html")
    public String getUserList(Model model,
                              @RequestParam(value="queryname",required=false) String queryUserName,
                              @RequestParam(value="queryUserRole",required=false) String queryUserRole,
                              @RequestParam(value="pageIndex",required=false) String pageIndex){
        logger.info("getUserList ---- > queryUserName: " + queryUserName);
        logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
        logger.info("getUserList ---- > pageIndex: " + pageIndex);
        int _queryUserRole = 0;
        List<User> userList = null;
        //设置页面容量
        int pageSize = Constants.pageSize;
        //当前页码
        int currentPageNo = 1;

        if(queryUserName == null){
            queryUserName = "";
        }
        if(queryUserRole != null && !queryUserRole.equals("")){
            _queryUserRole = Integer.parseInt(queryUserRole);
        }

        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/user/syserror.html";
                //response.sendRedirect("syserror.jsp");
            }
        }
        //总数量（表）
        int totalCount	= userServices.getUserCount(queryUserName,_queryUserRole);
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
        userList = userServices.queryUserByPage(queryUserName,_queryUserRole,currentPageNo,pageSize);
        model.addAttribute("userList", userList);
        List<Role> roleList = null;
        roleList = roleServices.findRoles(null);
        model.addAttribute("roleList", roleList);
        model.addAttribute("queryUserName", queryUserName);
        model.addAttribute("queryUserRole", queryUserRole);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "userlist";
    }

/*    @RequestMapping("/doadd")
    @RequestParam(value = "userCode",required = false) String userCode,
    @RequestParam(value = "userName",required = false)String userName,
    @RequestParam(value = "userPassword",required = false)String userPassword,
    @RequestParam(value = "gender",required = false)Integer gender,
    @RequestParam(value = "birthday",required = false)Date birthday,
    @RequestParam(value = "phone",required = false)String phone,
    @RequestParam(value = "address",required = false)String address,
    @RequestParam(value = "userRole",required = false)Integer userRole,*/
    public String addUser(Model model, User user){
        userServices.regist(user);
        return "userlist";

    }

    @ExceptionHandler(value={RuntimeException.class})
	public String handlerException(RuntimeException e,HttpServletRequest req){
		req.setAttribute("e", e);
		return "error";
	}
}
