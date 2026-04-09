package br.com.carstore.controller;

import br.com.carstore.dto.CarDTO;
import br.com.carstore.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String exibirFormulario(Model model) {
        model.addAttribute("carDTO", new CarDTO());
        return "index"; // Retorna o nome do template
    }

    @PostMapping("/carros")
    public String salvarCarro(@Valid CarDTO carDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "index"; // Em caso de erro, retorna ao formulário com as mensagens
        }

        // Salvar o carro usando o serviço
        service.save(carDTO);

        return "redirect:/sucesso";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }

}