package com.haylen.pan.controller;

import com.haylen.pan.dto.CommonResult;
import com.haylen.pan.dto.OwnerInfo;
import com.haylen.pan.dto.RegisterParam;
import com.haylen.pan.dto.PasswordParam;
import com.haylen.pan.entity.Owner;
import com.haylen.pan.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author haylen
 * @date 2019-12-26
 */
@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@Valid @RequestBody RegisterParam registerParam) {
        if (ownerService.register(registerParam) == null) {
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestParam @NotEmpty String username,
                              @RequestParam @NotEmpty String password) {
        String token = ownerService.login(username, password);
        if (token == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(token);
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public CommonResult changePassword(@Valid @RequestBody PasswordParam passwordParam) {
        if (ownerService.updatePassword(passwordParam) <= 0) {
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    @RequestMapping("/isRegistered")
    public CommonResult isRegistered(@RequestParam @NotEmpty String username) {
        return CommonResult.success(ownerService.isRegistered(username));
    }

    @RequestMapping("/info")
    public CommonResult getInfo() {
        Owner owner = ownerService.getCurrentOwner();
        OwnerInfo ownerInfo = new OwnerInfo();
        ownerInfo.setName(owner.getUsername());
        ownerInfo.setAvatar(owner.getAvatar());
        return CommonResult.success(ownerInfo);
    }

    @RequestMapping(value = "/avatar/upload", method = RequestMethod.POST)
    public CommonResult uploadAvatar(MultipartFile file) {
        if (ownerService.uploadAvatar(file) <= 0) {
            return CommonResult.failed();
        }
        return CommonResult.success();
    }
}
