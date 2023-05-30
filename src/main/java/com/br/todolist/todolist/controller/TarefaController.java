package com.br.todolist.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
	
	
	
	
	
}
