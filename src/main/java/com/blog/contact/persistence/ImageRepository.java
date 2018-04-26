package com.blog.contact.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.contact.model.Image;
/*
 * Image repository to interact with image table
 * */
public interface ImageRepository extends JpaRepository<Image, Long>  {

	 Collection<Image> findByAccountUserName(String userName);
}