package com.blog.contact.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.contact.model.Phone;
/*
 * Phone repository to interact with phone table
 * */
public interface PhoneRepository extends JpaRepository<Phone, Long>  {

	 Collection<Phone> findByAccountUserName(String userName);
}