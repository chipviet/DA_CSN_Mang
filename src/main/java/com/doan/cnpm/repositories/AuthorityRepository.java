package com.doan.cnpm.repositories;

import com.doan.cnpm.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}

