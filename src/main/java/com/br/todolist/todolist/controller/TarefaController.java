package com.br.todolist.todolist.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.br.todolist.todolist.model.Tarefa;
import com.br.todolist.todolist.repository.TarefaRepository;

@Controller
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepo;

	public TarefaController(TarefaRepository tarefaRepo) {
		this.tarefaRepo = tarefaRepo;
	}

	@GetMapping("/tarefas")
	public String tarefas(Model model) {
		model.addAttribute("listaTarefas", tarefaRepo.findAll());
		model.addAttribute("tarefa", new Tarefa());
		return "/tarefas/index";
	}

	@GetMapping("/tarefas/nova")
	public String novaTarefa(@ModelAttribute("tarefa") Tarefa tarefa) {
		return "/tarefas/form";

	}

	@PostMapping("/tarefas/salvar")
	public String salvarTarefa(@ModelAttribute("tarefa") Tarefa tarefa, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "lista/tarefas";
		}

		tarefaRepo.save(tarefa);
		model.addAttribute("listaTarefas", tarefaRepo.findAll());
		return "redirect:/tarefas";
	}

	@GetMapping("/tarefas/{id}")
	public String alterarTarefa(@PathVariable("id") long id, Model model) throws IllegalAccessException {
		Optional<Tarefa> tarefaOpt = tarefaRepo.findById(id);
		if (tarefaOpt.isEmpty()) {
			throw new IllegalAccessException("Tarefa invalida");
		}

		model.addAttribute("tarefa", tarefaOpt.get());
		return "/tarefas/form";
	}

	@GetMapping("tarefas/excluir/{id}")
	public String excluirTarefa(@PathVariable("id") long id) throws IllegalAccessException {
		Optional<Tarefa> tareafaOpt = tarefaRepo.findById(id);
		if (tareafaOpt.isEmpty()) {
			throw new IllegalAccessException("Tarefa invalida");
		}

		tarefaRepo.delete(tareafaOpt.get());
		return "redirect:/tarefas";
	}

}
