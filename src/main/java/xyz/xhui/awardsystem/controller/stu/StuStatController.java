package xyz.xhui.awardsystem.controller.stu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@Slf4j
@RolesAllowed("STU")
public class StuStatController {
    @GetMapping("/stu-home")
    public String getHome() {
        return "home/stu";
    }

    @GetMapping(value = "/stu/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "stu/" + pageName;
    }
}
