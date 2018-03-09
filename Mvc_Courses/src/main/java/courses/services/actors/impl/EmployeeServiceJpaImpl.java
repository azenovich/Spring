package courses.services.actors.impl;

import courses.models.actors.Employee;
import courses.repositories.actors.EmployeeRepository;
import courses.services.actors.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class EmployeeServiceJpaImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public Employee create(Employee model) {
        return employeeRepository.save(model);
    }

    @Override
    public Employee edit(Employee model) {
        return employeeRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.delete(id);
    }
}
