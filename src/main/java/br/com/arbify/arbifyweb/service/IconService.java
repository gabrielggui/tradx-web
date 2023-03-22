package br.com.arbify.arbifyweb.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class IconService {
    
    public String getImage(String crytoName){
        ClassPathResource resource = new ClassPathResource("static/images/currency/"+crytoName.toLowerCase()+".svg");
        return resource.exists() ? "/images/currency/"+crytoName.toLowerCase()+".svg" : "/images/currency/unknown.svg";
    }
}
