package com.campbackend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campbackend.repository.AccountHolderRepository;
@RestController
public class IndexController {
  @Autowired AccountHolderRepository aServices;
    @RequestMapping(value="/login")
    public void  login()
    {
      // aServices.save(new AccountHolder(null, "admin", "Male", "0783402443", "admin@gmail.com", "profile", BCrypt.hashpw("admin", BCrypt.gensalt()), Role.UNION, null));
     }
}
