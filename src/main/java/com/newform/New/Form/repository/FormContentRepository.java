package com.newform.New.Form.repository;

import com.newform.New.Form.entity.domain.FormContentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormContentRepository extends JpaRepository<FormContentDO, Integer> {

//    Optional<FormContentDO> findByFormVersionIdPageNumber(Long formVersionIdAndPageNumber);

//    Optional<FormContentDO> findByIntegerFormVersionIdPageNumber(Integer integerFormVersionIdPageNumber);
    Optional<FormContentDO> findByFormVersionIdPageNumber(Long longFormVersionIdPageNumber);

    List<FormContentDO> findAllByFormVersionId(Long versionId);
}
