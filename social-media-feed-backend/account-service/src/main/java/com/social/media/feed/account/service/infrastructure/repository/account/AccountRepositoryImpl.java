package com.social.media.feed.account.service.infrastructure.repository.account;

import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountRepositoryMapper accountRepositoryMapper;
    private final AccountJpaRepository accountJpaRepository;

    public AccountRepositoryImpl(AccountRepositoryMapper accountRepositoryMapper, AccountJpaRepository accountJpaRepository) {
        this.accountRepositoryMapper = accountRepositoryMapper;
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = accountRepositoryMapper.accountToAccountEntity(account);
        try {
            accountEntity = accountJpaRepository.save(accountEntity);
            return accountRepositoryMapper.accountEntityToAccount(accountEntity);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new AccountDomainException("Error while saving account for user " + account.getUsername()
                    + ", error: " + e.getMessage(), e);
        }
    }

    @Override
    public Account findAccountByUsername(String username) {
        Optional<AccountEntity> accountEntity = accountJpaRepository.findAccountEntityByUsername(username);
        return accountEntity.map(accountRepositoryMapper::accountEntityToAccount).orElse(null);
    }
}
