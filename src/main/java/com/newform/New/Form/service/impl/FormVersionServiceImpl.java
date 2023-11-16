package com.newform.New.Form.service.impl;

import com.newform.New.Form.entity.domain.FormDO;
import com.newform.New.Form.entity.domain.FormVersionDO;
import com.newform.New.Form.repository.FormRepository;
import com.newform.New.Form.repository.FormVersionRepository;
import com.newform.New.Form.service.FormVersionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.util.List;
import java.util.Optional;

@Service
public class FormVersionServiceImpl implements FormVersionService {

    @Autowired
    private FormVersionRepository formVersionRepository;

    @Autowired
    private FormRepository formRepository;

//    @Override
//    public void createVersion(FormVersionDO formVersionDO) {
//
//        Optional<FormVersionDO> versionOptional = formVersionRepository.findById(formVersionDO.getId());
//        if(!versionOptional.isPresent()){
//            formVersionRepository.save(formVersionDO);
//        }else {
//            throw new EntityNotFoundException("Version already exists!");
//        }
//    }

    @Override
    public void createVersion(String formName, String formInternalName, FormVersionDO formVersionDO) {

//        Integer formId = formVersionDO.getFormId();
        String formVersionName = formVersionDO.getFormVersionName();

        Optional<FormDO> formOptional = formRepository.findByFormNameAndFormInternalName(formName, formInternalName);

        if(formOptional.isPresent()){

            FormDO form = formOptional.get();
            Integer formId = form.getId();

            Optional<FormVersionDO> versionOptional = formVersionRepository.findByFormIdAndFormVersionName(formId, formVersionName);
            if(!versionOptional.isPresent()){

                FormVersionDO newVersion = new FormVersionDO();

                newVersion.setFormId(form.getId());
                newVersion.setFormVersionName(formVersionDO.getFormVersionName());

                formVersionRepository.save(newVersion);

            }else {

                throw new EntityNotFoundException("Version already exists!");
            }
        }else{
            throw new EntityNotFoundException("Form Not exists!");
        }

    }
//    @Override
//    public void createVersion(String formInternalName, FormVersionDO formVersionDO) {
//
//        Integer formId = formVersionDO.getFormId();
//        String formVersionName = formVersionDO.getFormVersionName();
//
//        Optional<FormDO> formOptional = formRepository.findByFormInternalName(formInternalName);
//        if(formOptional.isPresent()){
//            Optional<FormVersionDO> versionOptional = formVersionRepository.findByFormIdAndFormVersionName(formId, formVersionName);
//            if(!versionOptional.isPresent()){
//                FormDO form = formOptional.get();
//
//                FormVersionDO newVersion = new FormVersionDO();
//
//                newVersion.setFormId(form.getId());
//                newVersion.setFormVersionName(formVersionDO.getFormVersionName());
//
//                formVersionRepository.save(newVersion);
//
//            }else {
//
//                throw new EntityNotFoundException("Version already exists!");
//            }
//        }else{
//            throw new EntityNotFoundException("Form Not exists!");
//        }
//
//    }
    @Override
    public List<FormVersionDO> getVersion() {
        return formVersionRepository.findByActiveTrue();
    }

//    @Override
//    public void updateVersion(FormVersionDO formVersionDO) {
//        Optional<FormVersionDO> optionalVersion = formVersionRepository.findById(formVersionDO.getId());
//        if(optionalVersion.isPresent()){
//            Optional<FormDO> optionalForm = formRepository.findById(formVersionDO.getFormId());
//            if(optionalForm.isPresent()){
//                FormVersionDO updatedVersion = optionalVersion.get();
//
//                updatedVersion.setFormVersionName(formVersionDO.getFormVersionName());
//                updatedVersion.setFormId(formVersionDO.getFormId());
//
//                formVersionRepository.save(updatedVersion);
//            }else{
//                throw new EntityNotFoundException("Form does not exists!");
//            }
//        }else {
//            throw new EntityNotFoundException("Version does not exists!");
//
//        }
//    }

    @Override
    public void updateVersion(Integer formId, Integer versionId, FormVersionDO formVersionDO) {
        Optional<FormVersionDO> optionalVersion = formVersionRepository.findById(versionId);
        if(optionalVersion.isPresent()){

            Optional<FormVersionDO> versionOptional = formVersionRepository.findByFormIdAndFormVersionName(formId, formVersionDO.getFormVersionName());
            if(!versionOptional.isPresent()){
                FormVersionDO updatedVersion = optionalVersion.get();

                if(!formVersionDO.getFormVersionName().equals(updatedVersion.getFormVersionName())){
                    System.out.println(formVersionDO.getFormVersionName());

                    updatedVersion.setFormVersionName(formVersionDO.getFormVersionName());

                    formVersionRepository.save(updatedVersion);
                }else{
                    throw new EntityNotFoundException("Please rename the version!");
                }

            }else {
                throw new EntityNotFoundException("Version Already not exists!");
            }
        }else {
            throw new EntityNotFoundException("Version does not exists!");
        }
    }

    @Override
    public void deleteVersion(Integer Id) {
        Optional<FormVersionDO> optionalVersion = formVersionRepository.findById(Id);
        if(optionalVersion.isPresent()){
            FormVersionDO version = optionalVersion.get();

            version.setActive(false);

            formVersionRepository.save(version);
        }else{
            throw new EntityNotFoundException("Version does not exists!");
        }
    }
}
