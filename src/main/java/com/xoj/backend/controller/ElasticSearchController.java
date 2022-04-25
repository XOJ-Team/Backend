package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.param.ESCRUDParam;
import com.xoj.backend.param.UserParam;
import com.xoj.backend.service.ElasticSearchService;
import com.xoj.backend.service.UserInfoService;
import com.xoj.backend.util.TransUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/***
 * @Author jianghanchen
 * @Date 14:40 2022/3/27
 ***/

@RestController
@RequestMapping("/es")
@Api("for insert delete update in es")
public class ElasticSearchController {
    @Autowired
    ElasticSearchService elasticSearchService;


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "insert a document")
    public RestResponse<?> insert(@RequestBody ESCRUDParam param) {
        return elasticSearchService.insertDocument(param.getIndex(), param.getId() ,param.getObj());
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "delete a document by name")
    public RestResponse<?> delete(@RequestBody ESCRUDParam param) {
        return elasticSearchService.deleteDocument(param.getIndex(), param.getId());
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "notice! this update is implemented by insert (will cover origin data)")
    public RestResponse<?> update(@RequestBody ESCRUDParam param) {
        return elasticSearchService.insertDocument(param.getIndex(),param.getId(), param.getObj());
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ApiOperation(value = "show es-head")
    public ModelAndView showES() {
        return new ModelAndView(new RedirectView("localhost:9100"));
    }




}
