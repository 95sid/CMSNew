package com.collegeMS.CMS.Repository;

import com.collegeMS.CMS.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
