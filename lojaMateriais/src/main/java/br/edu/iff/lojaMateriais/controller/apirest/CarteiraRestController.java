package br.edu.iff.lojaMateriais.controller.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.lojaMateriais.model.Carteira;
import br.edu.iff.lojaMateriais.service.CarteiraService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/carteiras")
public class CarteiraRestController {

	private final CarteiraService carteiraService;

	public CarteiraRestController(CarteiraService carteiraService) throws Exception{
		this.carteiraService = carteiraService;
	}

	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar carteira.")
	public String adicionarCarteira() throws Exception{

		if (carteiraService.criarCarteira() == null) {
			return "Carteira não foi criado.";
		} else {
			return "Carteira criada com sucesso.";
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar carteira.")
	public String atualizarCarteira(@PathVariable("id") Long idCarteira, Float valor) throws Exception{

		return carteiraService.AtualizarSaldo(idCarteira, valor);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar carteira.")
	public String deletarCarteira(@PathVariable("id") Long idCarteira) throws Exception{

		return carteiraService.deletarCarteira(idCarteira);
	}

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar Carteiras")
	public List<Carteira> listarCarteiras() throws Exception{

		return carteiraService.listarCarteiras();
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Buscar carteira.")
	public Optional<Carteira> buscarCarteira(@PathVariable("id") Long id) throws Exception{

		return carteiraService.obterCarteira(id);
	}

}
