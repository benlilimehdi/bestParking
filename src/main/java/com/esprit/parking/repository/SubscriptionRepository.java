package com.esprit.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.parking.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
