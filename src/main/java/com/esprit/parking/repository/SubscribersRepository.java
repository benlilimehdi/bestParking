package com.esprit.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.parking.entities.Subscribers;

public interface SubscribersRepository extends JpaRepository<Subscribers, Long> {

}
