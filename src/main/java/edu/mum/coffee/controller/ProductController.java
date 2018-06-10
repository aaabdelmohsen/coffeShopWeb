package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    PersonService personService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderlineService orderlineService;


    List<Product> products;

    @GetMapping(value = {"/*", "/products"})
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("persons", personService.getAllPerson());
        model.addAttribute("orders", orderService.findAll());
        return "products";
    }

    @GetMapping("/createProduct")
    public String createProduct(){
        return "createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute("product")Product product){
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/editProduct/{productId}")
    public String editPerson(@PathVariable("productId") int productId, Model model) {
        model.addAttribute("product", productService.findById(productId));
        return "editProduct";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId) {
        productService.delete(productService.findById(productId));
        return "redirect:/products";
    }

    @PostMapping("/editProduct")
    public String editPerson(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }



    @GetMapping("/placeOrder")
    public String placeOrder(Model model, HttpServletRequest request) {
        products = new ArrayList<>();
        model.addAttribute("products", productService.getAllProduct());
        return "placeOrder";
    }

    @PostMapping("/placeOrder")
    public String createOrder(HttpServletRequest request){

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        Date orderDate = null;
        try {
            orderDate = sdf.parse(request.getParameter("txtDT").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Creating an order first with no orderline
        Order order = new Order();
        order.setPerson(personService.findByEmail(userService.findByUsername(request.getSession().getAttribute("username").toString()).get(0).getPerson().getEmail()).get(0));
        order.setOrderDate(orderDate);

        for(Product product : products){
            Orderline orderline = new Orderline();
            orderline.setOrder(orderService.save(order));
            orderline.setQuantity(1);
            orderline.setProduct(product);
            orderlineService.save(orderline);
        }
        return "redirect:/products";
    }

    @PostMapping(value = "/addProductToCart", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addProductToCart(@RequestBody Product product, HttpServletRequest request){
        try{
            if(request.getSession().getAttribute("productsOrderline") != null){
                products = (List<Product>) request.getSession().getAttribute("productsOrderline");
            }
            products.add(product);
            request.getSession().setAttribute("productsOrderline", products);
        }
        catch(Exception ex){
            System.out.println("Issue");
        }
    }
}
