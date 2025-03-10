package com.edison.springsecsection1.controller;

import com.edison.springsecsection1.model.Contact;
import com.edison.springsecsection1.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    @GetMapping("/contact")
    public Contact saveContactEnquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);


    }
    public String getServiceReqNumber() {
        Random random=new Random();
        int ranNum=random.nextInt(999999999 - 9999)+9999;
        return "SR"+ranNum;
    }
}
