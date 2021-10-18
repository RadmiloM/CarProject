package com.example.demo.controllers;

import com.example.demo.dao.CarsDaoSQL;
import com.example.demo.dao.ContractsDaoSQL;
import com.example.demo.dao.UsersDaoSQL;
import com.example.demo.model.CarsContractsModel;
import com.example.demo.model.CarsModel;
import com.example.demo.model.ContractsModel;
import com.example.demo.model.UsersModel;
import com.example.demo.parametersmodel.CarsParametersModel;
import com.example.demo.requestmodels.CarsRequestModel;
import com.example.demo.requestmodels.CarsRequestThrteenModel;
import com.example.demo.responsemodels.CarsResponseModel;
import com.example.demo.responsemodels.ResponseDateModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class CarsController {
    private static final CarsDaoSQL cars = new CarsDaoSQL();
    private static final UsersDaoSQL user = new UsersDaoSQL();
    private static final ContractsDaoSQL contracts = new ContractsDaoSQL();


    // This method should return lists of all cars.
    @GetMapping("/cars")
    public List<CarsResponseModel> getAllCars() {
        return cars.getAllCars();
    }

    // This method should return list of all cars if conditions are true.
    @GetMapping("/cars/search")
    public List<CarsResponseModel> getSpecificCar(@RequestParam(defaultValue = "1992") int year, @RequestParam(defaultValue = "Yugo") String make,
                                                  @RequestParam(defaultValue = "T320") String model, @RequestParam(defaultValue = "false") boolean authomatic,
                                                  @RequestParam(defaultValue = "220.4") double price, @RequestParam(defaultValue = "1111") int power,
                                                  @RequestParam(defaultValue = "4") int doors) {


        for (CarsParametersModel c : cars.getSpecificCars()) {
            if (c.getYear() >= year && c.getPrice() <= price && c.getPower() <= power && c.getMake().length() > 0) {
                return cars.getAllCars();

            }

        }
        return null;
    }



    // This method should return one car model.
    @GetMapping("/cars/{carId}")
    public CarsResponseModel getCar(@RequestParam("carId") UUID id) {
        return cars.getCar(id.toString());
    }

    //This method should update car model only if user is admin.
    @PatchMapping("/cars/{carId}")
    public CarsModel adminChangeCar(@RequestHeader("authorization") String adminId) {
        UsersDaoSQL user = new UsersDaoSQL();
        UUID id = UUID.randomUUID();
        for(UsersModel us : user.getAllUsers()){
            if(us.getUserId().equals(adminId) && us.isAdmin()){
                for(var c : cars.getAllCars()){
                    return new CarsModel(id,c.getLicencePlate(),c.getMake(),c.getModel(),c.getYear(),c.getEngineCapacity(),c.getColor(),c.getPrice(),c.getDoors(),c.getSize(),c.getPower(),c.isAutomatic(),c.getFuel(),c.getImage());
                }
            }
        }
        return null;


    }

    //This method should delete car with given id.
    @DeleteMapping("/cars/{carId}")
    public void deleteCars(@RequestHeader("authorization") String adminId) {
        for (var user : user.getAllUsers()) {
            for (var car : cars.getAllCars()) {
                if (user.getUserId().equals(adminId) && user.isAdmin()) {
                    cars.deleteCar(car.getCarId());

                }


            }

        }

    }
    //This method should add new car only if user is admin.
    @PostMapping("/cars")
    public CarsModel addNewCar(@RequestHeader("authorization") UUID adminId, CarsRequestModel cR) {
        UUID randomId = UUID.randomUUID();
        for (var car : cars.getAllCars()) {
            for (var us : user.getAllUsers()) {
                if (us.getUserId().equals(adminId) && us.isAdmin()) {
                    return new CarsModel(randomId, car.getLicencePlate(), car.getMake(), car.getModel(), car.getYear(), car.getEngineCapacity(), car.getColor(), car.getPrice(), car.getDoors(), car.getSize(), car.getPower(), car.isAutomatic(), car.getFuel(), car.getImage());
                }
            }
        }
        return null;
    }

    //This method should return all available cars from given dates.
    @GetMapping("/cars/available")
    public List<CarsContractsModel> getAllCarsAvailable(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        return cars.getAvailableCars();

    }

    //This method should return all available cars if conditions are true.
    @GetMapping("cars/available/search")
    public List<CarsContractsModel> getAllCars(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
                                        @RequestParam(defaultValue ="1993") int year,@RequestParam(defaultValue = "Porsche") String make,
                                        @RequestParam(defaultValue ="P1112") String model,@RequestParam(defaultValue = "false") boolean automatic,
                                        @RequestParam(defaultValue ="4500") double price,@RequestParam(defaultValue ="2222")int power,
                                        @RequestParam(defaultValue = "4") int doors) {

    for(var contract : contracts.getAllContracts()){
        if(contract.getStartDate().equals(startDate) && contract.getEndDate().equals(endDate)){
            return cars.getAvailableCars();

        }
    }
    return null;
    }



    //This method should return all dates when the cars are not available.
    @GetMapping("/cars/{carId}/calendar")
    public ResponseDateModel notAvailableDates(@RequestParam("carId") UUID id , ContractsModel cont) {
        List<ResponseDateModel> notAvailableDates = new ArrayList<>();
        for (var contract : contracts.getAllContracts()) {
            for (var car : cars.getAvailableCars()) {
                if (!contract.isApproved() && !contract.isSigned() && !car.getCarId().equals(id) &&
                        !contract.getStartDate().equals(cont.getStartDate()) && !contract.getEndDate().equals(cont.getEndDate())) {
                    return new ResponseDateModel(contract.getStartDate(), contract.getEndDate());


                }
            }

        }
        return null;

    }
}












