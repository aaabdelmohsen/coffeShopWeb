package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.domain.UserRole;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
import edu.mum.coffee.service.UserRoleService;
import edu.mum.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class PersonController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PersonService personService;

    @Autowired
    ProductService productService;

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") User user, BindingResult result) {
        user.setEnabled(1);
        User userRes = userService.saveUser(user);
        UserRole userRole = new UserRole();
        userRole.setRole("USER");
        userRole.setUser(userRes);
        userRoleService.saveUser(userRole);
        return "signup";
    }

    @GetMapping("/displayLogin")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Principal principal) {
        request.getSession().setAttribute("userLoggedInCheck", true);
        request.getSession().setAttribute("username", principal.getName());
        request.getSession().setAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().toString());
        return "redirect:/products";
    }

    @GetMapping("/createPerson")
    public String createPerson(Model model) {
        return "createPerson";
    }

    @PostMapping("/createPerson")
    public String createPerson(@ModelAttribute("person") Person person) {
        personService.savePerson(person);
        return "redirect:/persons";
    }

    @PostMapping("/userProfile")
    public String userProfile(@ModelAttribute("person") Person person){
        personService.savePerson(person);
        return "redirect:/userProfile";
    }

    @GetMapping("/userProfile")
    public String userProfile(HttpServletRequest request, Model model) {
        model.addAttribute("person", personService.findByEmail(userService.findByUsername(request.getSession().getAttribute("username").toString()).get(0).getPerson().getEmail()).get(0));
        return "userProfile";
    }


}
