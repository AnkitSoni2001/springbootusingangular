package com.newform.New.Form.entity.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "form_Version")
public class FormVersionDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_Version_Id")
    private Integer id;

    @Column(name = "form_Version_Name")
    private String formVersionName;

    @Column(name = "form_Id")
    @NonNull
    private Integer formId;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "form_Id", referencedColumnName = "form_Id", insertable = false, updatable = false)
    private FormDO form;

    @Override
    public String toString() {
        return "FormVersionDO{" +
                "id=" + id +
                ", formVersionName='" + formVersionName + '\'' +
                ", formId=" + formId +
                ", active=" + active +
                ", form=" + form +
                ", formContents=" + formContents +
                '}';
    }

    @OneToMany(mappedBy = "formVersions", cascade = CascadeType.ALL)
    private List<FormContentDO> formContents;

    public List<FormContentDO> getFormContents() {
        return formContents;
    }

    public void setFormContents(List<FormContentDO> formContents) {
        this.formContents = formContents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormVersionName() {
        return formVersionName;
    }

    public void setFormVersionName(String formVersionName) {
        this.formVersionName = formVersionName;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public FormDO getForm() {
        return form;
    }

    public void setForm(FormDO form) {
        this.form = form;
    }
}
