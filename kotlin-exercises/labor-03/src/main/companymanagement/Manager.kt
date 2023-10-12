package main.companymanagement

class Manager(name: String, salary: Double, val department: String) : Employee(name, salary) {
    override fun displayInfo() {
        //println("ID: $employeeID, Name: $name, Salary: $salary, Department: $department")
    }

}
