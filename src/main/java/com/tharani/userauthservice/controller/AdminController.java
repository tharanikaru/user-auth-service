package com.tharani.userauthservice.controller;

import com.tharani.userauthservice.dto.AdminCreateRequest;
import com.tharani.userauthservice.dto.AdminCreateResponse;
import com.tharani.userauthservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/create")
    public AdminCreateResponse adminCreate(@RequestBody AdminCreateRequest request) {
        return adminService.adminCreate(request);
    }
}
