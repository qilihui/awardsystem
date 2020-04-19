package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.entity.SysApartment;
import xyz.xhui.awardsystem.service.ApartmentService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/apartment")
@Api(tags = "公寓管理接口")
@RolesAllowed({"ADMIN"})
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

//    @PostMapping(name = "")
//    @ApiOperation("添加")
//    public Result save(@RequestParam Integer name) {
//        SysApartment apartment = new SysApartment();
//        apartment.setName(name);
//        return ResultFactory.buildSuccessResult(apartmentService.save(apartment), "保存成功");
//    }
//
    @GetMapping(name = "")
    @ApiOperation("查询所有")
    @ResponseBody
    public Result<List<SysApartment>> findAll() {
        List<SysApartment> apartmentList = apartmentService.findAll();
        return ResultFactory.buildSuccessResult(apartmentList.size(), apartmentList);
    }
//
//    @GetMapping(value = "/{id}")
//    @ApiOperation("根据id查询")
//    public Result findOne(@PathVariable Integer id) {
//        return ResultFactory.buildSuccessResult(apartmentService.findById(id).orElse(null), "查询成功");
//    }
//
//    @DeleteMapping(value = "{id}")
//    @ApiOperation("根据id删除")
//    public Result deleteById(@PathVariable Integer id) {
//        if(apartmentService.deleteById(id)) {
//            return ResultFactory.buildSuccessResult(null, "删除成功");
//        }
//        else {
//            return ResultFactory.buildFailResult(null, "删除失败");
//        }
//    }
}
