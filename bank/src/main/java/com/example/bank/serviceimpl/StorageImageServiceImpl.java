/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.serviceimpl;


import com.example.bank.Exceptions.FileNotStoredException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import org.springframework.core.io.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.bank.service.StorageImageService;


/**
 *
 * @author ketul
 */
@Service
public class StorageImageServiceImpl implements StorageImageService{
    private final Path rootLocation;
    
    @Autowired
    public StorageImageServiceImpl(@Value("${image.rootlocation}") String rootLocation) {
        this.rootLocation = Paths.get(rootLocation);
    }

    @Override
    public void store(MultipartFile file, String fileName) {
        try {  
            Files.deleteIfExists(this.rootLocation.resolve(fileName)); 
            Files.copy(this.resizeImage(file.getInputStream(), 250, 200), this.rootLocation.resolve(fileName));
        } catch (Exception e) {
//            throw new FileNotStoredException("FAIL TO STORE FILE!");
        }
    }
    
    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    @Override
    public void replaceImage(String originalFilename, String fileName) {
      Path source=this.rootLocation.resolve(originalFilename);
        try {
            Files.deleteIfExists(source.resolveSibling(fileName));
            Files.move(source, source.resolveSibling(fileName));
        } catch (IOException ex) {
//            Logger.getLogger(CmsStorageImageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            
            Path defaultFile = rootLocation.resolve("default.jpg");
            Resource defaultResource = new UrlResource(defaultFile.toUri());
  
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
               return defaultResource;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL TO LOAD FILE!");
        }
    }

    @Override
    public InputStream resizeImage(InputStream inputStream, int width, int height){
        BufferedImage sourceImage=null;
        try {
            sourceImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(StorageImageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image thumbnail = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
                thumbnail.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedThumbnail, "jpeg", baos);
        } catch (IOException ex) {
            Logger.getLogger(StorageImageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

}
