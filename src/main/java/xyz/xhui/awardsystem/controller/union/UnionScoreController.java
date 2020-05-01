package xyz.xhui.awardsystem.controller.union;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.UnionScore;
import xyz.xhui.awardsystem.service.UnionScoreService;

import java.util.List;

@RestController
@RequestMapping("/union/score")
@Slf4j
@Api(tags = "学生会成绩管理接口")
public class UnionScoreController {
    @Autowired
    private UnionScoreService unionScoreService;

//    @PostMapping("")
//    @ApiOperation("添加")
//    public Result save(@Valid UnionScoreDto unionScoreDto) {
//        UnionScore unionScore;
//        try {
//            unionScore = unionScoreService.save(unionScoreDto);
//        } catch (Exception e) {
//            return ResultFactory.buildFailResult(null, e.getMessage());
//        }
//        return ResultFactory.buildSuccessResult(unionScore, "添加成功");
//    }

    @GetMapping(name = "")
    @ApiOperation("分页查询")
    public Result<List<ScoreDto>> findAll(@RequestParam("page") Integer pagenum, @RequestParam("limit") Integer pagesize) {
        log.info(pagenum.toString() + "   " + pagesize.toString());
        List<ScoreDto> list = null;
        try {
            list = unionScoreService.findAll(pagenum - 1, pagesize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
//        log.info(list.toString());
        return ResultFactory.buildSuccessResult(list.size(), list);
    }

//    @GetMapping(value = "/{id}")
//    @ApiOperation("根据id查询")
//    public Result findOne(@PathVariable Integer id) {
//        return ResultFactory.buildSuccessResult(unionScoreService.findById(id).orElse(null), "查询成功");
//    }
//
//    @DeleteMapping(value = "{id}")
//    @ApiOperation("根据id删除")
//    public Result deleteById(@PathVariable Integer id) {
//        if(unionScoreService.deleteById(id)) {
//            return ResultFactory.buildSuccessResult(null, "删除成功");
//        }
//        else {
//            return ResultFactory.buildFailResult(null, "删除失败");
//        }
//    }
}
