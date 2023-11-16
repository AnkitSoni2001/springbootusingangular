package com.newform.New.Form.controller;

import com.newform.New.Form.entity.domain.FormVersionDO;
import com.newform.New.Form.service.FormVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/version")
@CrossOrigin
public class FormVersionController {
    @Autowired
    private FormVersionService formVersionService;

//    @PostMapping("/create")
//    public void createVersion(@RequestBody FormVersionDO formVersionDO){
//        System.out.println(formVersionDO);
//        formVersionService.createVersion(formVersionDO);
//    }
    @PostMapping("/create/{formName}/{formInternalName}")
    public void createVersion(@PathVariable("formName") String formName, @PathVariable("formInternalName") String formInternalName, @RequestBody FormVersionDO formVersionDO){
        System.out.println(formVersionDO);
        formVersionService.createVersion(formName, formInternalName, formVersionDO);
    }

    @GetMapping("/get")

    public List<FormVersionDO> getVersion(){
        return formVersionService.getVersion();
    }

//    @PutMapping("/update")
//    public void updateVersion(@RequestBody FormVersionDO formVersionDO){
//        formVersionService.updateVersion(formVersionDO);
//    }

    @PutMapping("/update/{form_Id}/{version_Id}")
    public void updateVersion(@PathVariable("form_Id") Integer formId, @PathVariable("version_Id") Integer versionId, @RequestBody FormVersionDO formVersionDO){
        formVersionService.updateVersion(formId, versionId, formVersionDO);
    }
    @DeleteMapping("/delete/{version_Id}")
    public void deleteVersion(@PathVariable("version_Id") Integer Id){
        formVersionService.deleteVersion(Id);
    }
}
