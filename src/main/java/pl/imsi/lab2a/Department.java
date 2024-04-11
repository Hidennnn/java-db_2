package pl.imsi.lab2a;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDept;
    private String departmentName;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<Employee> employeeList;

    public Department() {}

    public Department(String departmentName, List<Employee> employeeList) {
        this.departmentName = departmentName;
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "idDept=" + idDept +
                ", departmentName='" + departmentName + '\'' +
                ", employeeSet=" + employeeList +
                '}';
    }

    public long getIdDept() {
        return idDept;
    }

    public void setIdDept(long idDept) {
        this.idDept = idDept;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
