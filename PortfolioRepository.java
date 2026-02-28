package com.innomax.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.innomax.demo.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}