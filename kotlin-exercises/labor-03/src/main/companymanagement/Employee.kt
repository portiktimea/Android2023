package main.companymanagement

var count = 0
open class Employee(val name: String, private var salary: Double){
    val employeeID = ++count

    fun getSalary() : Double{
        return this.salary
    }

    fun setSalary(newSalary: Double){
        salary += newSalary
    }

    open fun displayInfo(){
        println("ID: $employeeID, Name: $name, Salary: $salary")
    }

}