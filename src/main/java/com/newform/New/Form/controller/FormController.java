package com.newform.New.Form.controller;

import com.newform.New.Form.entity.domain.FormDO;
import com.newform.New.Form.response.ApiResponse;
import com.newform.New.Form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
@CrossOrigin
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @PostMapping("create")
    public org.springframework.http.ResponseEntity<com.newform.New.Form.response.ApiResponse<FormDO>> createForm(@RequestBody FormDO formDO){
        System.out.println(formDO);

        return formService.createForm(formDO);
    }

    @GetMapping("/get")
    public List<FormDO> getForm(){
        return formService.getForm();
    }

    @PutMapping("/update/{form_Id}")
    public ResponseEntity<ApiResponse<FormDO>> updateForm(@PathVariable("form_Id") Integer Id, @RequestBody FormDO formDO){
        return formService.updateForm(Id, formDO);
    }

    @DeleteMapping("/delete/{form_Id}")
    public void deleteForm(@PathVariable("form_Id") Integer Id){
        formService.deleteForm(Id);
    }
}
