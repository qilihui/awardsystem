package xyz.xhui.awardsystem.controller.union;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@Slf4j
@RolesAllowed("UNION")
public class UnionStatController {
    @GetMapping("/union-home")
    public String getHome() {
        return "home/union";
    }

    @GetMapping(value = "/union/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "union/" + pageName;
    }
}
