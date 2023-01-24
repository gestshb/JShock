package com.spring.service.impl;

import com.spring.bean.Report;
import com.spring.repository.ReportRepository;
import com.spring.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Override
    public Report save(Report entity) {
        return reportRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        reportRepository.deleteById(id);

    }
    @Override
    public Report findByIdOrCarId(Long id,String carId){
        return reportRepository.findFirstByIdEqualsOrCarIdEquals(id, carId==null?"-1":carId);
    }

    @Override
    public void deleteOldRows(LocalDate dateToDelete) {
        reportRepository.deleteOldRows(dateToDelete);
    }

    @Override
    public Report find(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}
