package com.engage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app/expenses")
public class ExpensesController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String expenses() {
        return "{}";
    }

}
