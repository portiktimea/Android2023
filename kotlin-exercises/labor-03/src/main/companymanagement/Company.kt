package main.companymanagement

class Company {

    private val employees = mutableListOf<Employee>()

    fun addEmployee(employee: Employee) : Boolean{
        return if(!employees.contains(employee)){
            employees.add(employee)
            true
        } else{
            false
        }
    }

    fun displayEmployees(){
        employees.map{it.displayInfo()}
    }

    fun displayManagers() {
        val managers = employees.filterIsInstance<Manager>()
        managers.forEach { it.displayInfo() }
    }

    fun doubleSalaryOfManagers() {
        val managers = employees.filterIsInstance<Manager>()
        managers.forEach { it.setSalary(it.getSalary() * 2) }
    }

    fun fireEmployee(id: Int): Boolean {
        val employee = employees.find { it.employeeID == id }
        return if (employee != null) {
            employees.remove(employee)
            true
        } else {
            false
        }
    }

    fun orderBy(criteria: OrderBy) {
        when (criteria) {
            OrderBy.NAME -> employees.sortBy { it.name }
            OrderBy.SALARY -> employees.sortBy { it.getSalary() }
        }
    }

}