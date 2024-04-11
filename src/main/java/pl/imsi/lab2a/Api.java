package pl.imsi.lab2a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Api {
    private final Manager manager;

    @Autowired
    public Api(Manager manager) {
        this.manager = manager;
    }

    @GetMapping("/employee/all")
    public Iterable<Employee> getAll() {
        return manager.findAll();
    }

    @GetMapping("/employee/id")
    public Optional<Employee> getById(@RequestParam Long index){
        return manager.findById(index);
    }

    @GetMapping(value = "/employee/{employeeId}")
    public Optional<Employee> getId (@PathVariable("employeeId") Long employeeId){
        return manager.findById(employeeId);
    }

    @DeleteMapping("/employee/id")
    public void deleteById(@RequestParam Long index){
        manager.deleteById(index);
    }

    @PostMapping(value = "/employee/employee")
    public Employee save(String firstname, String lastname, String salary) {
        return manager.save(new Employee(firstname, lastname,
                new BigDecimal(salary), null));
    }

    @GetMapping("/employee/all/order/lastnameAsc")
    public List<Employee> findAllByOrderByLastnameAsc(){
        return manager.findAllByOrderByLastnameAsc();
    }

    @GetMapping("/employee/all/paging")
    public Page<Employee> findAllPage(){
        return manager.findAllPage();
    }

    @GetMapping("/employee/all/paging/order/lastnameAsc")
    public List<Employee> findAllByOrderByLastnameAscPage(){
        return manager.findAllByOrderByLastnameAscPage();
    }

    @GetMapping("/employee/all/order/lastnameAsc_firstnameDesc")
    public List<Employee> findAllByOrderByLastnameAscByFirstnameDesc(){
        return manager.findAllByOrderByLastnameAscByFirstnameDesc();
    }

    @GetMapping("/employee/name_ignorecase/{name}")
    public List<Employee> findByFirstnameIgnoreCase(@PathVariable("name") String name){
        return manager.findByFirstnameIgnoreCase(name);
    }

    @GetMapping("/employee/salary/between/{start}_{end}")
    public List<Employee> findBySalaryBetween(@PathVariable("start") String start, @PathVariable("end") String end){
        return manager.findBySalaryBetween(new BigDecimal(start), new BigDecimal(end));
    }

    @GetMapping("/employee/salary/n_first")
    public List<Employee> findBySalary(@RequestParam String salary, @RequestParam int n){
        return manager.findBySalary(new BigDecimal(salary), n);
    }

    @GetMapping("/department/id")
    public Department findByIdDept(@RequestParam Long id){
        return manager.findByIdDept(id);
    }

    @PostMapping("/department/save")
    public Department save(@RequestParam String name){
        return manager.save(new Department(name, new ArrayList<Employee>()));
    }

    @PutMapping("/employee/update")
    public Employee update(@RequestParam Long id, @RequestParam Long idDept){
        return manager.update(id, idDept);
    }

    @GetMapping("/employee/find_id_dept")
    public Employee findByIdAndDepartment(@RequestParam Long id, @RequestParam Long idDept){
        return manager.findByIdAndDepartment(id, idDept);
    }
}
