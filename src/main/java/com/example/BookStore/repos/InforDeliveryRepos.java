package com.example.BookStore.repos;

import com.example.BookStore.entity.InforDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforDeliveryRepos extends JpaRepository<InforDelivery, Long> {
}
