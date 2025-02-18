package net.PracticeSpringBootReactJS.ems_backend.controller;

import lombok.AllArgsConstructor;
import net.PracticeSpringBootReactJS.ems_backend.dto.EmployeeDto;
import net.PracticeSpringBootReactJS.ems_backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor  // Automatically creates a constructor with all class fields as arguments
@RestController      // Marks this class as a Spring REST controller
@RequestMapping("/api/employees")  // Base URL for all API endpoints in this class
public class EmployeeController {
    private EmployeeService employeeService;  // Service class for business logic

    // Create Employee - Adds a new employee to the database
    @PostMapping  // Maps HTTP POST requests to this method
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        // Calls service to create an employee and returns the saved employee with status 201 (CREATED)
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Get Employee by ID - Returns an employee's details by their ID
    @GetMapping("{id}")  // Maps GET requests with an 'id' parameter in the URL
    public ResponseEntity<EmployeeDto> createEmployee(@PathVariable("id") Long employeeId) {
        // Calls service to get employee details by ID and returns the details with status 200 (OK)
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    // Get All Employees - Returns a list of all employees
    @GetMapping()
    @RequestMapping("/all")  // Endpoint for listing all employees
    public ResponseEntity<List<EmployeeDto>> createEmployee() {
        // Calls service to fetch all employees and returns the list with status 200 (OK)
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Update Employee - Updates an existing employee's details
    @PutMapping("{id}")  // Maps HTTP PUT requests with an 'id' parameter
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee) {
        // Calls service to update the employee with the given ID and returns the updated employee details
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    // Delete Employee - Deletes an employee by their ID
    @DeleteMapping("{id}")  // Maps HTTP DELETE requests with an 'id' parameter
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        // Calls service to delete the employee and returns a success message
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
