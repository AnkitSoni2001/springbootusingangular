package com.newform.New.Form.controller;

import com.newform.New.Form.entity.domain.FormContentDO;
import com.newform.New.Form.response.ApiResponse;
import com.newform.New.Form.service.FormContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class FormContentController {

    @Autowired
    private FormContentService formContentService;

    @PostMapping("/create/{formVersion_Id}")
    public ResponseEntity<ApiResponse<FormContentDO>> createContent(@PathVariable("formVersion_Id") Integer Id, @RequestBody FormContentDO formContentDO){
        return formContentService.createContent(Id, formContentDO);
//        return "Content Created Sucessfully";
    }

//    @PutMapping("/update")
//    public void updateContent(@RequestBody FormContentDO formContentDO){
//        formContentService.updateContent(formContentDO);
//    }
    @PutMapping("/update/{versionId}/{pageNumber}")
    public ResponseEntity<ApiResponse<FormContentDO>> updateContent(@PathVariable("versionId") Long versionId, @PathVariable("pageNumber") Long pageNumber, @RequestBody FormContentDO formContentDO){
        return formContentService.updateContent(versionId, pageNumber, formContentDO);
//        return "Update Sucessfully";
    }

    @DeleteMapping("/delete/{versionId}/{pageNumber}")
    public ResponseEntity<ApiResponse<FormContentDO>> deleteContent(@PathVariable("versionId") Long versionId, @PathVariable("pageNumber") Long pageNumber){
        return formContentService.deleteContent(versionId, pageNumber);
    }
}
