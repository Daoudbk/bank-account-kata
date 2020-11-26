package com.katabankaccount.katabankaccount.service;

import com.katabankaccount.katabankaccount.exception.UnauthorizedOperationException;
import com.katabankaccount.katabankaccount.model.Account;
import com.katabankaccount.katabankaccount.model.Operation;
import com.katabankaccount.katabankaccount.repository.AccountRepository;
import com.katabankaccount.katabankaccount.repository.OperationRepository;
import com.katabankaccount.katabankaccount.util.CurrencyUtil;
import com.katabankaccount.katabankaccount.util.ValidatorUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Currency;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class OperationService {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @Inject
    public OperationService(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = requireNonNull(accountRepository);
        this.operationRepository = requireNonNull(operationRepository);
    }

    public List<Operation> findOperations(Integer accountId,
                                          Instant startOperationDate,
                                          Instant endOperationDate) {

        return operationRepository
                .findOperationsByAccountIdAndDateBetweenOrderByDateDesc(accountId,
                        requireNonNull(startOperationDate),
                        requireNonNull(endOperationDate));
    }

    public Operation saveOperation(Operation operation) {
        ValidatorUtil.validate(operation);

        Account account = getAccount(operation);

        double newPotentialAccountAmount = getNewPotentialAccountAmount(account, operation);

        if (!isOperationAllowed(account, newPotentialAccountAmount)) {
            throw new UnauthorizedOperationException(account, operation);
        }

        account.setAmount(newPotentialAccountAmount);

        accountRepository.save(account);
        return  operationRepository.save(requireNonNull(operation));
    }

    private Account getAccount(Operation operation) {
        return requireNonNull(accountRepository.findById(operation.getAccountId()).get());
    }

    private double getNewPotentialAccountAmount(Account account, Operation operation) {

        double accountAmount = account.getAmount();
        double operationAmount = operation.getAmount();

        Currency accountCurrency = account.getCurrency();
        Currency operationCurrency = operation.getCurrency();

        double
                operationAmountWithAccountCurrency =
                CurrencyUtil.convertAmount(operationAmount, operationCurrency, accountCurrency);

        return accountAmount + operationAmountWithAccountCurrency;
    }

    private boolean isOperationAllowed(Account account, double newPotentialAccountAmount) {
        return account.isAllowNegativeAmount() || newPotentialAccountAmount >= 0;
    }
}
