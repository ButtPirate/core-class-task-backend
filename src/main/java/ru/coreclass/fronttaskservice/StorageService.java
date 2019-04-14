package ru.coreclass.fronttaskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {
    @Autowired
    FileDAO fileDAO;

    public List<String> getFiles() {
        return fileDAO.getFiles();


    }



}
