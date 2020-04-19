package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.service.UserTutorService;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@Controller
@RequestMapping("/user/tutor")
@Api(tags = "辅导员管理接口")
@RolesAllowed({"ADMIN"})
@Slf4j
public class UserTutorController {
    private static final Logger logger = LoggerFactory.getLogger(UserTutorController.class);

    @Autowired
    private UserTutorService userTutorService;

    @GetMapping(value = "/edit")
    @ApiOperation("查询用户信息")
    public String edit(Model model, @RequestParam("id") Integer id) {
//        log.info(id.toString());
        Optional<SysUserTutor> userTutorOptional = userTutorService.findBySysUserId(id);
        model.addAttribute("sysUser", userTutorOptional.get().getUser());
        model.addAttribute("userTutor", userTutorOptional.get());
        return "user/user-tutor-edit";
    }

    @PostMapping("edit")
    @ApiOperation("修改用户信息")
    @ResponseBody
    public Result<String> editUserInfo(UserInfoDto userInfoDto, SysUserDto userDto) {
//        log.info(id);
        log.info(userInfoDto.toString());
        log.info(userDto.toString());
        Integer count = null;
        try {
            count = userTutorService.updateEmailAndRealName(userInfoDto, userDto);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        if (count > 1) {
            return ResultFactory.buildSuccessResult();
        }
        return ResultFactory.buildFailResult();
    }

//    @PostMapping(value = "")
//    @ApiOperation("添加用户")
//    public Result save(@RequestBody SysUserTutor userTutor) {
//        logger.info(userTutor.toString());
//        SysUserTutor retUserTutor = null;
//        try {
//            retUserTutor = userTutorService.save(userTutor);
//        } catch (EntityFieldException e) {
//            return ResultFactory.buildFailResult(null, e.getMessage());
//        }
//        PasswordUtils.hiddenPassword(retUserTutor);
//        return ResultFactory.buildSuccessResult(retUserTutor, "添加成功");
//    }
//
//    @GetMapping(value = "")
//    @ApiOperation("查询所有详细信息")
//    public Result findTutorAll() {
//        List<SysUserTutor> tutors = userTutorService.findAll();
//        for (SysUserTutor tutor : tutors) {
//            PasswordUtils.hiddenPassword(tutor);
//        }
//        return ResultFactory.buildSuccessResult(tutors, "查询成功");
//    }
//
//    @GetMapping(value = "{id}")
//    @ApiOperation("根据id查询")
//    public Result findById(@PathVariable Integer id) {
//        Optional<SysUserTutor> retUserTutor = userTutorService.findById(id);
//        retUserTutor.ifPresent(PasswordUtils::hiddenPassword);
//        return ResultFactory.buildSuccessResult(retUserTutor.orElse(null), "查询成功");
//    }

    @DeleteMapping(value = "")
    @ApiOperation("根据sysUserId删除辅导员")
    @ResponseBody
    public Result<String> deleteById(@RequestParam Integer id) {
        try {
            userTutorService.deleteBySysUserId(id);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
