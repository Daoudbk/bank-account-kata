package com.katabankaccount.katabankaccount.controller;


import com.katabankaccount.katabankaccount.model.Operation;
import com.katabankaccount.katabankaccount.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "api")
public class OperationController {

    @Autowired
    OperationService operationService;

    @GetMapping(value = "/operations")
    public ResponseEntity<List<Operation>> getOperations(@RequestParam(name = "accountId") Integer accountId,
                                                         @RequestParam(name = "startOperationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startOperationDate,
                                                         @RequestParam(name = "endOperationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endOperationDate) {
        return new ResponseEntity<>(operationService.findOperations(accountId, startOperationDate.atZone(ZoneId.of("UTC")).toInstant(), endOperationDate.atZone(ZoneId.of("UTC")).toInstant()), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
        return new ResponseEntity<>(operationService.saveOperation(operation), HttpStatus.OK);
    }

}
