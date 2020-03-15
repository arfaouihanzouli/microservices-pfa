package com.pfa.microserviceusers.controller;

import com.pfa.microserviceusers.exceptions.ResourceNotFoundException;
import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.token.PasswordRestToken;
import com.pfa.microserviceusers.repository.ResetPasswordRepository;
import com.pfa.microserviceusers.requests.ResetPassword;
import com.pfa.microserviceusers.requests.ApiResponse;
import com.pfa.microserviceusers.requests.ResetPasswordRequest;
import com.pfa.microserviceusers.service.EmailSenderService;
import com.pfa.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/reset-password")
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/reset")
    public void resetPassword(@RequestBody ResetPassword email)
    {
        User user=userService.findByEmail(email.getEmail());
        if(user==null)
        {
            throw new RuntimeException("This user not exists");
        }
        emailSenderService.sendResetPasswordEmail(user);
    }
    @RequestMapping(value="/change", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Object> changePassword(@RequestParam("token")String resetToken)
    {
        PasswordRestToken token = resetPasswordRepository.findByResetToken(resetToken);
        //System.out.println(token.getExpirationDate().after(new Date()));
        if(token != null && (token.getExpirationDate().after(new Date())))
        {
            return new ResponseEntity<>(new ApiResponse("Your token is valid, you can change your password",true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("The link is invalid or broken!",false), HttpStatus.LOCKED);
    }

    @RequestMapping(value="/update", method= { RequestMethod.POST})
    public ResponseEntity<Object> updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest,@RequestParam("token")String resetToken)
    {
        PasswordRestToken token = resetPasswordRepository.findByResetToken(resetToken);
        if(token != null && (token.getExpirationDate().after(new Date())))
        {
            User user=userService.findByEmail(resetPasswordRequest.getEmail());
            if(user==null)
            {
                throw new ResourceNotFoundException("This user not exists");
            }
            String password=resetPasswordRequest.getNewPassword();
            String repassword=resetPasswordRequest.getConfirmNewPassword();
            if(!password.equals(repassword))
            {
                throw new RuntimeException("You must confirm your password!");
            }
            user.setPassword(password);
            userService.saveUser(user);
            return new ResponseEntity<>(new ApiResponse("Your password has been successfully changed",true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("The link is invalid or broken!",false), HttpStatus.LOCKED);
    }
}
