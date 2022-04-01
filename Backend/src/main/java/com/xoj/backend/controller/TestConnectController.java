package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.exception.CommonErrorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/connect")
@Api(value = "test connect")
public class TestConnectController {

    @GetMapping("")
    @ApiOperation(value = "test whether the IDE connected with the backend")
    public RestResponse<?> connected() {
        return RestResponse.ok(new Date(), CommonErrorType.SUCCESS.getResultMsg());
    }
}
