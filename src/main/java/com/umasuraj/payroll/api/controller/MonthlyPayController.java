package com.umasuraj.payroll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umasuraj.payroll.api.payload.MonthlyPayDto;
import com.umasuraj.payroll.api.response.ApiResponse;
import com.umasuraj.payroll.api.service.MonthlyPayService;

/**
 *
 * @author umasuraj
 */
@RestController
@RequestMapping("/payroll/api/v1")
public class MonthlyPayController {

    @Autowired
    private MonthlyPayService monthlyPayService;

    // POST save monthlyPay
    @PostMapping("/salary/{salaryId}/monthly/add")
    public ResponseEntity<MonthlyPayDto> saveMonthlyPay(@RequestBody MonthlyPayDto monthlyPayDto, @PathVariable("salaryId") Long salaryId) {

        MonthlyPayDto savedMonthlyPayDto = this.monthlyPayService.saveMonthlyPay(monthlyPayDto, salaryId);

        return new ResponseEntity<>(savedMonthlyPayDto, HttpStatus.CREATED);
    }

    // PUT update monthlyPay
    @PutMapping("/monthly/update/{monthlyPayId}")
    public ResponseEntity<MonthlyPayDto> updateMonthlyPay(@RequestBody MonthlyPayDto monthlyPayDto, @PathVariable("monthlyPayId") Long monthlyPayId) {

        MonthlyPayDto updatedMonthlyPayDTO = this.monthlyPayService.updateMonthlyPay(monthlyPayDto, monthlyPayId);

        return new ResponseEntity(updatedMonthlyPayDTO, HttpStatus.ACCEPTED);
    }

    // DELETE delete monthlyPay
    @DeleteMapping("/monthly/delete/{monthlyPayId}")
    public ResponseEntity<ApiResponse> deleteMonthlyPay(@PathVariable("monthlyPayId") Long monthlyPayId) {

        this.monthlyPayService.deleteMonthlyPayById(monthlyPayId);

        ApiResponse apiResponse = new ApiResponse("SUCCESS", "EMPLOYEE_DELETED");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    // GET  monthlyPay
    @GetMapping("/monthly/get/{monthlyPayId}")
    public ResponseEntity<MonthlyPayDto> getMonthlyPay(@PathVariable("monthlyPayId") Long monthlyPayId) {

        MonthlyPayDto foundMonthlyPayDto = this.monthlyPayService.getMonthlyPayById(monthlyPayId);

        return new ResponseEntity<>(foundMonthlyPayDto, HttpStatus.FOUND);
    }

    // GET find all monthlyPay list
    @GetMapping("/monthly/get/all")
    public ResponseEntity<List<MonthlyPayDto>> getAllMonthlyPay() {

        List<MonthlyPayDto> foundMonthlyPayDtoList = this.monthlyPayService.getAllMonthlyPay();

        return new ResponseEntity<>(foundMonthlyPayDtoList, HttpStatus.FOUND);
    }

    // GET  all monthlyPay BY employee Id
    @GetMapping("/employee/{employeeId}/monthly/get")
    public ResponseEntity<List<MonthlyPayDto>> getAllMonthlyPayByEmployee(@PathVariable("employeeId") Long employeeId) {

        List<MonthlyPayDto> foundMonthlyPayDtoList = this.monthlyPayService.getAllMonthlyPayByEmployee(employeeId);

        return new ResponseEntity<>(foundMonthlyPayDtoList, HttpStatus.FOUND);
    }
    
    // GET  all monthlyPay BY employee Id
    @GetMapping("/employee/{employeeId}/monthly/pdf/get/{monthlyPayId}")
    public ResponseEntity<ApiResponse> getSalarySlip(@PathVariable("employeeId") Long employeeId, @PathVariable("monthlyPayId") Long monthlyPayId) {

        this.monthlyPayService.getSalarySlip(employeeId, monthlyPayId);
        
        ApiResponse apiResponse = new ApiResponse("SUCCESS", "SALARY_SLIP_CREATED");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

}
// end class MonthlyPayController{}
