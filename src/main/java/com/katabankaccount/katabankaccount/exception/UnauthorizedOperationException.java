package com.katabankaccount.katabankaccount.exception;


import com.katabankaccount.katabankaccount.model.Account;
import com.katabankaccount.katabankaccount.model.Operation;

public class UnauthorizedOperationException extends RuntimeException {

    private static final String MESSAGE = "Unauthorized operation %s on account %s";

    public UnauthorizedOperationException(Account account, Operation operation) {
        super(String.format(MESSAGE, operation, account.getId()));
    }

}
