package pl.imsi.lab2a;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    List<Employee> findAllByOrderByLastnameAsc();
    List<Employee> findAllByOrderByLastnameAsc(Pageable pageable);

    List<Employee> findAllByOrderByLastnameAscFirstnameDesc();

    List<Employee> findByFirstnameIgnoreCase(String name);

    List<Employee> findBySalaryBetween(BigDecimal start, BigDecimal end);
    List<Employee> findBySalary(BigDecimal salary, Pageable pageable);
    Employee findByIdAndDepartment(Long id, Department department);
}
