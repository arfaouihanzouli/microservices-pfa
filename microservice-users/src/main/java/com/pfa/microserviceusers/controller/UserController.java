package com.pfa.microserviceusers.controller;

import com.pfa.microserviceusers.models.Candidat;
import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.embedded.Address;
import com.pfa.microserviceusers.models.embedded.Photo;
import com.pfa.microserviceusers.models.enumuration.RoleName;
import com.pfa.microserviceusers.models.token.ConfirmationToken;
import com.pfa.microserviceusers.repository.ConfirmationTokenRepository;
import com.pfa.microserviceusers.requests.*;
import com.pfa.microserviceusers.service.EmailSenderService;
import com.pfa.microserviceusers.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    private final Logger log = LoggerFactory.getLogger(User.class);

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public User signUp(@Valid @RequestBody RegistrationForm data) throws IOException
    {
        String username=data.getUsername();
        String email=data.getEmail();
        User user=userService.findByUsernameOrEmail(username,email);
        if(user!=null)
        {
            throw new RuntimeException("This user already exists, Try with another user");
        }
        String password=data.getPassword();
        String repassword=data.getRepassword();
        if(!password.equals(repassword))
        {
            throw new RuntimeException("You must confirm your password");
        }
        User u=new User();
        u.setPassword(password);
        u.setUsername(username);
        u.setEmail(email);
        u.setName(data.getName());
        u.setLastName(data.getLastName());
        u.setTelephone(data.getTelephone());
        u.setRole(RoleName.USER);
        if(data.getAddress()!=null && data.getNiveau()!=null && data.getCity()!=null && data.getCountry()!=null)
        {
            Candidat candidat=new Candidat();
            Address address=new Address(data.getAddress(),data.getCity(),data.getCountry(),data.getPostcode());
            candidat.setAddress(address);
            candidat.setDate_naissance(data.getDate_naissance());
            candidat.setDiplome(data.getDiplome());
            candidat.setInstitut(data.getInstitut());
            candidat.setNiveau(data.getNiveau());
            candidat.setPassword(password);
            candidat.setUsername(username);
            candidat.setEmail(email);
            candidat.setName(data.getName());
            candidat.setLastName(data.getLastName());
            candidat.setTelephone(data.getTelephone());
            candidat.setRole(RoleName.CANDIDAT);
            userService.saveUser(candidat);
            emailSenderService.sendEmail(candidat);
            return candidat;
        }
        userService.saveUser(u);
        emailSenderService.sendEmail(u);
        //log.info(u.getEmail());
        return u;
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        log.info(token.getConfirmationToken());
        if(token != null)
        {
            User user = userService.findByUsernameOrEmail(token.getUser().getEmail(),token.getUser().getEmail());
            log.info(user.getEmail());
            user.setEnabled(true);
            userService.updateUser(user);
            return "Congratulations! Your account has been activated and email is verified!";
        }
        return "The link is invalid or broken!";
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/addPhoto/{username}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User updatePhoto(@RequestParam("file") MultipartFile file,@PathVariable String username) throws IOException
    {
        User user=userService.findByUsername(username);
        //get details of photos and encoding it
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String type=file.getContentType();
        byte[] fileContent =file.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        Photo photo=new Photo(fileName,type,encodedString);

        userService.addPhotoToUser(user,photo);
        return user;
    }
    @GetMapping("/findByUsername/{username}")
    public User findByUsername(@Valid @PathVariable String username)
    {
        User user=userService.findByUsername(username);
        if(user==null)
        {
            throw new RuntimeException("This user not exists, Try with another user");
        }
        return user;
    }

    @GetMapping("/getEncoded/{id}")
    public String encodedImageOfUser(@PathVariable Long id)
    {
        String encodedStringOfImage=userService.encodedStringOfImage(id);
        if(encodedStringOfImage==null)
        {
            throw new RuntimeException("This user not exists, Try with another user");
        }
        return encodedStringOfImage;
    }
    @GetMapping("/findByUsernameOrEmail/{usernameOrEmail}")
    public User findByUsernameOrEmail(@Valid @PathVariable String usernameOrEmail)
    {
        User user=userService.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        if(user==null)
        {
            throw new RuntimeException("This user not exists, Try with another user");
        }
        return user;
    }
    @GetMapping("/findById/{id}")
    public User findById(@Valid @PathVariable Long id)
    {
        Optional<User> user=userService.findById(id);
        if(user==null)
        {
            throw new RuntimeException("This user not exists, Try with another user");
        }
        return user.get();
    }
}
