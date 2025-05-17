package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.application.port.out.AccountRegisterOutPortV1;
import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.infrastructor.annotations.OutPortAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * AccountRegisterOutPortV1Adapter
 * <p>
 * 계정 등록 아웃포트 어댑터
 * </p>
 * <p>
 * 계정 등록을 위한 아웃포트 어댑터입니다.
 * </p>
 *
 * @author seungse
 */
@OutPortAdapter
@RequiredArgsConstructor
public class AccountRegisterOutPortV1Adapter implements AccountRegisterOutPortV1 {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> createAccount(Account account, String password) {
        AccountEntity accountEntity = AccountEntity.newEntityFrom(account);
        accountEntity.setEncryptedPassword(passwordEncoder.encode(password));
        accountRepository.save(accountEntity);
        return Optional.of(accountEntity.toDomain());
    }

}
