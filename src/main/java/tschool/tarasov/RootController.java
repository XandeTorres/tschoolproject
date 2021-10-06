package tschool.tarasov;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greetingp")
    public String greetingp(HttpServletRequest request) {
        String name = request.getParameter("n");
        String surname = request.getParameter("s");
        System.out.println(name + " " + surname);

        return "greeting";
    }
}
