package tschool.tarasov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tschool.tarasov.models.Contract;
import tschool.tarasov.models.Option;
import tschool.tarasov.models.Tariff;
import tschool.tarasov.models.users.Customer;
import tschool.tarasov.service.ContractService;
import tschool.tarasov.service.CustomerService;
import tschool.tarasov.service.OptionService;
import tschool.tarasov.service.TariffService;


@Controller
@RequestMapping("/management")
public class ManagementController {

    private final ContractService contractService;
    private final CustomerService customerService;
    private final OptionService optionService;
    private final TariffService tariffService;

    @Autowired
    public ManagementController(ContractService contractService, CustomerService customerService,
                                OptionService optionService, TariffService tariffService) {
        this.contractService = contractService;
        this.customerService = customerService;
        this.optionService = optionService;
        this.tariffService = tariffService;
    }

    @GetMapping("/options")
    public String listOptions(Model model){
        model.addAttribute("option", new Option());
        model.addAttribute("listOptions", optionService.getOptions());

        return "management/options";
    }

    @PostMapping("/options")
    public String createOption(@ModelAttribute("option") Option option,
                               @RequestParam("price") Long price,
                               @RequestParam("conectionCost") Long connectionCost){

        option.setPrice(price);
        option.setConnectionCost(connectionCost);
        optionService.createOption(option);

        return "redirect:/management/options";
    }
    /**
     * Tariff management endpoints
     */

    @GetMapping("/tariffs")
    public String listTariffs(Model model) {
        model.addAttribute("tariff", new Tariff());
        model.addAttribute("listTariffs", tariffService.getTariffs());
        model.addAttribute("listOptions", optionService.getOptions());
        return "management/tariffs";
    }

    @PostMapping("/tariffs")
    public String createTariff(@ModelAttribute("tariff") Tariff tariff, @RequestParam("options") int[] optionsIds) {
        tariffService.createTariffWithOptions(tariff, optionsIds);

        return "redirect:/management/tariffs";
    }

    /**
     * Contracts management endpoints
     */

    @GetMapping("/contracts")
    public String listContracts(Model model){
        model.addAttribute("contract", new Contract());
        model.addAttribute("listContracts", contractService.listContracts());
        model.addAttribute("listTariffs", tariffService.getTariffs());
        model.addAttribute("optionList", optionService.getOptions());

        return "management/contracts";
    }

    @PostMapping("/contracts")
    public String createContract( @ModelAttribute("contract") Contract contract,
                                  @RequestParam("chosenTariff") int chosenTariffId,
                                  @RequestParam("chosenIncludedOptions") int[] chosenOptionsIds) {
        contractService.createContract(contract, chosenTariffId, chosenOptionsIds);

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
    public String createCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("contractNumber") String contractNumber) {
        customerService.createCustomer(customer, contractNumber);

        return "redirect:/management/customers";
    }





}
