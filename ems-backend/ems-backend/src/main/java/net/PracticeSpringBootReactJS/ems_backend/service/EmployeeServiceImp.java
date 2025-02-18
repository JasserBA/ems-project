package net.PracticeSpringBootReactJS.ems_backend.service;

import lombok.AllArgsConstructor;
import net.PracticeSpringBootReactJS.ems_backend.dto.EmployeeDto;
import net.PracticeSpringBootReactJS.ems_backend.entity.Employee;
import net.PracticeSpringBootReactJS.ems_backend.exception.ResourceNotFoundException;
import net.PracticeSpringBootReactJS.ems_backend.mapper.EmployeeMapper;
import net.PracticeSpringBootReactJS.ems_backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor  // Automatically generates a constructor for all fields
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // Create a new employee
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);  // Convert DTO to entity
        Employee savedEmployee = employeeRepository.save(employee);  // Save employee to DB
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);  // Convert saved entity back to DTO
    }

    // Get an employee by their ID
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with given ID: " + employeeId + " does not exist!"));
        return EmployeeMapper.mapToEmployeeDto(employee);  // Convert entity to DTO and return
    }

    // Get all employees
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();  // Fetch all employees
        return employees.stream()  // Convert list of entities to DTOs
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    // Update an existing employee
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with given ID: " + employeeId + " does not exist!"));

        // Update employee fields
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);  // Save updated employee to DB
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);  // Return updated employee as DTO
    }

    // Delete an employee by their ID
    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with given ID: " + employeeId + " does not exist!"));
        employeeRepository.deleteById(employeeId);  // Delete employee from DB
    }
}
