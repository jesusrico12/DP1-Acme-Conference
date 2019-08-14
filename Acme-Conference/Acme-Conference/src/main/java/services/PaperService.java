package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Paper;

import repositories.PaperRepository;

@Service
@Transactional
public class PaperService {
	
	@Autowired
	private PaperRepository paperRepository;
	
	
	
	public Collection<Paper> paperReadys(){
		Collection<Paper> res= this.paperRepository.paperReadys();
		return res;
	}

}
