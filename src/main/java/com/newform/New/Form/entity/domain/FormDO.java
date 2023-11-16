package com.newform.New.Form.entity.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "form")
public class FormDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_Id")
    private Integer id;

    @Column(name = "form_Name")
    private String formName;

    @Column(name = "form_Internal_Name")
    private String formInternalName;




    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;


    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormVersionDO> formVersions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormInternalName() {
        return formInternalName;
    }

    public void setFormInternalName(String formInternalName) {
        this.formInternalName = formInternalName;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }



    public List<FormVersionDO> getFormVersions() {
        return formVersions;
    }

    public void setFormVersions(List<FormVersionDO> formVersions) {
        this.formVersions = formVersions;
    }

    @Override
    public String toString() {
        return "FormDO{" +
                "id=" + id +
                ", formName='" + formName + '\'' +
                ", formInternalName='" + formInternalName + '\'' +
                ", active=" + active +
                ", formVersions=" + formVersions +
                '}';
    }


}
