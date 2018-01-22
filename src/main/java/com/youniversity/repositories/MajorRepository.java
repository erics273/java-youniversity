package com.youniversity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.youniversity.models.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {

}
