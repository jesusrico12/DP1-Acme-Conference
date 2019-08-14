package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
	
		
			
			Panel result;
			Date currentMoment;
			Assert.isTrue(panel.getStartMoment().before(conference.getEndDate())&&panel.getStartMoment().after(conference.getStartDate()),"date.error");
			currentMoment = new Date(System.currentTimeMillis() - 1);
			if(panel.getAttachments().size()>0){
			for(String i : panel.getAttachments() ){
				Assert.isTrue(i.startsWith("https://www.dropbox.com")||i.startsWith("https://www.flickr.com"),"url.error");
			}
			}
			Actor principal = this.actorService.findByPrincipal();
			Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
					"not.allowed");
			Assert.isTrue(panel.getStartMoment().after(currentMoment),"invalid.date");
			result=this.panelRepository.save(panel);
			if(panel.getId()==0){
				
				conference.getActivities().add(panel);
				
				conferenceService.saveForce(conference);
				
			}
			
			
			
			return result;
		/*	if(panel.getId()==0){
				
				
				Assert.isTrue(panel.getStartMoment().after(currentMoment),"invalid.date");
		
				res=this.panelRepository.save(panel);
				
			//	conference=this.conferenceService.findOne(conference.getId());
				
				Collection<Activity> actis=conference.getActivities();
				actis.add(panel);
				conference.setActivities(actis);
				this.conferenceService.saveForce(conference);
			}else {
			//	Assert.isTrue(conference.getAdministrator().getId() == principal.getId(), "no.permission");
				//COmprobar que le pertenece a esa conferencia
				
				Actor principal = this.actorService.findByPrincipal();
				Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
						"not.allowed");
				Assert.isTrue(panel.getStartMoment().after(currentMoment),"invalid.date");
				
				
				res=this.panelRepository.save(panel);
			}
			
			
			return res;
			*/
		}

		public void delete(Panel panel,Conference conference){
			
			
			Actor principal = this.actorService.findByPrincipal();
			Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
					"not.allowed");
			
			//conference.getActivities().remove(panel);
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
