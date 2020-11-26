package com.katabankaccount.katabankaccount.controller;


import com.katabankaccount.katabankaccount.model.Operation;
import com.katabankaccount.katabankaccount.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/operations")
public class OperationController {

    private OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping(name = "/")
    public ResponseEntity<List<Operation>> getOperations(@RequestParam(name = "accountId")Integer accountId,
                                                         @RequestParam(name = "startOperationDate")Instant startOperationDate,
                                                         @RequestParam(name = "endOperationDate")Instant endOperationDate) {
        return new ResponseEntity<>(operationService.findOperations(accountId, startOperationDate, endOperationDate), HttpStatus.OK);
    }

    @PostMapping(name = "/")
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
        return new ResponseEntity<>(operationService.saveOperation(operation), HttpStatus.OK);
    }

}
