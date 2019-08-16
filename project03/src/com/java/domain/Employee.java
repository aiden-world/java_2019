/*
* 员工类
*
* */

package com.java.domain;

public class Employee {
    // 类变量
    private static int init = 1; // 员工编号初始值

    // 实例变量
    private int id; // 员工编号
    private String name;
    private int age;
    private double salary; // 薪水


    // 构造器
    public Employee() {}

    public Employee(String name, int age, double salary) {
        initIncrement();
//        this.name = name;
//        this.age = age;
//        this.salary = salary;
        setName(name);
        setAge(age);
        setSalary(salary);
    }


    // 方法
    private void initIncrement() {
        id = init;
        ++init;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 0 && name.length() <= 36) {
            this.name = name;
        } else {
            System.out.println("Employee name字段长度为[1- 36]");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Employee age范围(0, 150]");
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Employee salary[0, +∞)");
        }
    }

    public String getFields() {
        return " id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary;
    }
    @Override
    public String toString() {
        return "Employee{" +
                getFields() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (age != employee.age) return false;
        return name != null ? name.equals(employee.name) : employee.name == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
