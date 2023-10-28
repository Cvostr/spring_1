package com.example.t1;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsStorage {

    private final List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void removeByEmail(String email) {
        contacts.removeIf(contact -> contact.getEmail().equals(email));
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }
}
