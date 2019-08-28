package services;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;


import domain.Actor;

import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class SectionService {
	
	@Autowired
	private SectionRepository  sectionRepository;
	
	@Autowired
	private ActorService actorService;

	
	@Autowired
	private TutorialService tutorialService;
	
	@Autowired
	private ConferenceService conferenceService;
	
	
public Section create(){
		
		Section res= new Section();
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		return res;
		
		
	}
	
	public Section save(Section section,Tutorial tutorial){
		
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(this.conferenceService.ConferenceOwn(tutorial.getId()).getAdministrator()==principal,"commit.error");
		Section copy= new Section();
		
		
		if(section.getPictures().size()>0){
		for(String i : section.getPictures() ){
			Assert.isTrue(i.startsWith("https://www.dropbox.com")||i.startsWith("https://www.flickr.com"),"url.error");
		}
		}
		
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		
		
		if(section.getId()!=0){
			copy=this.findOne(section.getId());
			copy.setPictures(section.getPictures());
			copy.setSummary(section.getSummary());
			copy.setTitle(section.getTitle());
	
			Assert.isTrue(this.findOne(section.getId())!=null,"commit.error");
			this.sectionRepository.save(copy);
			
		}else{
			
			
			copy.setPictures(section.getPictures());
			copy.setSummary(section.getSummary());
			copy.setTitle(section.getTitle());
		
			
			tutorial.getSections().add(copy);
			
			tutorialService.saveForce(tutorial);
		}
		
		
		return copy;
	}
	
	public void delete(Section section,Tutorial tutorial){
		
		
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		Assert.isTrue(this.findOne(section.getId())!=null,"commit.error");
		
		Assert.isTrue(this.conferenceService.ConferenceOwn(tutorial.getId()).getAdministrator()==principal,"commit.error");

		
	
		Collection<Section> col= tutorial.getSections();
		col.remove(section);
		tutorial.setSections(col);
		this.tutorialService.saveForce(tutorial);
		this.sectionRepository.delete(section);
		
		
		
	}
	public Section findOne(int id){
		return this.sectionRepository.findOne(id);
	}
	public Collection<Section> findAll(){
		
		return this.sectionRepository.findAll();
	}
	
}
