package com.doan.cnpm.repositories;

import com.doan.cnpm.domain.Authority;
import com.doan.cnpm.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String> {

    @Query("SELECT t.authorityName from UserAuthority t where t.userId = :userId")
    String getAuthorialUserId(@Param("userId") String userId);
}
