package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.dao.MysqlInfoDao;
import xyz.xhui.awardsystem.service.UserService;

import javax.annotation.security.RolesAllowed;
import java.util.Properties;

@Controller
@Api("统计")
@RolesAllowed("ADMIN")
@Slf4j
public class AdminStatController {
    @Autowired
    private UserService userService;

    @Autowired
    private MysqlInfoDao mysqlInfoDao;

    @GetMapping("/admin-home")
    public String getStat(Model model) {
        Properties properties = System.getProperties();
        model.addAttribute("properties", properties);
        model.addAttribute("mysql", mysqlInfoDao.getVersion());
        model.addAttribute("all", userService.getCount());
        model.addAttribute("admin", userService.getCount(RoleEnum.ROLE_ADMIN));
        model.addAttribute("tutor", userService.getCount(RoleEnum.ROLE_TUTOR));
        model.addAttribute("houseparent", userService.getCount(RoleEnum.ROLE_HOUSEPARENT));
        model.addAttribute("union", userService.getCount(RoleEnum.ROLE_UNION));
        model.addAttribute("stu", userService.getCount(RoleEnum.ROLE_STU));
        return "/home/admin";
    }

    @GetMapping(value = "/admin/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "admin/" + pageName;
    }
}
