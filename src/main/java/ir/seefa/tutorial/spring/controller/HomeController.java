package ir.seefa.tutorial.spring.controller;

import ir.seefa.tutorial.spring.entity.ContactEntity;
import ir.seefa.tutorial.spring.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 10 Sep 2020 T 13:24:21
 */
// 1. Read spring-core-tutorial and spring-jdbc-mvc-tutorial codes before starting this project because primary annotations and mvc logic explained there
// 2. new way to introduce a Bean to Spring with @Controller annotation specific for implement biz logic
@Controller
public class HomeController {
    // 3. wiring Controller bean to Service bean layer
    @Autowired
    ContactService contactService;

    // 4. Spring solution to handle some specific UI data models to understandable Java object(convert String date format to java.util.Date object). useful for marshalling purpose
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    // 5. handle root path of application to show list of contact in home page view
    @RequestMapping(value = "/")
    public ModelAndView getContacts(ModelAndView model) throws IOException {
        List<ContactEntity> contacts = contactService.getAll();
        model.addObject("contacts", contacts);
        model.setViewName("home");
        return model;
    }

    // 6. handle /newContact path to route to contactForm page for adding new contact info and submit changes with GET method
    @RequestMapping(value = "/newContact", method = RequestMethod.GET)
    public ModelAndView addNewContact(ModelAndView model) {
        ContactEntity newContact = new ContactEntity();
        model.addObject("contact", newContact);
        model.setViewName("contactForm");
        return model;
    }

    // 7. save new contact information with POST request
    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute ContactEntity contact) {
        contactService.addOrUpdateContact(contact);
        return new ModelAndView("redirect:/");
    }

    // 8. Delete a contact record with GET request and send contact_id with URL query param
    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        contactService.delete(contactId);
        return new ModelAndView("redirect:/");
    }

    // 8. Edit a contact record with GET request and send contact_id with URL query param for redirect to Edit page
    @RequestMapping(value = "/editContact", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        Optional<ContactEntity> contact = contactService.get(contactId);
        ModelAndView model = new ModelAndView("ContactForm");
        contact.ifPresent(contactEntity -> model.addObject("contact", contactEntity));

        return model;
    }
}
