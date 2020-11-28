package com.katabankaccount.katabankaccount.controller;


import com.katabankaccount.katabankaccount.model.Client;
import com.katabankaccount.katabankaccount.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<String> getDashboard() {
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/clients" )
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.OK);
    }
}
