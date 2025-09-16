package com.collegeMS.CMS.Services;

import com.collegeMS.CMS.DTOs.ProfessorDTO;
import com.collegeMS.CMS.Entity.Professor;
import com.collegeMS.CMS.Exceptions.ProfessorNotFoundException;
import com.collegeMS.CMS.Repository.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ModelMapper mapper;

    public ProfessorService(ProfessorRepository professorRepository, ModelMapper mapper) {
        this.professorRepository = professorRepository;
        this.mapper = mapper;
    }


    public List<ProfessorDTO> getAllProfessor() {
        List<Professor> professors =  professorRepository.findAll();
        return professors.stream()
                .map(professor->mapper.map(professor,ProfessorDTO.class))
                .toList();
    }


    public ProfessorDTO getProfessorById(Long professorId) {
        // not exist will throw an exception
        Professor professor = professorRepository
                .findById(professorId)
                .orElseThrow(()->new ProfessorNotFoundException("Professor with this id:"+ professorId +" doesn't Exist"));

        return mapper.map(professor,ProfessorDTO.class);
    }

    @Transactional
    public ProfessorDTO updateProfessorById(Long professorId,ProfessorDTO professorDto) {
        isProfessorIdExist(professorId);
        Professor professor = mapper.map(professorDto,Professor.class);
        professor.setId(professorId);
        return mapper.map(professorRepository.save(professor),ProfessorDTO.class);
    }
    @Transactional
    public Boolean deleteProfessorById(Long professorId) {
        isProfessorIdExist(professorId);
        professorRepository.deleteById(professorId);
        return true;
    }
    @Transactional
    public ProfessorDTO createNewProfessor(ProfessorDTO professorDto) {
        Professor professor = mapper.map(professorDto,Professor.class);
        return mapper.map(professorRepository.save(professor),ProfessorDTO.class);
    }

    private void isProfessorIdExist(Long professorId) {
        if(!professorRepository.existsById(professorId)) throw new ProfessorNotFoundException("Professor with this id:"+ professorId +" doesn't Exist");
    }
}
