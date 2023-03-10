package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.hcsc.entity.CitizenRegistration;

public interface UserRegistrationRepo extends ReactiveMongoRepository<CitizenRegistration, Serializable> {

}
