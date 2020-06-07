package xyz.xhui.awardsystem.controller.tutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.service.DeptService;
import xyz.xhui.awardsystem.service.GradeService;
import xyz.xhui.awardsystem.service.TermService;
import xyz.xhui.awardsystem.service.UserTutorService;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@Slf4j
@RolesAllowed("TUTOR")
public class TutorStatController {
    @Autowired
    private UserTutorService userTutorService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TermService termService;

    @GetMapping("/tutor-home")
    public String getHome(Model model) {
        Integer week = null;
        try {
            week = termService.findNowWeek();
        } catch (UnknownException e) {
            model.addAttribute("exception", e.getMessage());
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("nowTime", df.format(System.currentTimeMillis()));
        model.addAttribute("nowWeek", week);
        return "home/tutor";
    }

    @GetMapping(value = "/tutor/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "tutor/" + pageName;
    }

    @GetMapping("/tutor/info")
    public String getInfo(Model model) {
        Optional<SysUserTutor> userTutorOptional = userTutorService.findBySysUserId(MyUserUtils.getId());
        SysUserTutor userTutor = null;
        try {
            userTutor = userTutorOptional.get();
            model.addAttribute("sysUser", userTutor);
            model.addAttribute("deptName", deptService.findById(userTutor.getDeptId()).get().getName());
            model.addAttribute("gradeName", gradeService.findById(userTutor.getGradeId()).get().getName());
        } catch (NoSuchElementException e) {
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        return "tutor/tutor-info";
    }
}
