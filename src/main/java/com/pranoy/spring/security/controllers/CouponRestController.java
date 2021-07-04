package com.pranoy.spring.security.controllers;

import com.pranoy.spring.security.model.Coupon;
import com.pranoy.spring.security.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {
    @Autowired
    CouponRepository couponRepository;

    @PostMapping("/coupons")
    public Coupon create(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @GetMapping("/coupons/{code}")
    public Coupon getCoupon(@PathVariable String code) {
        return couponRepository.findByCode(code);
    }
}
