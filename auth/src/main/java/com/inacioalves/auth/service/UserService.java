package com.inacioalves.auth.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inacioalves.auth.exception.objectNotFoundException;
import com.inacioalves.auth.model.User;
import com.inacioalves.auth.repository.UserRepository;


@Service
public class UserService implements Serializable {

	private static final long serialVersionUID = -1339402540922449458L;
	
	private final UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User create(User obj) {
		obj.setSenha(encoder.encode(obj.getSenha()));
		return userRepository.save(obj);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.orElseThrow(()-> new objectNotFoundException("Usuário não encontrado com Id:"+id));
	}
	
	public User Update(User obj) {
		final Optional<User> optUser = userRepository.findById(obj.getId());
		
		if(!optUser.isPresent()) {
			new objectNotFoundException("Usuário não encontrado");
		}
		
		return userRepository.save(obj);
	}
	
	public void Deletar(Long id) {
		var entity = userRepository.findById(id)
				.orElseThrow(()-> new objectNotFoundException("Usuário não encontrado com Id:"+id));
			
		userRepository.delete(entity);
	}
	

}
