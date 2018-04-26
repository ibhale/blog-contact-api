package com.blog.contact.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.contact.model.Account;
/*
 * Account repository to interact with account table
 * */
public interface AccountRepository extends JpaRepository<Account, Long>  {

	Account findByEmail(String email);
	Account findByUserName(String userName);
	
}