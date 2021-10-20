package tschool.tarasov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tschool.tarasov.models.Contract;
import tschool.tarasov.models.users.Customer;
import tschool.tarasov.service.ContractService;
import tschool.tarasov.service.CustomerService;



@Controller
@RequestMapping("/management")
public class ManagementController {

    private final ContractService contractService;
    private final CustomerService customerService;

    @Autowired
    public ManagementController(ContractService contractService, CustomerService customerService) {
        this.contractService = contractService;
        this.customerService = customerService;
    }

    @GetMapping("/contracts")
    public String listContracts(Model model){
        model.addAttribute("contract", new Contract());
        model.addAttribute("listContracts", contractService.listContracts());

        return "management/contracts";
    }

    @PostMapping("/contracts")
    public String createContract(@ModelAttribute("contract") Contract contract) {
        contractService.createContract(contract);

        return "redirect:/management/contracts";
    }

    /**
     * Customers management endpoints
     */

    @GetMapping(value = "customers")
    public String listCustomers(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("listCustomers", this.customerService.listCustomers());

        return "management/customers";
    }

    @PostMapping("/customers")
    public String sendCustomer(@ModelAttribute("customer") Customer customer, @RequestParam("contractNumber") String contractNumber) {
        customerService.createCustomer(customer, contractNumber);

        return "redirect:/management/customers";
    }





}
