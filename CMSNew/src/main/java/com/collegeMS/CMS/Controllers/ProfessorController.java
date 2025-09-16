package com.collegeMS.CMS.Controllers;

import com.collegeMS.CMS.DTOs.ProfessorDTO;
import com.collegeMS.CMS.Entity.Professor;
import com.collegeMS.CMS.Repository.ProfessorRepository;
import com.collegeMS.CMS.Services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProfessorDTO>> getAllProfessor(){
        List<ProfessorDTO> professors = professorService.getAllProfessor();
        return ResponseEntity.ok(professors);
    }

    @GetMapping(value = "{professorId}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long professorId){
        ProfessorDTO professor = professorService.getProfessorById(professorId);
        return ResponseEntity.ok(professor);
    }

    @PutMapping(value="{professorId}")
    public ResponseEntity<ProfessorDTO> updateProfessorById(@PathVariable Long professorId,@Valid @RequestBody ProfessorDTO professorDto){
        ProfessorDTO professor = professorService.updateProfessorById(professorId,professorDto);
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping(value="{professorId}")
    public ResponseEntity<Boolean> deleteProfessorById(@PathVariable Long professorId){
        Boolean isDeleted = professorService.deleteProfessorById(professorId);
        if(isDeleted){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> createNewProfessor(@Valid @RequestBody ProfessorDTO professorDTO){
        ProfessorDTO professor = professorService.createNewProfessor(professorDTO);
        return new ResponseEntity<>(professor, HttpStatus.CREATED);
    }
}
