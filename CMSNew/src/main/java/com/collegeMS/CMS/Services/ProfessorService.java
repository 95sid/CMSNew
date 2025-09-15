package com.collegeMS.CMS.Services;

import com.collegeMS.CMS.Entity.Professor;
import com.collegeMS.CMS.Exceptions.ProfessorNotFoundException;
import com.collegeMS.CMS.Repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<Professor> getAllProfessor() {
        return professorRepository.findAll();
    }


    public Professor getProfessorById(Long professorId) {
        // not exist will throw an exception
        isProfessorIdExist(professorId);
        Optional<Professor> professor = professorRepository.findById(professorId);
        return professor.get();
    }


    public Professor updateProfessorById(Long professorId,Professor professor) {
        isProfessorIdExist(professorId);
        return professorRepository.save(professor);
    }

    public Boolean deleteProfessorById(Long professorId) {
        isProfessorIdExist(professorId);
        professorRepository.deleteById(professorId);
        return true;
    }



    private void isProfessorIdExist(Long professorId) {
        if(!professorRepository.existsById(professorId)) throw new ProfessorNotFoundException("Professor with this id doesn't Exist : "+ professorId);
    }


    public Professor createNewProfessor(Professor professor) {
        return professorRepository.save(professor);
    }
}
