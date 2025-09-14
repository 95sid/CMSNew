package com.collegeMS.CMS.Repository;

import com.collegeMS.CMS.Entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord,Long> {
}
