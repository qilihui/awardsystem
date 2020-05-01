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
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserAdmin;
import xyz.xhui.awardsystem.service.UserAdminService;
import xyz.xhui.awardsystem.service.UserService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/admin")
@Api(tags = "管理员管理接口")
@RolesAllowed({"ADMIN"})
@Slf4j
public class UserAdminController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping(value = "/edit")
    @ApiOperation("查询用户信息")
    public String edit(Model model, @RequestParam("id") Integer id) {
//        log.info(id.toString());
        Optional<SysUserAdmin> userAdminOptional = userAdminService.findBySysUserId(id);
        model.addAttribute("sysUser", userAdminOptional.get().getUser());
        model.addAttribute("userAdminId", userAdminOptional.get().getId());
        return "admin/user/user-admin-edit";
    }

    @PostMapping("edit")
    @ApiOperation("修改用户信息")
    @ResponseBody
    public Result<String> editUserAdmin(SysUserDto userDto) {
//        log.info(id);
        log.info(userDto.toString());
        Integer count = null;
        try {
            count = userAdminService.updateEmailAndRealName(userDto);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        if (count > 0) {
            return ResultFactory.buildSuccessResult();
        }
        return ResultFactory.buildFailResult();
    }

    @PostMapping(value = "/add")
    @ApiOperation("添加管理员")
    @ResponseBody
    public Result<String> save(SysUserDto sysUserDto) {
        try {
            userAdminService.save(sysUserDto);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

//    @GetMapping(value = "")
//    @ApiOperation("查询所有详细信息")
//    public Result findTutorAll() {
//        List<SysUserAdmin> admins = userAdminService.findAll();
//        for (SysUserAdmin admin : admins) {
//            PasswordUtils.hiddenPassword(admin);
//        }
//        return ResultFactory.buildSuccessResult(admins, "查询成功");
//    }
//
//    @GetMapping(value = "{id}")
//    @ApiOperation("根据id查询")
//    public Result findById(@PathVariable Integer id) {
//        Optional<SysUserAdmin> retUserAdmin = userAdminService.findById(id);
//        retUserAdmin.ifPresent(PasswordUtils::hiddenPassword);
//        return ResultFactory.buildSuccessResult(retUserAdmin.orElse(null), "查询成功");
//    }

//    @DeleteMapping(value = "")
//    @ApiOperation("根据sysUserId删除管理员")
//    @ResponseBody
//    public Result<String> deleteById(@RequestParam Integer id) {
//        log.info(id.toString());
//        if (MyUserUtils.getId().equals(id)) {
//            return ResultFactory.buildFailResult("不能删除当前登陆账号!");
//        }
//        try {
//            userAdminService.deleteBySysUserId(id);
//        } catch (EntityFieldException e) {
//            return ResultFactory.buildFailResult(e.getMessage());
//        }
//        return ResultFactory.buildSuccessResult();
//    }
}
