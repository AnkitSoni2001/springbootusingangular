package com.newform.New.Form.repository;

import com.newform.New.Form.entity.domain.FormDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<FormDO, Integer> {

    Optional<FormDO> findByFormInternalName(String formInternalName);

    List<FormDO> findByActiveTrue();



    Optional<FormDO> findByFormNameAndFormInternalName(String formName, String formInternalName);
}
