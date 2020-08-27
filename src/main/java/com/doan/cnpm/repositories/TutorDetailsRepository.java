package com.doan.cnpm.repositories;


import com.doan.cnpm.domain.TutorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the TutorDetails domain.
 */
@SuppressWarnings("unused")
@Repository
public interface TutorDetailsRepository extends JpaRepository<TutorDetails, Long> {

}