package tschool.tarasov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tschool.tarasov.models.Contract;
import tschool.tarasov.models.users.Customer;
import tschool.tarasov.service.ContractService;
import tschool.tarasov.service.CustomerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.*;

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

/*
    @GetMapping("/new")
    public String newContract(Model model) {
        model.addAttribute("contract", new Contract());
        return "contracts/new";
    }
*/
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
        model.addAttribute("contract", new Contract());
        model.addAttribute("contractNumber", new String());
        model.addAttribute("listCustomers", this.customerService.listCustomers());

        return "management/customers";
    }

    @PostMapping("/customers")
    public String createCustomer(@ModelAttribute("customer") Customer customer,
                                 @ModelAttribute("contractNumber") String number
                                 ) {

//        Contract contract = contractService.getContractByNumber(number);
//        customer.setContractList(new ArrayList<Contract>(Arrays.asList(contract)));

        //List<Contract> contractList = (ArrayList<Contract>) contractService.listContracts();
        //for (Contract contract : contractList){
        //    if (number == contract.getNumber()){
        //        customer.setContractList(new ArrayList<Contract>(Arrays.asList(contract)));
        //    }
        //}

        customerService.createCustomer(customer, number);

        return "redirect:/management/customers";
    }

}
