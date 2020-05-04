package xyz.xhui.awardsystem.controller.tutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@Slf4j
@RolesAllowed("TUTOR")
public class TutorStatController {
    @GetMapping("/tutor-home")
    public String getHome() {
        return "home/tutor";
    }

    @GetMapping(value = "/tutor/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "tutor/" + pageName;
    }
}
