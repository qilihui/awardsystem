package xyz.xhui.awardsystem.controller.tutor;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.SumScoreDto;
import xyz.xhui.awardsystem.model.vo.SumScoreVo;
import xyz.xhui.awardsystem.service.SumScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/sumScore")
@RolesAllowed("TUTOR")
@Slf4j
@Api("综合成绩管理")
public class SumScoreController {
    @Autowired
    private SumScoreService sumScoreService;

    @PostMapping("/get")
    @ResponseBody
    public Result<List<SumScoreDto>> getSumScore(SumScoreVo sumScoreVo) {
        log.info(sumScoreVo.toString());
        List<SumScoreDto> scoreDtoList = null;
        try {
            scoreDtoList = sumScoreService.getByTutor(sumScoreVo);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(scoreDtoList.size(), scoreDtoList);
    }
}
