package com.example.BookStore.repos;

import com.example.BookStore.entity.InforDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InforDeliveryRepos extends JpaRepository<InforDelivery, Long> {
    List<InforDelivery> findByUser_Id(Long userId);
}
