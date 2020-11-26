package com.katabankaccount.katabankaccount.service;

import com.katabankaccount.katabankaccount.model.Account;
import com.katabankaccount.katabankaccount.repository.AccountRepository;
import com.katabankaccount.katabankaccount.repository.ClientRepository;
import com.katabankaccount.katabankaccount.util.ValidatorUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.notEmpty;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private ClientRepository clientRepository;

    @Inject
    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = requireNonNull(accountRepository);
        this.clientRepository = clientRepository;
    }

    public Account createAccount(Account account) {
        if (clientRepository.findById(account.getClientId()).isEmpty()) {
            throw new ObjectNotFoundException("Unable to locate client of account name: " + account.getName(), AccountService.class.getName());
        }
        return accountRepository.save(ValidatorUtil.validate(account));
    }

    public List<Account> findAccountsByClient(String clientId) {
        return accountRepository.findAccountsByClientId(notEmpty(clientId));
    }
}
