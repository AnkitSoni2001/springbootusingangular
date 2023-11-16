//package com.newform.New.Form.service.impl;
//
//import com.newform.New.Form.entity.domain.ClientDO;
//import com.newform.New.Form.repository.ClientRepository;
//import com.newform.New.Form.service.ClientService;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ClientServiceImpl implements ClientService {
//
//    @Autowired
//    private ClientRepository clientRepository;
//    @Override
//    public void createClient(ClientDO clientDO) {
//        Optional<ClientDO> optionalForm = clientRepository.findById(clientDO.getId());
//        if(!optionalForm.isPresent()){
//            clientRepository.save(clientDO);
//        }else{
//            throw new EntityNotFoundException("Client already existsssss!");
//        }
//    }
//
//    @Override
//    public List<ClientDO> getClient() {
//        return clientRepository.findAll();
//    }
//
//    @Override
//    public ClientDO updateClient(ClientDO clientDO) {
//        Optional<ClientDO> oldClient = clientRepository.findById(clientDO.getId());
//        if(oldClient.isPresent()){
//            ClientDO newClient = oldClient.get();
//
//            newClient.setClientName(clientDO.getClientName());
//            newClient.setClientDescription(clientDO.getClientDescription());
//
//            return clientRepository.save(newClient);
//
//        }else {
//            throw new EntityNotFoundException("Client does not esists!");
//        }
//
//    }
//
//    @Override
//    public void deleteClient(Integer id) {
//        Optional<ClientDO> optionalClient = clientRepository.findById(id);
//        if(optionalClient.isPresent()){
//            ClientDO client = optionalClient.get();
//            client.setActive(false);
//            clientRepository.save(client);
//        }else {
//            throw new EntityNotFoundException("Client does not esists!");
//
//        }
//    }
//}
