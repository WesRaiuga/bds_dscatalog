package br.dev.wesraiuga.dscatalog.services;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.wesraiuga.dscatalog.dto.CategoryDTO;
import br.dev.wesraiuga.dscatalog.entities.Category;
import br.dev.wesraiuga.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		return list.stream().map(c -> new CategoryDTO(c)).collect(toList());
	}

}
