package com.jobspot.repository;

import com.jobspot.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TehRepository extends JpaRepository<Technology,Long> {
}
