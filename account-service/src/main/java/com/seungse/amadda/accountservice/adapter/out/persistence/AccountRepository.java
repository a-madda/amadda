package com.seungse.amadda.accountservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
