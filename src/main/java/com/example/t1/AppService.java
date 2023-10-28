package com.example.t1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AppService {

    private final ContactsStorage contactsStorage;
    private final ContactsPersistService contactsPersistService;

    @Value("${app.profile}")
    private String profile;

    public AppService(ContactsStorage contactsStorage, ContactsPersistService contactsPersistService) {
        this.contactsPersistService = contactsPersistService;
        this.contactsStorage = contactsStorage;
    }

    public void work() {
        try {
            if ("init".equals(profile)) {
                contactsPersistService.loadFromFile();
            }
        } catch (Exception e) {
            System.out.println("Unable to read contacts : " + e.getMessage());
        }

        while (true) {
            shell();
        }
    }

    private void shell() {
        Scanner scanner = new Scanner(System.in);

        String cmd = scanner.next();

        switch (cmd) {
            case "list":
                listContactsCmd();
                break;
            case "add":
                addContactCmd(scanner.next(), scanner.next(), scanner.next());
                break;
            case "remove":
                removeContactCmd(scanner.next());
                break;
            case "save":
                persistCmd();
                break;
        }
    }

    private void listContactsCmd() {
        for (var contact : contactsStorage.getContacts()) {
            String line = String.format("%s | %s | %s", contact.getFullName(), contact.getPhoneNumber(), contact.getEmail());
            System.out.println(line);
        }
    }

    private void addContactCmd(String fullName, String phone, String email) {
        Contact contact = new Contact(fullName, phone, email);
        contactsStorage.add(contact);
    }

    private void removeContactCmd(String email) {
        contactsStorage.removeByEmail(email);
    }

    private void persistCmd() {
        try {
            contactsPersistService.persistToFile();
        } catch (Exception e) {
            System.out.println("Error saving contacts to file + " + e.getMessage());
        }
    }
}
