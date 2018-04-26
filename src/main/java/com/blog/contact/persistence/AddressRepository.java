package com.blog.contact.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.contact.model.Address;
/*
 * Address repository to interact with address table
 * */
public interface AddressRepository extends JpaRepository<Address, Long>  {

	 Collection<Address> findByAccountUserName(String userName);
}