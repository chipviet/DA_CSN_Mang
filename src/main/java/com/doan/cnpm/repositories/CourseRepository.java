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
    @Query("SELECT n from Course n where n.id= :id ")
    Course findOneById (@Param("id") Long id);

    @Query("SELECT n from Course n where n.idNeed = :idNeed ")
    Course findOneByidNeed (@Param("idNeed") Long idNeed);

    @Query("SELECT n from Course n where n.idTutor = :idTutor ")
    Course findOneByidTutor(@Param("idTutor") Long idTutor);


    @Transactional
    @Modifying
    @Query("DELETE from Course n where n.id = :id ")
    void deleteById(@Param("id") Long id);
}
