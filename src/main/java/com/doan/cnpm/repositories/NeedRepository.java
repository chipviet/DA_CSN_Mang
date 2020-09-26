package com.doan.cnpm.repositories;

import com.doan.cnpm.domain.Need;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NeedRepository extends JpaRepository<Need, Long> {

    @Query("SELECT n from Need n where n.type = 'OPEN_NEED'")
    List<Need> findAllNeed();

    @Query("SELECT n from Need n where n.type = 'OPEN_COURSE'")
    List<Need> findAllCourse();

    @Query("SELECT n from Need n where n.id= :id ")
    Need findOneById (@Param("id") Long id);

    @Query("SELECT n from Need n where n.idUser = :idUser ")
    Need findOneByidUser (@Param("idUser") Long idUser);

    @Query("SELECT n from Need n where n.level = :level ")
    Need findOneBylevel (@Param("level") Long level);

    @Transactional
    @Modifying
    @Query("DELETE from Need n where n.id = :id ")
    void deleteById(@Param("id") Long id);
}
