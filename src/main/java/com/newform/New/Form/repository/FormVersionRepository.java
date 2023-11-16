package com.newform.New.Form.repository;

import com.newform.New.Form.entity.domain.FormDO;
import com.newform.New.Form.entity.domain.FormVersionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface FormVersionRepository extends JpaRepository<FormVersionDO, Integer> {

    Optional<FormVersionDO> findByFormIdAndFormVersionName(Integer formId, String formVersionName);

    List<FormVersionDO> findByActiveTrue();
}
