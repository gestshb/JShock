package com.spring.service;

import com.spring.bean.Report;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ReportService extends GenericService<Report> {
    Report findByIdOrCarId(Long id, String carId);
    void deleteOldRows(LocalDate DateToDelete);
}
