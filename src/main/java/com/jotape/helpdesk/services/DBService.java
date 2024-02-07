package com.jotape.helpdesk.services;

import com.jotape.helpdesk.domain.Chamado;
import com.jotape.helpdesk.domain.Cliente;
import com.jotape.helpdesk.domain.Tecnico;
import com.jotape.helpdesk.enums.Perfil;
import com.jotape.helpdesk.enums.Prioridade;
import com.jotape.helpdesk.enums.Status;
import com.jotape.helpdesk.repositories.ChamadoRepository;
import com.jotape.helpdesk.repositories.ClienteRepository;
import com.jotape.helpdesk.repositories.PessoaRepository;
import com.jotape.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Bob", "550.482.150-95", "bob@gmail.com", encoder.encode("123"));
        tec1.addPerfils(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

        Cliente cli1 = new Cliente(null, "Rob", "111.661.890-74", "rob@gmail.com", encoder.encode("123"));
        Cliente cli2 = new Cliente(null, "Albert", "322.429.140-06", "albert@gmail.com", encoder.encode("123"));
        Cliente cli3 = new Cliente(null, "Darwin", "792.043.830-62", "curie@mail.com", encoder.encode("123"));
        Cliente cli4 = new Cliente(null, "Charles Darwin", "177.409.680-30", "darwin@mail.com", encoder.encode("123"));
        Cliente cli5 = new Cliente(null, "Stephen Hawking", "081.399.300-83", "hawking@mail.com", encoder.encode("123"));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

        pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
        clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3, cli4,cli5));

    }
}
