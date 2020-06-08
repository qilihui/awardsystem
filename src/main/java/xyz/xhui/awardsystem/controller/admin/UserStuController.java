package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.model.dto.*;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.UserStuService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/stu")
@Api(tags = "用户管理：学生")
@RolesAllowed({"ADMIN"})
@Slf4j
public class UserStuController {
    @Autowired
    private UserStuService userStuService;

    @GetMapping(value = "/edit")
    @ApiOperation("查询用户信息")
    public String edit(Model model, @RequestParam("id") Integer id) {
//        log.info(id.toString());
        Optional<SysUserStu> userStuOptional = userStuService.findBySysUserId(id);
        model.addAttribute("sysUser", userStuOptional.get().getUser());
        model.addAttribute("userStu", userStuOptional.get());
        return "admin/user/user-stu-edit";
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

    @PostMapping(value = "/add")
    @ApiOperation("添加")
    @ResponseBody
    public Result<String> save(UserInfoDto userInfoDto, SysUserDto userDto) {
        log.info(userInfoDto.toString());
        log.info(userDto.toString());
        try {
            userStuService.save(userInfoDto, userDto);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult("添加成功");
    }

    @PostMapping(value = "/adds")
    @ApiOperation("批量添加")
    @ResponseBody
    public Result<String> saves(@RequestBody StuDto[] stuDtos) {
        try {
            userStuService.saves(stuDtos);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult("添加成功");
    }

    @GetMapping("/findByUsername")
    @ApiOperation("根据用户名查找")
    @RolesAllowed({"UNION", "ADMIN"})
    @ResponseBody
    public Result<String> findByUsername(@RequestParam("username") String username) {
        log.info(username);
        SysUserStu userStu = null;
        try {
            if (MyUserUtils.getRoleEnum().equals(RoleEnum.ROLE_ADMIN)) {
                userStu = userStuService.findSysUserStuByUsernameToAdmin(username);
            } else {
                userStu = userStuService.findSysUserStuByUsername(username);
            }
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(userStu.getUser().getRealName());
    }

    @GetMapping("getApartment")
    @ApiOperation("按照公寓查找学生")
    @RolesAllowed("HOUSEPARENT")
    @ResponseBody
    public Result<List<StuDto>> getApartment(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        PageDto<List<StuDto>> pageDto = null;
        try {
            pageDto = userStuService.findByHouseparent(pageNum - 1, pageSize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), pageDto.getObj());
    }

    @GetMapping("/getApartmentToExcel")
    @ApiOperation("按照公寓查找学生导出表格")
    @RolesAllowed("HOUSEPARENT")
    @ResponseBody
    public Result<List<ApartmentScoreDto>> getApartmentToExcel() {
        List<ApartmentScoreDto> apartmentScoreDtoList = null;
        try {
            apartmentScoreDtoList = userStuService.findByHouseparentToExcel();
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(apartmentScoreDtoList.size(), apartmentScoreDtoList);
    }

    @GetMapping("getByTutor")
    @ApiOperation("按照辅导员查找学生")
    @RolesAllowed("TUTOR")
    @ResponseBody
    public Result<List<StuDto>> getByTutor(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        PageDto<List<StuDto>> pageDto = null;
        try {
            pageDto = userStuService.findByTutor(pageNum - 1, pageSize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), pageDto.getObj());
    }


}
