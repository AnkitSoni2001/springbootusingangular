package com.newform.New.Form.entity.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "form_Contents")
public class FormContentDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_Content_Id")
    private Integer id;

    @Column(name = "content_Data")
    @NotNull
    private String contentData;

    @Column(name = "page_Number")
    @NotNull
    private Long pageNumber;

    @Column(name = "form_Version_Id")
    @NonNull
    private Long formVersionId;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    @Column(name = "form_Version_Id_Page_Number")
    @NonNull
    private Long formVersionIdPageNumber;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "form_Version_Id", referencedColumnName = "form_Version_Id", insertable = false, updatable = false)
    private FormVersionDO formVersions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getFormVersionId() {
        return formVersionId;
    }

    public void setFormVersionId(Long formVersionId) {
        this.formVersionId = formVersionId;
    }

    public FormVersionDO getFormVersions() {
        return formVersions;
    }

    public void setFormVersions(FormVersionDO formVersions) {
        this.formVersions = formVersions;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getFormVersionIdPageNumber() {
        return formVersionIdPageNumber;
    }

    public void setFormVersionIdPageNumber(Long formVersionIdPageNumber) {
        this.formVersionIdPageNumber = formVersionIdPageNumber;
    }

    @Override
    public String toString() {
        return "FormContentDO{" +
                "id=" + id +
                ", contentData='" + contentData + '\'' +
                ", pageNumber=" + pageNumber +
                ", formVersionId=" + formVersionId +
                ", formVersions=" + formVersions +
                '}';
    }
}
