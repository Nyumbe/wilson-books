package com.example.WilsonBooksAPI.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderedRepository extends JpaRepository<OrderedBook, Long> {
}