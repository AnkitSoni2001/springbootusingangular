package com.newform.New.Form.service;

import com.newform.New.Form.entity.domain.FormContentDO;
import com.newform.New.Form.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface FormContentService {
    public ResponseEntity<ApiResponse<FormContentDO>> createContent(Integer Id, FormContentDO formContentDO);
//    public void updateContent(FormContentDO formContentDO);

    public ResponseEntity<ApiResponse<FormContentDO>> updateContent(Long versionId, Long pageNumber, FormContentDO formContentDO);

    public ResponseEntity<ApiResponse<FormContentDO>> deleteContent(Long versionId, Long pageNumber);
}
