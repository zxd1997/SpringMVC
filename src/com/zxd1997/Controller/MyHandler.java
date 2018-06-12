package com.zxd1997.Controller;

import com.zxd1997.Beans.Message;
import com.zxd1997.Services.MessageService;
import com.zxd1997.Util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyHandler {
    @Autowired
    MessageService messageService;
    @RequestMapping(value = "/find")
    public String find(HttpServletRequest request,String page){
        System.out.println("____MyHandler____find()");
        Page pages=new Page(messageService.getPages(),Integer.valueOf(page));
        request.setAttribute("list",messageService.find_page(pages.getPage()));
        request.setAttribute("page",pages);
        return "forward:msg.jsp";
    }
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,Message message){
        System.out.println("____MyHandler____add()");
        if (messageService.add(message)){
            return "forward:/find?page=1";
        }else {
            request.setAttribute("msg","Failed");
            return "forward:msgworng.jsp";
        }
    }
    @RequestMapping(value = "delete")
    public String delete(HttpServletRequest request,String id){
        if (messageService.delete(Integer.valueOf(id))) {
            return "forward:/find?page=1";
        } else {
            request.setAttribute("msg", "Delete failed");
            return "forward:msgworng.jsp";
        }
    }
}
