package com.softwear.webapp5.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.softwear.webapp5.model.Statics;
import com.softwear.webapp5.service.StaticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestStaticsController {
 
    @Autowired
    private StaticsService staticsService;
    
    @GetMapping(path = {"/statics"}, params = {"page", "size"})
    public List<Statics> getStatics(@RequestParam("page") int page,
        @RequestParam("size") int size, HttpServletResponse response) throws Exception{
            Page<Statics> statics = staticsService.findAll(PageRequest.of(page, size));
            if (page > statics.getTotalPages()){
                throw new Exception("page requested does not exist");
            } else {
                return statics.getContent();
            }
        }   
}
