package com.jotape.helpdesk.services;

import com.jotape.helpdesk.domain.Pessoa;
import com.jotape.helpdesk.domain.Cliente;
import com.jotape.helpdesk.dto.ClienteDTO;
import com.jotape.helpdesk.exception.DataIntegrityViolationException;
import com.jotape.helpdesk.exception.ObjectNotFoundException;
import com.jotape.helpdesk.repositories.PessoaRepository;
import com.jotape.helpdesk.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;


	public Cliente findById(Integer id) {

		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	public Cliente update(Integer id, ClienteDTO objDTO) {

		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {

		Cliente obj = findById(id);

		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O cliente possui serviços em aberto e não pode ser apagado");
		}
		repository.deleteById(id);
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-Mail já cadastrado no sistema");
		}
	}

}
