package xyz.xhui.awardsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.model.dto.PermissionDto;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class CommonController {

    @GetMapping(value = "/login.html")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/403.html")
    public String noPermission() {
        return "403";
    }

    @GetMapping({"/index.html", "/"})
    public String getIndex(Model model) {
        RoleEnum roleEnum = MyUserUtils.getRoleEnum();
        ObjectMapper objectMapper = new ObjectMapper();
        PermissionDto object = null;
        try {
            object = objectMapper.readValue(this.getClass().getResourceAsStream("/permision/" + roleEnum.toString() + ".json"), PermissionDto.class);
        } catch (IOException e) {
            log.error("系统错误：读取权限文件失败");
            e.printStackTrace();
        }
        model.addAttribute("realName", MyUserUtils.getRealName());
        model.addAttribute("homePage", object.getHomePage());
        model.addAttribute("permission", object.getData());
        return "index";
    }

    @GetMapping("/change-password")
    public String getPage() {
        return "user-change-password";
    }
}
