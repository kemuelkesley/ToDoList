package com.br.todolist.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.br.todolist.todolist.model.Tarefa;
import com.br.todolist.todolist.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner{
	
	@Autowired
	private TarefaRepository tarefaRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Tarefa tarefa1 = new Tarefa("Estudar SpringBoot");
		Tarefa tarefa2 = new Tarefa("Zerar Resident Evil 4 remake");
		Tarefa tarefa3 = new Tarefa("Fazer Treinamento de musculação");
		Tarefa tarefa4 = new Tarefa("Estudar Inglês");
		/*
		 * tarefa1.setDescricao("vamos zerar"); tarefa2.setDescricao("estudar java");
		 */
		
		tarefaRepo.save(tarefa1);
		tarefaRepo.save(tarefa2);
		tarefaRepo.save(tarefa3);
		tarefaRepo.save(tarefa4);
		
		tarefaRepo.flush();
	}
	
	
	
}
