package com.tk.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 18, message = "Age must be greater than or equal to 18")
    @Max(value = 150, message = "Age must be less than or equal to 150")
    @Column(nullable = false)
    private Long age;

    @NotBlank
    private String name;

    public Customer() {
    }

    public Customer(@Min(value = 18, message = "Age must be greater than or equal to 18") @Max(value = 150, message = "Age must be less than or equal to 150") Long age, String name) {
        this.age = age;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, age, name);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
