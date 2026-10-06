package org.example.autolocapi.service;

import org.example.autolocapi.domain.Employe;

import java.util.List;

public interface IEmployeService {

    List<Employe> retrieveAllEmployes();

    Employe addEmploye(Employe e);

    Employe updateEmploye(Employe e);

    Object retrieveEmploye(Long idEmploye);

    void removeEmploye(Long idEmploye);

    List<Employe> addEmployes(List<Employe> employes);
}