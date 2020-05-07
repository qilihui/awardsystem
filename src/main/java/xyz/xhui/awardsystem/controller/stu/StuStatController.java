package xyz.xhui.awardsystem.controller.stu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.ApartmentService;
import xyz.xhui.awardsystem.service.DeptService;
import xyz.xhui.awardsystem.service.GradeService;
import xyz.xhui.awardsystem.service.UserStuService;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@Slf4j
@RolesAllowed("STU")
public class StuStatController {
    @Autowired
    private UserStuService userStuService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ApartmentService  apartmentService;

    @GetMapping("/stu-home")
    public String getHome() {
        return "home/stu";
    }

    @GetMapping(value = "/stu/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "stu/" + pageName;
    }

    @GetMapping("/stu/info")
    public String getInfo(Model model) {
        Optional<SysUserStu> userStuOptional = userStuService.findBySysUserId(MyUserUtils.getId());
        SysUserStu userStu = null;
        try {
            userStu = userStuOptional.get();
            model.addAttribute("sysUser", userStu);
            model.addAttribute("deptName", deptService.findById(userStu.getDeptId()).get().getName());
            model.addAttribute("gradeName", gradeService.findById(userStu.getGradeId()).get().getName());
            model.addAttribute("apartmentName", apartmentService.findById(userStu.getApartmentId()).get().getName());
        }catch (NoSuchElementException e){
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        return "stu/stu-info";
    }
}
