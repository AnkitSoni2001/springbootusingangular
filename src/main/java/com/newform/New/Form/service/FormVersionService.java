package com.newform.New.Form.service;

import com.newform.New.Form.entity.domain.FormVersionDO;

import java.util.List;

public interface FormVersionService {
//    public void createVersion(FormVersionDO formVersionDO);

    public void createVersion(String formName, String formInternalName, FormVersionDO formVersionDO);

    public List<FormVersionDO> getVersion();

//    public void updateVersion(FormVersionDO formVersionDO);

    public void updateVersion(Integer formId, Integer versionId, FormVersionDO formVersionDO);

    public void deleteVersion(Integer Id);
}
