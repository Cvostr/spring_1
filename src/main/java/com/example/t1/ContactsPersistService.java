package com.example.t1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class ContactsPersistService {
    private final ContactsStorage contactsStorage;

    @Value("${app.settings.filepath}")
    private String filePath;

    public ContactsPersistService(ContactsStorage contactsStorage) {
        this.contactsStorage = contactsStorage;
    }

    public void persistToFile() throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

        for (var contact : contactsStorage.getContacts()) {
            String line = String.format("%s;%s;%s\n", contact.getFullName(), contact.getPhoneNumber(), contact.getEmail());
            bufferedWriter.write(line);
        }

        bufferedWriter.close();
    }

    public void loadFromFile() throws FileNotFoundException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            var values = line.split(";");
            Contact contact = new Contact(values[0], values[1], values[2]);
            contactsStorage.add(contact);
        }
    }
}
