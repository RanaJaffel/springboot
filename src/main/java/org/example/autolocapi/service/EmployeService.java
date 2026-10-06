package org.example.autolocapi.service;

import org.example.autolocapi.domain.Employe;
import org.example.autolocapi.repository.IEmployeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService implements IEmployeService {

    private final IEmployeRepository employeRepository;

    public EmployeService(IEmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public List<Employe> retrieveAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe addEmploye(Employe e) {
        return employeRepository.save(e);
    }

    @Override
    public Employe updateEmploye(Employe e) {
        return employeRepository.save(e);
    }

    @Override
    public Object retrieveEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye).orElse(null);
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        employeRepository.deleteById(idEmploye);
    }

    @Override
    public List<Employe> addEmployes(List<Employe> employes) {
        return employeRepository.saveAll(employes);
    }
}