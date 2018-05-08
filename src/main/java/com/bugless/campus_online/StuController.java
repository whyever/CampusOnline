package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StuController {

    @Autowired
    private ReserveRepository reserveRepository;

    public StuController(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String stuGET(Model model) {
        List<Reservation> stuReserve = reserveRepository.findDistinctByTag(1);
        System.out.println("GET");
        model.addAttribute("stuReserves",stuReserve);

        return "student";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String stuPOST(Model model) {
        return "student";
    }
}
