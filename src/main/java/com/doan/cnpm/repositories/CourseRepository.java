package com.doan.cnpm.repositories;

import com.doan.cnpm.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c from Course c where c.id= :id")
    Course findOneById (@Param("id") Long id);

    @Query("SELECT c from Course c where c.idStudent = :idStudent")
    Course findOneByidStudent (@Param("idStudent") Long idStudent);

    @Query("SELECT c from Course c where c.level = :level")
    Course findOneBylevel (@Param("level") Long level);

    @Transactional
    @Modifying
    @Query("DELETE from Course c where c.id = :id")
    void deleteById(@Param("id") Long id);
}
