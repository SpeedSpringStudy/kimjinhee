package backend.speedspringstudy.product.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(value = "/products", produces = MediaType.TEXT_HTML_VALUE)
    public String productsPage() {
        return "products";
    }

    @GetMapping(value = "/products/create", produces = MediaType.TEXT_HTML_VALUE)
    public String productCreatePage() {
        return "product_form";
    }

    @GetMapping(value = "/products/edit", produces = MediaType.TEXT_HTML_VALUE)
    public String productEditPage() {
        return "product_edit_form";
    }
}