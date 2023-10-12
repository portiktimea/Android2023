package main

import main.companymanagement.Company
import main.companymanagement.Employee
import main.companymanagement.Manager
import main.companymanagement.OrderBy

fun main(args: Array<String>) {

    val company = Company()

    val employee = Employee("A", 2000.0)
    val employee2 = Employee("B", 3000.0)
    val employee3 = Employee("C", 1000.0)
    val employee4 = Employee("D", 6000.0)
    val employee5 = Employee("E", 5000.0)
    val employee6 = Employee("F", 8000.0)

    val manager = Manager("Ana", 4500.0, "H")
    val manager2 = Manager("Kris", 4500.0, "H")
    val manager3 = Manager("Tom", 4500.0, "F")
    val manager4 = Manager("Nick", 4500.0, "K")

    company.addEmployee(employee)
    company.addEmployee(employee2)
    company.addEmployee(employee3)
    company.addEmployee(employee4)
    company.addEmployee(employee5)
    company.addEmployee(employee6)
    company.addEmployee(manager)
    company.addEmployee(manager2)
    company.addEmployee(manager3)
    company.addEmployee(manager4)

    println("Employees:")
    company.displayEmployees()

    company.fireEmployee(4)
    company.doubleSalaryOfManagers()

    println("Emp 4 removed:")
    company.displayEmployees()

    println("Employees ordered:")
    company.orderBy(OrderBy.SALARY)
    company.displayEmployees()
}