//package com.newform.New.Form.controller;
//
//import com.newform.New.Form.entity.domain.ClientDO;
//import com.newform.New.Form.service.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/client")
//public class ClientController {
//
//    @Autowired
//    private ClientService clientService;
//
//    @Autowired
//    public JdbcTemplate jdbcTemplate;
//
//    @PostMapping("/create")
//    public void createClient(@RequestBody ClientDO clientDO){
//        clientService.createClient(clientDO);
//    }
//
//    @GetMapping("/get")
//    public List<ClientDO> getClient(){
//        return clientService.getClient();
//    }
////@GetMapping("/get")
////public ResponseEntity<List<ClientDO>> getClient() {
////    List<ClientDO> clients = clientService.getClient();
////    return new ResponseEntity<>(clients, HttpStatus.OK);
////}
//
//    @PutMapping("/update")
//    public void updateClient(@RequestBody ClientDO clientDO){
//        clientService.updateClient(clientDO);
//    }
//
//    @DeleteMapping("/delete/{client_id}")
//    public void deleteClient(@PathVariable("client_id") Integer id){
//        clientService.deleteClient(id);
//    }
//}
