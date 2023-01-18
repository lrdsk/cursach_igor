package ru.yura;


import io.micronaut.http.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller("/api")
public class ApiController {
    private ShopRepo shopRepo;
    private EmployeeRepo employeeRepo;

    public ApiController(ShopRepo shopRepo, EmployeeRepo employeeRepo) {
        this.shopRepo = shopRepo;
        this.employeeRepo = employeeRepo;
    }

    @Get("/shop")
    public Shop getShop() throws SQLException {
       return this.shopRepo.getInfoAboutShopFromBase();
    }
    @Post("/shop")
    public void setShop(@Body Shop shop) throws SQLException {
        this.shopRepo.setInfoAboutShopToBase(
                shop.getNameOfShop(),
                shop.getAddress(),
                shop.getSpecializationOfShop(),
                shop.getNameOfDirector()
        );
    }
    @Get("/employees")
    public List<Employee> getEmployees() throws SQLException {
        return this.employeeRepo.getAllEmployees();
    }
    @Get("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) throws SQLException {
        return this.employeeRepo.getInfoAboutEmployeeFromDB(id);
    }
    @Post("/employees")
    public void createEmployee(@Body Employee employee) throws SQLException {
        this.employeeRepo.setInfoAboutEmployeeToDB(employee);
    }
    @Patch("/employees/{id}")
    public void patchEmployee(@PathVariable int id, @Body Employee employee) throws SQLException {
        this.employeeRepo.redactEmployee(id, employee.getNameEmployee(), employee.getSalaryEmployee());
    }
    @Delete("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) throws SQLException {
        this.employeeRepo.deleteEmployeeByName(id);
    }
}