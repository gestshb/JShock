package com.spring.repository;

import com.spring.bean.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findFirstByIdEqualsOrCarIdEquals(Long id, String idCar);

    @Query("delete from Report r where r.date_ < :DateToDelete")
    @Modifying
    void deleteOldRows(@Param("DateToDelete") LocalDate DateToDelete);

}
