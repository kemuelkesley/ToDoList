package com.br.todolist.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.todolist.todolist.model.Tarefa;
import com.br.todolist.todolist.repository.TarefaRepository;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;

@Controller
public class TarefaController {

	private TarefaRepository tarefaRepo;

	public TarefaController(TarefaRepository tarefaRepo) {
		this.tarefaRepo = tarefaRepo;
	}

	@GetMapping("/lista/tarefas")
	public String tarefas(Model model) {
		model.addAttribute("listaTarefas", tarefaRepo.findAll());
		System.out.println("ola mundooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
		return "/lista/tarefas/index";
	}

	@GetMapping("lista/tarefas/nova")
	public String novaTarefa(@ModelAttribute("tarefa") Tarefa tarefa) {
		return "lista/tarefas/form";
		
	}
	
	@PostMapping("/lista/tarefas/salvar")
	public String salvarTarefa(@ModelAttribute("tarefa") Tarefa tarefa, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "rh/pessoas/form";
		}
		
		tarefaRepo.save(tarefa);
		return "redirect:/lista/tarefas";
		
	}

}
