package com.example.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hokyeong on 2017. 2. 21..
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
