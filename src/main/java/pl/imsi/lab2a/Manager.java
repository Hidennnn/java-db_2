package pl.imsi.lab2a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class Manager {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    @Autowired
    public Manager(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAllByOrderByLastnameAsc(){
        return employeeRepository.findAllByOrderByLastnameAsc();
    }

    public Page<Employee> findAllPage(){
        return employeeRepository.findAll(PageRequest.of(0, 2));
    }

    public List<Employee> findAllByOrderByLastnameAscPage(){
        return employeeRepository.findAllByOrderByLastnameAsc(PageRequest.of(0, 2));
    }

    public List<Employee> findAllByOrderByLastnameAscByFirstnameDesc(){
        return employeeRepository.findAllByOrderByLastnameAscFirstnameDesc();
    }

    public List<Employee> findByFirstnameIgnoreCase(String name){
        return employeeRepository.findByFirstnameIgnoreCase(name);
    }

    public List<Employee> findBySalaryBetween(BigDecimal start, BigDecimal end){
        return employeeRepository.findBySalaryBetween(start, end);
    }

    public List<Employee> findBySalary(BigDecimal salary, int n){
        return employeeRepository.findBySalary(salary, PageRequest.of(0, n));
    }

    public Department findByIdDept(Long id){
        return departmentRepository.findByIdDept(id);
    }

    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public Employee update(Long id, Long idDept){
        Employee employee = null;
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        Optional<Department> optDepartment = departmentRepository.findById(idDept);

        if (optEmployee.isPresent() && optDepartment.isPresent()){
            employee = optEmployee.get();
            Department department = optDepartment.get();

            employee.setDepartment(department);
            employeeRepository.save(employee);
            List<Employee> deptEmpList = department.getEmployeeList();
            deptEmpList.add(employee);
            department.setEmployeeList(deptEmpList);
            departmentRepository.save(department);
        }
        return employee;
    }

    public Employee findByIdAndDepartment(Long id, Long idDept){
        return employeeRepository.findByIdAndDepartment(id, departmentRepository.findByIdDept(idDept));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Employee("Dowacki", "Budus", new BigDecimal("3500.40"), null));
        save(new Employee("Bowacki", "Audus", new BigDecimal("6820.50"), null));
        save(new Employee("Rowacki", "Budus", new BigDecimal("5000.50"), null));
        save(new Employee("Kowacki", "Audus", new BigDecimal("32100.50"), null));
        save(new Employee("Wowacki", "Cudus", new BigDecimal("3500.40"), null));
        save(new Employee("Uowacki", "Cudus", new BigDecimal("5000.50"), null));
        save(new Department("HR", null));
        save(new Department("IT", null));
        save(new Department("Magazine", null));
    }
}
