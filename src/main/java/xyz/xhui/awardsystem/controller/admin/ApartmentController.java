package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.entity.SysApartment;
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.service.ApartmentService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/apartment")
@Api(tags = "公寓管理接口")
@RolesAllowed({"ADMIN"})
@Slf4j
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    @PostMapping("/edit")
    @ApiOperation("更新")
    @ResponseBody
    public Result<String> updateDept(SysApartment apartment) {
        log.info(apartment.toString());
        try {
            apartmentService.updatAapartment(apartment);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
//
    @GetMapping(name = "")
    @ApiOperation("查询所有")
    @ResponseBody
    public Result<List<SysApartment>> findAll() {
        List<SysApartment> apartmentList = apartmentService.findAll();
        return ResultFactory.buildSuccessResult(apartmentList.size(), apartmentList);
    }

    @PostMapping("/add")
    @ApiOperation("添加公寓")
    @ResponseBody
    public Result<String> save(@RequestParam String name) {
        log.info(name);
        try {
            apartmentService.save(name);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
//
//    @GetMapping(value = "/{id}")
//    @ApiOperation("根据id查询")
//    public Result findOne(@PathVariable Integer id) {
//        return ResultFactory.buildSuccessResult(apartmentService.findById(id).orElse(null), "查询成功");
//    }

    @DeleteMapping("")
    @ApiOperation("根据id删除公寓")
    @ResponseBody
    public Result<String> deleteById(@RequestParam("ids[]") Integer[] ids) {
        try {
            apartmentService.deleteApartments(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
