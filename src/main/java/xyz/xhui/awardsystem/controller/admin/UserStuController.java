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
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.UserStuService;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@Controller
@RequestMapping("/user/stu")
@Api(tags = "学生管理接口")
@RolesAllowed({"ADMIN"})
@Slf4j
public class UserStuController {
    private static final Logger logger = LoggerFactory.getLogger(UserStuController.class);

    @Autowired
    private UserStuService userStuService;

    @GetMapping(value = "/edit")
    @ApiOperation("查询用户信息")
    public String edit(Model model, @RequestParam("id") Integer id) {
//        log.info(id.toString());
        Optional<SysUserStu> userStuOptional = userStuService.findBySysUserId(id);
        model.addAttribute("sysUser", userStuOptional.get().getUser());
        model.addAttribute("userStu", userStuOptional.get());
        return "user/user-stu-edit";
    }

    @PostMapping("edit")
    @ApiOperation("修改用户信息")
    @ResponseBody
    public Result<String> editUserAdmin(UserInfoDto userInfoDto, SysUserDto userDto) {
//        log.info(id);
        log.info(userInfoDto.toString());
        log.info(userDto.toString());
        Integer count = null;
        try {
            count = userStuService.updateEmailAndRealName(userInfoDto, userDto);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        if (count > 1) {
            return ResultFactory.buildSuccessResult();
        }
        return ResultFactory.buildFailResult();
    }

//    @PostMapping(value = "")
//    @ApiOperation("添加")
//    public Result save(@RequestBody SysUserStu userStu) {
//        logger.info(userStu.toString());
//        SysUserStu retUserStu = null;
//        try {
//            retUserStu = userStuService.save(userStu);
//        } catch (EntityFieldException e) {
//            return ResultFactory.buildFailResult(null, e.getMessage());
//        }
//        PasswordUtils.hiddenPassword(retUserStu);
//        return ResultFactory.buildSuccessResult(retUserStu, "添加成功");
//    }
//
//    @GetMapping(value = "")
//    @ApiOperation("查询所有学生详细信息")
//    public Result findStuAll() {
//        List<SysUserStu> userStus = userStuService.findAll();
//        for (SysUserStu userStu : userStus) {
//            PasswordUtils.hiddenPassword(userStu);
//        }
//        return ResultFactory.buildSuccessResult(userStus, "查询成功");
//    }
//
//    @GetMapping(value = "{id}")
//    @ApiOperation("根据id查询")
//    public Result findById(@PathVariable Integer id) {
//        Optional<SysUserStu> retUserStu = userStuService.findById(id);
//        retUserStu.ifPresent(PasswordUtils::hiddenPassword);
//        return ResultFactory.buildSuccessResult(retUserStu.orElse(null), "查询成功");
//    }

    @DeleteMapping(value = "")
    @ApiOperation("根据sysUserId删除学生")
    @ResponseBody
    public Result<String> deleteById(@RequestParam Integer id) {
        try {
            userStuService.deleteBySysUserId(id);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
