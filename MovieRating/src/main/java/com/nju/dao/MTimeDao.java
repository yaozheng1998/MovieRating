package com.nju.dao;

import com.nju.entity.MTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MTimeDao extends JpaRepository<MTime, Integer> {
}
