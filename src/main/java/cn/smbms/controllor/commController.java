package cn.smbms.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class commController {

    @RequestMapping(value="/syserror.html")
    public String sysError(){
        return "syserror";
    }
}
