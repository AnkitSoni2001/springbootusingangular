package com.newform.New.Form.service.impl;

import com.newform.New.Form.entity.domain.FormContentDO;
import com.newform.New.Form.entity.domain.FormVersionDO;
import com.newform.New.Form.repository.FormContentRepository;
import com.newform.New.Form.repository.FormVersionRepository;
import com.newform.New.Form.response.ApiResponse;
import com.newform.New.Form.service.FormContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newform.New.Form.exception.EntityNotFoundException;




import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FormContentServiceImpl implements FormContentService {

    @Autowired
    private FormContentRepository formContentRepository;

    @Autowired
    private FormVersionRepository formVersionRepository;

    @Override
    public ResponseEntity<ApiResponse<FormContentDO>> createContent(Integer versionId, FormContentDO formContentDO) {
        Long longVersionId = versionId.longValue();

        String strVersionId = versionId.toString();
        String strPageNumber = formContentDO.getPageNumber().toString();
        String newVersionIdPageNumber = strVersionId+strPageNumber;

        Long longFormVersionIdPageNumber = Long.parseLong(newVersionIdPageNumber);

        Optional<FormVersionDO> optionalVersion = formVersionRepository.findById(versionId);

        if(optionalVersion.isPresent()){

            Optional<FormContentDO> optionalContent = formContentRepository.findByFormVersionIdPageNumber(longFormVersionIdPageNumber);

            if(!optionalContent.isPresent()){

                FormContentDO newContent = new FormContentDO();

                newContent.setFormVersionId(longVersionId);
                newContent.setPageNumber(formContentDO.getPageNumber());
                newContent.setContentData(formContentDO.getContentData());
                newContent.setFormVersionIdPageNumber(longFormVersionIdPageNumber);

                formContentRepository.save(newContent);

                List<FormContentDO> responseData = new ArrayList<>();
                responseData.add(newContent);
                ApiResponse<FormContentDO> apiResponse = new ApiResponse<>("Content created successfully", responseData);

                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
            }else {

                throw new EntityNotFoundException("Page already exists!");
            }
        }else{
            throw new EntityNotFoundException("Version does not exists!");

        }
    }

    @Override
    public ResponseEntity<ApiResponse<FormContentDO>> updateContent(Long versionId, Long pageNumber, FormContentDO formContentDO) {

        Integer integerVersionId = versionId.intValue();

        String strVersionId = versionId.toString();
        String strPageNumber = pageNumber.toString();
        String newVersionIdPageNumber = strVersionId+strPageNumber;

        Long longFormVersionIdPageNumber = Long.parseLong(newVersionIdPageNumber);

        Optional<FormContentDO> versionOptional = formContentRepository.findById(integerVersionId);
        if(versionOptional.isPresent()){
            Optional<FormContentDO> contentOptional = formContentRepository.findByFormVersionIdPageNumber(longFormVersionIdPageNumber);

            if(contentOptional.isPresent()){

                FormContentDO content = contentOptional.get();
                if(!content.getContentData().equals(formContentDO.getContentData())){
                    content.setContentData(formContentDO.getContentData());
                    formContentRepository.save(content);

                    List<FormContentDO> responseData = new ArrayList<>();
                    responseData.add(content);
                    ApiResponse<FormContentDO> apiResponse = new ApiResponse<>("Content updated successfully", responseData);

                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

                }else {
                    ApiResponse<FormContentDO> apiResponse =  new ApiResponse<>("Please update the content");
                    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
                }


            }else{
                throw new EntityNotFoundException("Page does not exists!");

            }
        }else {
            throw new EntityNotFoundException("Version does not exists!");
        }
    }
    @Override
    @Transactional
    public ResponseEntity<ApiResponse<FormContentDO>> deleteContent(Long versionId, Long pageNumber) {
        Integer integerVersionId = versionId.intValue();

        String strVersionId = versionId.toString();
        String strPageNumber = pageNumber.toString();
        String newVersionIdPageNumber = strVersionId+strPageNumber;

        Long longFormVersionIdPageNumber = Long.parseLong(newVersionIdPageNumber);

        Optional<FormContentDO> versionOptional = formContentRepository.findById(integerVersionId);
        if(versionOptional.isPresent()){
            Optional<FormContentDO> contentOptional = formContentRepository.findByFormVersionIdPageNumber(longFormVersionIdPageNumber);
            if(contentOptional.isPresent()){
                List<FormContentDO> allContentBasedOnVersionId = formContentRepository.findAllByFormVersionId(versionId);

                FormContentDO contentToDelete = null;

                for(FormContentDO content : allContentBasedOnVersionId){
                    if(content.getPageNumber() == pageNumber){
                        contentToDelete = content;
                        content.setActive(false);
                        formContentRepository.save(content);
                        break;
                    }
                }
                allContentBasedOnVersionId.sort(Comparator.comparing(FormContentDO::getPageNumber));
                int count = 1;
                for(int i = 0; i < allContentBasedOnVersionId.size(); i++){
                    FormContentDO content = allContentBasedOnVersionId.get(i);
                    if(content.getActive() == true){
                        Long pageNo = (long)(count);
                        String newPageNum = pageNo.toString();
                        String newVersionIdPageNum = strVersionId+newPageNum;
                        Long longFormVersionIdPageNum = Long.parseLong(newVersionIdPageNum);

                        content.setFormVersionIdPageNumber(longFormVersionIdPageNum);
                        content.setPageNumber(pageNo);
//                        content.setPageNumber((long)(count));
                        count++;

                    }else{
                        content.setPageNumber(0L);
                        content.setFormVersionIdPageNumber(0L);
                    }
                }
                formContentRepository.saveAll(allContentBasedOnVersionId);
                List<FormContentDO> responseData = new ArrayList<>();
                responseData.add(contentToDelete);
                ApiResponse<FormContentDO> apiResponse = new ApiResponse<>("Content deleted successfully", responseData);

                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

            }else {
                throw new EntityNotFoundException("Page does not exists!");
            }
        }else{
            throw new EntityNotFoundException("Version does not exists!");
        }
    }





    //    @Override
//    public void createContent(Integer versionId, FormContentDO formContentDO) {
//        Long longVersionId = versionId.longValue();
//
//        String strVersionId = versionId.toString();
//        String strPageNumber = formContentDO.getPageNumber().toString();
//        String newVersionIdPageNumber = strVersionId+strPageNumber;
//        Long longVersionIdPageNumber = Long.parseLong(newVersionIdPageNumber);
//
//        Integer integerFormVersionIdPageNumber = formContentDO.getFormVersionIdPageNumber().intValue();
//        System.out.println("hhhhhhhhh");
//        Optional<FormVersionDO> optionalVersion = formVersionRepository.findById(versionId);
//        System.out.println("uuuuuuuuuuuuuu");
//        if(optionalVersion.isPresent()){
//            //working
//            Optional<FormContentDO> optionalContent = formContentRepository.findByFormVersionIdPageNumber(formContentDO.getFormVersionIdPageNumber());
//
////            Optional<FormContentDO> optionalContent = formContentRepository.findByIntegerFormVersionIdPageNumber(longVersionIdPageNumber);
//            System.out.println("sssssssssssss");
//            if(!optionalContent.isPresent()){
//                FormContentDO newContent = new FormContentDO();
//
//                newContent.setFormVersionId(longVersionId);
//                newContent.setPageNumber(formContentDO.getPageNumber());
//                newContent.setFormVersionIdPageNumber(formContentDO.getFormVersionIdPageNumber());
//                newContent.setContentData(formContentDO.getContentData());
//                newContent.setFormVersionIdPageNumber(longVersionIdPageNumber);
//
//
//                formContentRepository.save(newContent);
//            }else {
//                throw new EntityNotFoundException("Page already exists!");
//            }
//
//        }else{
//            throw new EntityNotFoundException("Version does not exists!");
//        }
//    }

//    @Override
//    public void updateContent(FormContentDO formContentDO) {
//        Integer integerVersionId = formContentDO.getFormVersionId().intValue();
//        Optional<FormContentDO> contentOptional = formContentRepository.findById(integerVersionId);
////        Optional<FormContentDO> contentOptional = formContentRepository.findByFormVersionIdAndPageNumber(formContentDO.getFormVersionId(), formContentDO.getPageNumber());
//        if(contentOptional.isPresent()){
//
//            FormContentDO content = contentOptional.get();
//
//            content.setContentData(formContentDO.getContentData());
//            formContentRepository.save(content);
//        }else{
//            throw new EntityNotFoundException("Version does not exists!");
//
//        }
//    }


//    @Override
//    @Transactional
//    public void deleteContent(Long versionId, Long pageNumber) {
//
//        List<FormContentDO> allContentBasedOnVersionId = formContentRepository.findAllByFormVersionId(versionId);
//
//        FormContentDO contentToDelete = null;
//
//        for(FormContentDO content : allContentBasedOnVersionId){
//            if(content.getPageNumber() == pageNumber){
//                contentToDelete = content;
//                content.setActive(false);
//                formContentRepository.save(content);
//                break;
//            }
//        }
////        if(contentToDelete != null){
////            contentToDelete.setActive(false);
////            formContentRepository.save(contentToDelete);
////        }
//        int count = 1;
//        for(int i = 0; i < allContentBasedOnVersionId.size(); i++){
//            FormContentDO content = allContentBasedOnVersionId.get(i);
//            if(content.getActive() == true){
//                content.setPageNumber((long)(count));
//                count++;
//            }else{
//                content.setPageNumber(0L);
//            }
//        }
//        formContentRepository.saveAll(allContentBasedOnVersionId);
//    }
}
