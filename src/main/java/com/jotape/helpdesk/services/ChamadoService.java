package com.jotape.helpdesk.services;

import com.jotape.helpdesk.domain.Chamado;
import com.jotape.helpdesk.domain.Cliente;
import com.jotape.helpdesk.domain.Tecnico;
import com.jotape.helpdesk.dto.ChamadoDTO;
import com.jotape.helpdesk.enums.Prioridade;
import com.jotape.helpdesk.enums.Status;
import com.jotape.helpdesk.exception.ObjectNotFoundException;
import com.jotape.helpdesk.repositories.ChamadoRepository;
import com.jotape.helpdesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, id: " + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDTO objDTO) {
        return chamadoRepository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getPrioridade()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());

        return chamado;
    }

    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);

        return chamadoRepository.save(oldObj);
    }
}
