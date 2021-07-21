package com.pranoy.spring.security.controllers;

import com.pranoy.spring.security.model.Coupon;
import com.pranoy.spring.security.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CouponController {
    @Autowired
    private CouponRepository couponRepository;

    @GetMapping("/showCreateCoupon")
    public String showCreateCoupon() {
        return "createCoupon";
    }

    @PostMapping("/saveCoupon")
    public String save(Coupon coupon, Model model) {
        Coupon newCoupon = couponRepository.save(coupon);
        return "createResponse";
    }

    @GetMapping("/showGetCoupon")
    public String showGetCoupon() {
        return "getCoupon";
    }

    @PostMapping("/getCoupon")
    public ModelAndView getCoupon(String code) {
        ModelAndView modelAndView = new ModelAndView("couponDetails");
        modelAndView.addObject(couponRepository.findByCode(code));
        return modelAndView;
    }
}
