package services;

import java.util.Collection;
import java.util.Date;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Activity;
import domain.Actor;
import domain.Conference;

import domain.Panel;

import repositories.PanelRepository;

@Service
@Transactional
public class PanelService {
	
	@Autowired
	private PanelRepository panelRepository;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ConferenceService conferenceService;
	
	
	public Panel create(){
		Panel res= new Panel();
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		return res;
		
		
		
	}
	
	public Panel save(Panel panel,Conference conference){
	
			Actor principal=this.actorService.findByPrincipal();
			Assert.isTrue(conference.getAdministrator()==principal,"commit.error");
			 	
			//Panel copy=new Panel();
			Date currentMoment;
			Assert.isTrue(panel.getStartMoment().before(conference.getEndDate())&&panel.getStartMoment().after(conference.getStartDate()),"date.error");
			currentMoment = new Date(System.currentTimeMillis() - 1);
			if(panel.getAttachments().size()>0){
			for(String i : panel.getAttachments() ){
				Assert.isTrue(i.startsWith("https://www.dropbox.com")||i.startsWith("https://www.flickr.com"),"url.error");
			}
			}
			
			Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
					"not.allowed");
			Assert.isTrue(panel.getStartMoment().after(currentMoment),"invalid.date");
			
			if(panel.getId()!=0){

				Assert.isTrue(this.findOne(panel.getId())!=null,"commit.error");
				this.panelRepository.save(panel);
				
			}else{
				

				
			
				panel=this.panelRepository.save(panel);
				Set<Activity> actis= new HashSet<Activity>();
				for(Activity a :conference.getActivities()){
					actis.add(a);
				}
				actis.add(panel);
				conference.setActivities(actis);
				
				conferenceService.saveForce(conference);
			}
			
			
			
			return panel;

		}

		public void delete(Panel panel,Conference conference){
			
			
			Actor principal = this.actorService.findByPrincipal();
			Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
					"not.allowed");
			Assert.isTrue(this.findOne(panel.getId())!=null,"commit.error");
			
			Assert.isTrue(conference.getAdministrator()==principal,"commit.error");

			
	
			Collection<Activity> col= conference.getActivities();
			col.remove(panel);
			conference.setActivities(col);
			this.conferenceService.saveForce(conference);
			this.panelRepository.delete(panel);
			
			
			
		}
	
	
	
	public Panel findOne(int id){
		return this.panelRepository.findOne(id);
	}
	public Collection<Panel> findAll(){
		
		return this.panelRepository.findAll();
	}
	
	
	
	
	

}
