package xyz.xhui.awardsystem.controller.houseparent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.ApartmentService;
import xyz.xhui.awardsystem.service.UserHouseparentService;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@Slf4j
@RolesAllowed("HOUSEPARENT")
public class HouseparentStatController {
    @Autowired
    private UserHouseparentService userHouseparentService;

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/houseparent-home")
    public String getHome() {
        return "home/houseparent";
    }

    @GetMapping(value = "/houseparent/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "houseparent/" + pageName;
    }

    @GetMapping("/houseparent/info")
    public String getInfo(Model model) {
        Optional<SysUserHouseparent> userHouseparentOptional = userHouseparentService.findBySysUserId(MyUserUtils.getId());
        SysUserHouseparent userHouseparent = null;
        try {
            userHouseparent = userHouseparentOptional.get();
            model.addAttribute("sysUser", userHouseparent);
            model.addAttribute("apartmentName", apartmentService.findById(userHouseparent.getApartmentId()).get().getName());
        }catch (NoSuchElementException e){
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        return "houseparent/houseparent-info";
    }
}
