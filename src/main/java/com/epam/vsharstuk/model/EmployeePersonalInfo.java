package com.epam.vsharstuk.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "emplyee_personal_info")
public class EmployeePersonalInfo implements Serializable {

    @Id
    @GenericGenerator(name = "auto_inc", strategy = "increment")
    @GeneratedValue(generator = "auto_inc")
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;


    @Column(name = "passport_data")
    private BigDecimal passportData;

    @OneToOne(mappedBy = "employeePersonalInfo")
    private Employee employee;

    public EmployeePersonalInfo() {
        //constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getPassportData() {
        return passportData;
    }

    public void setPassportData(BigDecimal passportData) {
        this.passportData = passportData;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
