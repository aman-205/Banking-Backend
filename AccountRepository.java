package com.aman.BankingApplication.repository;

import com.aman.BankingApplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

//jparepo provide various function to use the daatabase like to add to ectract data from database
// it takes to parameter , table name and primary key type

public interface AccountRepository extends JpaRepository<Account,Long> {

}
