package com.collegeMS.CMS.Controllers;

import com.collegeMS.CMS.Entity.Professor;
import com.collegeMS.CMS.Repository.ProfessorRepository;
import com.collegeMS.CMS.Services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/CMS/Professor/")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @GetMapping
    public List<Professor> getAllProfessor(){
        List<Professor> professors = professorService.getAllProfessor();
        return professors;
    }

    @GetMapping(value = "{professorId}")
    public Professor getProfessorById(@PathVariable Long professorId){
        return professorService.getProfessorById(professorId);
    }

    @PutMapping(value="{professorId}")
    public Professor updateProfessorById(@PathVariable Long professorId,@RequestBody Professor professor){
        return professorService.updateProfessorById(professorId,professor);
    }

    @DeleteMapping(value="{professorId}")
    public Boolean deleteProfessorById(@PathVariable Long professorId){
        return professorService.deleteProfessorById(professorId);
    }

    @PostMapping
    public Professor createNewProfessor(@RequestBody @Valid Professor professor){
        return professorService.createNewProfessor(professor);
    }
}
