package com.newform.New.Form.service;

import com.newform.New.Form.entity.domain.FormDO;
import com.newform.New.Form.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FormService {
    public ResponseEntity<ApiResponse<FormDO>> createForm(FormDO formDO);

    public List<FormDO> getForm();

    public ResponseEntity<ApiResponse<FormDO>> updateForm(Integer Id, FormDO formDO);

    public void deleteForm(Integer Id);
}
