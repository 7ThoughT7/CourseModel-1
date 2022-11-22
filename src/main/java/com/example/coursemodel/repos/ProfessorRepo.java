package com.example.coursemodel.repos;

import com.example.coursemodel.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepo extends CrudRepository<Professor, Integer> {
    Professor getById(Integer professorId);
}
