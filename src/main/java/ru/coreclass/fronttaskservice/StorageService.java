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

    @Value("${storage.tempDirectory}")
    private String tempDirectory;

    public List<String> getFiles() {
        return fileDAO.getFiles();

    }


    public void saveFile(MultipartFile file, Integer contentId) throws IOException {
        if (file.isEmpty()) {return;}

        String savedFilename = this.saveFileOnDisk(file);
        fileDAO.createFileRecord(file.getOriginalFilename(), savedFilename, contentId);

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

    private void saveFileToTemp(MultipartFile file) throws IOException {
        Path rootLocation = Paths.get(this.tempDirectory);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, rootLocation.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private Integer processContent(MultipartFile file) throws IOException {
        String content = new String(file.getBytes());

        return fileDAO.saveContent(content);

    }

    public void processFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {return;}

        this.saveFileToTemp(file);

        Integer contentId = this.processContent(file);

        this.saveFile(file, contentId);

        this.deleteFromTemp(file.getOriginalFilename());

    }

    private void deleteFromTemp(String filename) throws IOException {
        Path tempFolder = Paths.get(this.tempDirectory);

        Files.delete(tempFolder.resolve(filename));

    }
}
