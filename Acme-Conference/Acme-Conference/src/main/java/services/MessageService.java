package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Author;
import domain.Message;
import domain.Registration;
import domain.Submission;

@Transactional
@Service

public class MessageService {

	// Repository

	@Autowired
	private MessageRepository messageRepository;

	// Services

	@Autowired
	private ActorService actorService;

	@Autowired
	private Validator validator;
	
	@Autowired
	private SubmissionService submissionService;

	@Autowired
	private SystemConfigurationService systemConfigurationService;
	
	@Autowired
	private RegistrationService registrationService;
	
	
	@Autowired
	private AuthorService authorService;

	// CRUD METHODS

	public Message create() {
		Message result = new Message();
		Actor principal = this.actorService.findByPrincipal();

		result.setSendMoment(new Date(System.currentTimeMillis() - 1));
		result.setSender(principal);



		return result;
	}

	public Message save(final Message message) {
		//Message result = new Message();
		Message result;
		Actor principal = this.actorService.findByPrincipal();


		// Checking sender is the principal
		Assert.isTrue(message.getSender().getId() == principal.getId(),
				"Not your message");

		// Checking receiver is an actor from the system
		Assert.isTrue(this.actorService.checkAuthority(message.getReceiver(),
				"AUTHOR")
				|| this.actorService.checkAuthority(message.getReceiver(),
						"REVIEWER")
						|| this.actorService.checkAuthority(message.getReceiver(),
								"ADMIN")
				);

		
		

			// Update some values like send moment

			message.setSendMoment(new Date(System.currentTimeMillis() - 1));
			result=this.messageRepository.save(message);
			

			return result;
		}
		
	


	public void delete(final Message message) {
		Actor principal;
		

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		this.messageRepository.delete(message);

	
	}

	



	public Message findOne(final int messageId) {
		Message result;

		result = this.messageRepository.findOne(messageId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Message> findAll() {
		Collection<Message> result;

		result = this.messageRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public void broadcast(final Message m) {
		Actor principal;
		String subject, body;
		Date sentMoment;
		Message saved;
		Collection<Actor> recipients;

		
		recipients = new ArrayList<Actor>();

		recipients = this.actorService.findAll();

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(this.actorService.checkAuthority(principal,
				"ADMIN"));

		Assert.notNull(m);

		subject = m.getSubject();
		body = m.getBody();
		Map<String,String> topic = m.getTopic();
		
		
		sentMoment = new Date(System.currentTimeMillis() - 1);
		
		for (Actor a : recipients) {
			final Message message = new Message();
			message.setSubject(subject);
			message.setBody(body);
			message.setSendMoment(sentMoment);
			message.setTopic(topic);

			message.setReceiver(a);
			message.setSender(principal);
			
			saved = this.messageRepository.save(message);
				
			}

		


		

		

	}

	
	public void broadcastAuthorsSubmission(final Message m) {
		Actor principal;
		String subject, body;
		Date sentMoment;
		Message saved;
		

		
		
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(this.actorService.checkAuthority(principal,
				"ADMIN"));

		Assert.notNull(m);

		subject = m.getSubject();
		body = m.getBody();
		Map<String,String> topic = m.getTopic();
		
		
		sentMoment = new Date(System.currentTimeMillis() - 1);
		
		Collection<Submission> submissions = this.submissionService.findAll();
		
		for (Submission s : submissions) {
			final Message message = new Message();
			message.setSubject(subject);
			message.setBody(body);
			message.setSendMoment(sentMoment);
			message.setTopic(topic);

			message.setReceiver(s.getAuthor());
			message.setSender(principal);
			
			saved = this.messageRepository.save(message);
				
			}

		
		

		

		

	}
	
	public void notificateAuthors(Submission submission){
		Message message = this.create();
		
		message.setSubject("Your submission have been :" + submission.getStatus());
		message.setBody("Your submission have been :" + submission.getStatus());
		
		
		message.setReceiver(submission.getAuthor());
		
		
		this.messageRepository.save(message);
		submission.setToAuthor(true);
		this.submissionService.saveForce(submission);
		
	}
	

	public void broadcastAuthorsRegistration(final Message m) {
		Actor principal;
		String subject, body;
		Date sentMoment;
		Message saved;
		

		
		
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(this.actorService.checkAuthority(principal,
				"ADMIN"));

		Assert.notNull(m);

		subject = m.getSubject();
		body = m.getBody();
		Map<String,String> topic = m.getTopic();
		
		
		sentMoment = new Date(System.currentTimeMillis() - 1);
		
		Collection<Registration> registrations = this.registrationService.findAll();
		
		for (Registration r : registrations) {
			final Message message = new Message();
			message.setSubject(subject);
			message.setBody(body);
			message.setSendMoment(sentMoment);
			message.setTopic(topic);

			message.setReceiver(r.getAuthor());
			message.setSender(principal);
			
			saved = this.messageRepository.save(message);
				
			}

		


		

		

	}
	
	
	public void broadcastAuthors(final Message m) {
		Actor principal;
		String subject, body;
		Date sentMoment;
		Message saved;
		

		
		
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(this.actorService.checkAuthority(principal,
				"ADMIN"));

		Assert.notNull(m);

		subject = m.getSubject();
		body = m.getBody();
		Map<String,String> topic = m.getTopic();
		
		
		sentMoment = new Date(System.currentTimeMillis() - 1);
		
		Collection<Author> authors = this.authorService.findAll();
		
		for (Author a : authors) {
			final Message message = new Message();
			message.setSubject(subject);
			message.setBody(body);
			message.setSendMoment(sentMoment);
			message.setTopic(topic);

			message.setReceiver(a);
			message.setSender(principal);
			
			saved = this.messageRepository.save(message);
				
			}

		


		

		

	}
	
	public Collection<Message> getAllByOwner(int id){
		Collection<Message> result = this.messageRepository.getMessagesByOwner(id);
		
		return result;
	}
	
	public Message topicsFound(String topic ,Message mensaje){
		if(topic!=null){
		Map <String,String> topics= this.systemConfigurationService.findMySystemConfiguration().getTopics();
		String[] topicsEn=topics.get("English").split(",");
		List<String> listaEn= new ArrayList<String>();
		String[] topicsEs=topics.get("Español").split(",");
		List<String> listaEs= new ArrayList<String>();
		for(String s: topicsEn){
			listaEn.add(s.trim());
		}
		for(String s: topicsEs){
			listaEs.add(s.trim());
		}
		topic.trim();
		
	
	
		if(listaEs.contains(topic)){
		
			int pos=listaEs.indexOf(topic);
			if(pos!=-1){
			
				Map<String,String> t =new HashMap<String,String>();
				t.put(topic, listaEn.get(pos));
				mensaje.setTopic(t);
				}
			}
		
		if(listaEn.contains(topic)){
			
			int pos=listaEn.indexOf(topic);
			if(pos!=-1){
			
				Map<String,String> t =new HashMap<String,String>();
				t.put(listaEs.get(pos), topic);
				mensaje.setTopic(t);
				}
			}
	}
				
		return mensaje;
	
		}
}
