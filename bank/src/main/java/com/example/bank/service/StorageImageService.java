/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.service;


import java.io.InputStream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ketul
 */
public interface StorageImageService {
    public void store(MultipartFile file, String fileName) ;
    public void init();
    public void replaceImage(String originalFilename, String fileName) ;
    public Resource loadFile(String filename);   
    public InputStream resizeImage(InputStream inputStream, int width, int height);

}
