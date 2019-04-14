package ru.coreclass.fronttaskservice;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class StorageService {
    @Autowired
    FileDAO fileDAO;

    private static final Logger logger = LoggerFactory.getLogger(ExampleAppApplication.class);

    @Value("${storage.saveDirectory}")
    private String saveDirectory;

    public List<String> getFiles() {
        return fileDAO.getFiles();

    }


    public void saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {return;}

        String savedFilename = this.saveFileOnDisk(file);
        fileDAO.createFileRecord(file.getOriginalFilename(), savedFilename);

        logger.info("Saved file: " + savedFilename);

    }

    private String saveFileOnDisk(MultipartFile file) throws IOException {
        String cleaned = StringUtils.cleanPath(file.getOriginalFilename());
        String filename = StringUtils.stripFilenameExtension(cleaned);
        String extension = StringUtils.getFilenameExtension(cleaned);
        String generatedFilename = filename + "_"+ RandomStringUtils.randomAlphanumeric(5) + "." + extension;


        Path rootLocation = Paths.get(this.saveDirectory);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, rootLocation.resolve(generatedFilename),
                    StandardCopyOption.REPLACE_EXISTING);
        }

        return generatedFilename;

    }
}
