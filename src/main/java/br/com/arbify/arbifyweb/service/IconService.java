package br.com.arbify.arbifyweb.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class IconService {
    
    public String getImage(String crytoName){
        ClassPathResource resource = new ClassPathResource("/static/assets/images/currency/"+crytoName.toLowerCase()+".svg");
        return resource.exists() ? "/assets/images/currency/"+crytoName.toLowerCase()+".svg" : "/assets/images/currency/unknown.svg";
    }
}
