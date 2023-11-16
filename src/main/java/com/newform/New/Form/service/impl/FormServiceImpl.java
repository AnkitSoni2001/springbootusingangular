package com.newform.New.Form.service.impl;

import com.newform.New.Form.entity.domain.FormDO;
import com.newform.New.Form.exception.EntityNotFoundException;
import com.newform.New.Form.repository.FormRepository;
import com.newform.New.Form.response.ApiResponse;
import com.newform.New.Form.service.FormService;
//import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Override
    public ResponseEntity<ApiResponse<FormDO>> createForm(FormDO formDO) {
        String formName = formDO.getFormName();
        String internalName = formDO.getFormInternalName();
        Optional<FormDO> optionalFormDO = formRepository.findByFormNameAndFormInternalName(formName, internalName);

        if(!optionalFormDO.isPresent()){

            FormDO newForm = new FormDO();

            newForm.setFormName(formDO.getFormName());
            newForm.setFormInternalName(formDO.getFormInternalName());

            formRepository.save(newForm);

            List<FormDO> responseData = new ArrayList<>();
            responseData.add(newForm);
            ApiResponse<FormDO> apiResponse = new ApiResponse<>("Form created successfully", responseData);

            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

        }else{

            throw new EntityNotFoundException("Form already exist!");
        }

    }

//    @Override
//    public List<FormDO> getForm() {
//        return formRepository.findAll();
//    }


    @Override
    public List<FormDO> getForm() {
        return formRepository.findByActiveTrue();
    }




//    @Override
//    public ResponseEntity<ApiResponse<FormDO>> updateForm(Integer Id, FormDO formDO) {
//
//        String formName = formDO.getFormName();
//        String internalName = formDO.getFormInternalName();
//
//        Optional<FormDO> form = formRepository.findById(Id);
//
//        if(form.isPresent()){
//
//            FormDO updatedForm = form.get();
//
//            updatedForm.setFormName(formDO.getFormName());
//            updatedForm.setFormInternalName(formDO.getFormInternalName());
//
//            formRepository.save(updatedForm);
//
//            List<FormDO> responseData = new ArrayList<>();
//            responseData.add(updatedForm);
//            ApiResponse<FormDO> apiResponse = new ApiResponse<>("Form updated successfully", responseData);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
//
//        }else{
//
//            throw new EntityNotFoundException("Form Not Exist!");
//
//        }
//    }

    @Override
    public ResponseEntity<ApiResponse<FormDO>> updateForm(Integer Id, FormDO formDO) {

        String formName = formDO.getFormName();
        String internalName = formDO.getFormInternalName();

        Optional<FormDO> form = formRepository.findById(Id);

        FormDO updatedForm = form.get();
        if(!formDO.getFormInternalName().equals(updatedForm.getFormInternalName())){
            updatedForm.setFormName(formDO.getFormName());
            updatedForm.setFormInternalName(formDO.getFormInternalName());

            formRepository.save(updatedForm);

            List<FormDO> responseData = new ArrayList<>();
            responseData.add(updatedForm);
            ApiResponse<FormDO> apiResponse = new ApiResponse<>("Form updated successfully", responseData);

            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }else{
            throw new EntityNotFoundException("Form does not updated!");
        }
    }


    @Override
    public void deleteForm(Integer Id) {
        Optional<FormDO> optionalForm = formRepository.findById(Id);
        if(optionalForm.isPresent()){
            FormDO form = optionalForm.get();
            form.setActive(false);
            formRepository.save(form);
        }else{
            throw new EntityNotFoundException("Form does not esists!");
        }
    }
}
