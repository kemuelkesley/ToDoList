package com.br.todolist.todolist.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.br.todolist.todolist.model.Tarefa;
import com.br.todolist.todolist.repository.TarefaRepository;

@Controller
public class TarefaController {

	private TarefaRepository tarefaRepo;

	public TarefaController(TarefaRepository tarefaRepo) {
		this.tarefaRepo = tarefaRepo;
	}

	@GetMapping("/lista/tarefas")
	public String tarefas(Model model) {
		model.addAttribute("listaTarefas", tarefaRepo.findAll());
		model.addAttribute("tarefa", new Tarefa());
		return "/lista/tarefas/index";
	}

	@GetMapping("lista/tarefas/nova")
	public String novaTarefa(@ModelAttribute("tarefa") Tarefa tarefa) {
		return "lista/tarefas/form";

	}

	@PostMapping("/lista/tarefas/salvar")
	public String salvarTarefa(@ModelAttribute("tarefa") Tarefa tarefa, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "lista/tarefas";
		}

		tarefaRepo.save(tarefa);
		model.addAttribute("listaTarefas", tarefaRepo.findAll());
		return "redirect:/lista/tarefas";
	}

	@GetMapping("/lista/tarefas/{id}")
	public String alterarTarefa(@PathVariable("id") long id, Model model) throws IllegalAccessException {
		Optional<Tarefa> tarefaOpt = tarefaRepo.findById(id);
		if (tarefaOpt.isEmpty()) {
			throw new IllegalAccessException("Tarefa invalida");
		}

		model.addAttribute("tarefa", tarefaOpt.get());
		return "/lista/tarefas/form";
	}

	@GetMapping("/lista/tarefas/excluir/{id}")
	public String excluirTarefa(@PathVariable("id") long id) throws IllegalAccessException {
		Optional<Tarefa> tareafaOpt = tarefaRepo.findById(id);
		if (tareafaOpt.isEmpty()) {
			throw new IllegalAccessException("Tarefa invalida");
		}

		tarefaRepo.delete(tareafaOpt.get());
		return "redirect:/lista/tarefas";
	}

}
