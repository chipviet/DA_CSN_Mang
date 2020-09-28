package com.doan.cnpm.repositories;

import com.doan.cnpm.domain.Literacy;
import com.doan.cnpm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LiteracyRepository extends JpaRepository<Literacy, Long> {
}
