package pl.imsi.lab2a;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private BigDecimal salary;

    @ManyToOne()
    @JoinColumn(name="idDept", foreignKey
            =@ForeignKey(name="FK_EmpDept"))
    private Department department;

    public Employee(){};

    public Employee(String firstname, String lastname, BigDecimal salary, Department department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
