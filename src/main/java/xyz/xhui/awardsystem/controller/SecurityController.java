package xyz.xhui.awardsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class SecurityController {

    @GetMapping(value = "/login.html")
    public String login() {
        return "login";
    }

//    @GetMapping(value = "/403.html")
//    public String noPermission() {
//        return "403";
//    }

    @GetMapping(value = "/getPage")
    public String getPage( @RequestParam("name") String pageName) {
//        log.info(modelAndView.toString());
        log.info(pageName);
//        modelAndView.setViewName(pageName);
        return pageName;
    }

    @GetMapping(value = "/permission")
//    @RolesAllowed({"ADMIN", "TUTOR", "HOUSEPARENT", "UNION", "STU"})
    @ResponseBody
    public Object getPermission() {
        RoleEnum roleEnum = MyUserUtils.getRoleEnum();
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = null;
        try {
            object = objectMapper.readValue(this.getClass().getResourceAsStream("/permision/" + roleEnum.toString() + ".json"), Map.class);
        } catch (IOException e) {
            log.error("系统错误：读取权限文件失败");
            e.printStackTrace();
        }
        return object;
    }
}
