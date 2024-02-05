package com.jotape.helpdesk.services;

import com.jotape.helpdesk.domain.Chamado;
import com.jotape.helpdesk.domain.Cliente;
import com.jotape.helpdesk.domain.Tecnico;
import com.jotape.helpdesk.enums.Perfil;
import com.jotape.helpdesk.enums.Prioridade;
import com.jotape.helpdesk.enums.Status;
import com.jotape.helpdesk.repositories.ChamadoRepository;
import com.jotape.helpdesk.repositories.ClienteRepository;
import com.jotape.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Bob", "89791213003", "bob@gmail.com", "123456");
        tec1.addPerfils(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", "123");
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", "123");
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", "123");
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", "123");

        Cliente cli1 = new Cliente(null, "Rob", "34739041049", "rob@gmail.com", "123456");
        Cliente cli2 = new Cliente(null, "Rob", "34739041049", "rob@gmail.com", "123456");
        Cliente cli3 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", "12345");
        Cliente cli4 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", "12345");
        Cliente cli5 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", "12345");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
        clienteRepository.saveAll(List.of(cli1));
        chamadoRepository.saveAll(List.of(c1));

    }
}
