package xyz.xhui.awardsystem.controller.houseparent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@Slf4j
@RolesAllowed("HOUSEPARENT")
public class HouseparentStatController {
    @GetMapping("/houseparent-home")
    public String getHome() {
        return "home/houseparent";
    }

    @GetMapping(value = "/houseparent/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "houseparent/" + pageName;
    }
}
