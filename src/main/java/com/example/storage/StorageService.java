package com.example.storage;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by hokyeong on 2017. 2. 21..
 */
public interface StorageService {

    void init();

    void store(MultipartFile file);

    //Stream<Path> loadAll();

    Path load(String filename);

    //Resource loadAsResource(String filename);

    void deleteAll();
}
