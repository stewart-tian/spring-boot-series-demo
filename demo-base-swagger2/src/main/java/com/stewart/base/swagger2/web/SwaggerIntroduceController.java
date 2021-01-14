package com.stewart.base.swagger2.web;

import com.stewart.base.swagger2.entity.IntroduceReq;
import com.stewart.base.swagger2.entity.IntroduceResp;
import com.stewart.base.swagger2.entity.RespWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/base/swagger")
@Api(tags = "Swagger2功能介绍控制器")
public class SwaggerIntroduceController {

    @GetMapping("/hello")
    @ApiOperation(value = "方法简述", notes = "方法详细说明")
    public String hello() {
        return "Hello";
    }

    /**
     * 此处使用@ApiImplicitParam(name = "param", value = "入参", paramType = "query", example = "Param输入参数",required = true)也可以
     *
     * @param param 入参
     * @return 返回入参
     */
    @GetMapping("/one")
    @ApiOperation(value = "单个String入参", notes = "单String入参，单String反参")
    @ApiImplicitParams(@ApiImplicitParam(name = "param", value = "入参", paramType = "query", example = "Params输入参数", required = true))
    public String oneParam(@RequestParam("param") String param) {
        return "input param : " + param;
    }

    @GetMapping("/path/{var}")
    @ApiOperation(value = "path入参", notes = "path入参，单String反参")
    @ApiImplicitParam(name = "var", value = "入参", paramType = "path", example = "path输入参数", required = true)
    public String pathParam(@RequestParam("var") String var) {
        return "input param : " + var;
    }

    @GetMapping("/two")
    @ApiOperation(value = "多个String入参", notes = "多String入参，单String反参")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramOne", value = "入参一", paramType = "query", example = "Params输入参数一", required = true),
            @ApiImplicitParam(name = "paramTwo", value = "入参二", paramType = "query", example = "Params输入参数二", required = true)
    })
    public String twoParam(@RequestParam("paramOne") String paramOne, @RequestParam("paramTwo") String paramTwo) {
        return "input params : " + paramOne + "+" + paramTwo;
    }

    @PostMapping("/obj")
    @ApiOperation(value = "@RequestBody格式入参", notes = "json入参，String反参")
    public String objParam(@RequestBody IntroduceReq req) {
        return req.toString();
    }

    @PostMapping("/objRet")
    @ApiOperation(value = "@RequestBody格式入参，json反参", notes = "json入参，json反参")
    public IntroduceResp objRet(@RequestBody IntroduceReq req) {
        IntroduceResp introduceResp = new IntroduceResp();
        introduceResp.setId(456L);
        introduceResp.setNum(req.getNum());
        introduceResp.setInfo(req.getInfo());
        introduceResp.setFlag(req.getFlag());
        introduceResp.setCreateTime(LocalDateTime.now());
        return introduceResp;
    }

    @PostMapping("/warpRet")
    @ApiOperation(value = "@RequestBody格式入参，包装json反参", notes = "json入参，包装json反参")
    public RespWrapper<IntroduceResp> warpRet(@RequestBody IntroduceReq req) {
        IntroduceResp introduceResp = new IntroduceResp();
        introduceResp.setId(456L);
        introduceResp.setNum(req.getNum());
        introduceResp.setInfo(req.getInfo());
        introduceResp.setFlag(req.getFlag());
        introduceResp.setCreateTime(LocalDateTime.now());
        return new RespWrapper<>(200, "success", introduceResp);
    }

    @PostMapping("/file")
    @ApiOperation(value = "文件上传")
    public RespWrapper<String> fileUpload(@RequestPart("file") MultipartFile file) {
        String str = "";
        str += "Content-Type : " + file.getContentType() + ";  ";
        str += "Name : " + file.getName() + ";  ";
        str += "OriginalFilename : " + file.getOriginalFilename();
        return new RespWrapper<>(200, "success", str);
    }

}
