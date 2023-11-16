//package com.newform.New.Form.entity.domain;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "client")
//public class ClientDO {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "client_Id")
//    private Integer id;
//
//    @Column(name = "client_Name")
//    private String clientName;
//
//    @Column(name = "client_Description")
//    private String clientDescription;
//
//    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT true")
//    private Boolean active = true;
//
//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    private List<FormDO> forms;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getClientName() {
//        return clientName;
//    }
//
//    public void setClientName(String clientName) {
//        this.clientName = clientName;
//    }
//
//    public String getClientDescription() {
//        return clientDescription;
//    }
//
//    public void setClientDescription(String clientDescription) {
//        this.clientDescription = clientDescription;
//    }
//
//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
//
//    public List<FormDO> getForms() {
//        return forms;
//    }
//
//    public void setForms(List<FormDO> forms) {
//        this.forms = forms;
//    }
//
//    @Override
//    public String toString() {
//        return "ClientDO{" +
//                "id=" + id +
//                ", clientName='" + clientName + '\'' +
//                ", clientDescription='" + clientDescription + '\'' +
//                ", active=" + active +
//                '}';
//    }
//}
