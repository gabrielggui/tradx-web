package br.com.arbify.arbifyweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.arbify.arbifyweb.dto.UserDTO;
import br.com.arbify.arbifyweb.service.AuthenticationService;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("public/login");
    }

    @GetMapping("/cadastro")
    public ModelAndView telaCadastroDeUsuario() {
        return new ModelAndView("public/cadastro");
    }

    @PostMapping("/cadastro/salvar")
    public RedirectView salvarNovoUsuario(UserDTO userDTO) {
        try {
            authenticationService.salvarUsuario(userDTO);
        } catch (Exception e) {
            return new RedirectView("/cadastro?erro");
        }
        return new RedirectView("/cadastro?sucesso");
    }
}
