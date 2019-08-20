package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;

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
	private SystemConfigurationService systemConfigurationService;

	// CRUD METHODS

	public Message create() {
		Message result = new Message();
		Actor principal = this.actorService.findByPrincipal();

		result.setSendMoment(new Date(System.currentTimeMillis() - 1));
		result.setSender(principal);



		return result;
	}

	public Message save(final Message message) {
		Message result = new Message();
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

//	public void broadcast(final Message m) {
//		Actor principal;
//		String subject, priority, body, tags;
//		Date sentMoment;
//		boolean isSpam;
//		MessageBox outBoxAdmin;
//		Collection<MessageBox> allBoxes, boxes, notificationBoxes;
//		Message saved;
//		Collection<Actor> recipients;
//
//		allBoxes = new ArrayList<MessageBox>();
//		notificationBoxes = new ArrayList<MessageBox>();
//		boxes = new ArrayList<MessageBox>();
//		recipients = new ArrayList<Actor>();
//
//		recipients = this.actorService.findAll();
//
//		principal = this.actorService.findByPrincipal();
//		Assert.notNull(principal);
//		Assert.isTrue(this.actorService.checkAuthority(principal,
//				"ADMINISTRATOR"));
//
//		Assert.notNull(m);
//
//		subject = m.getSubject();
//		body = m.getBody();
//		priority = m.getPriority();
//		tags = m.getTag();
//		isSpam = false;
//		sentMoment = new Date(System.currentTimeMillis() - 1);
//
//		for (Actor a : recipients) {
//			if (!(this.actorService.checkAuthority(a, "ADMIN"))) {
//				notificationBoxes.add(this.messageBoxService.findByName(
//						principal.getId(), "Notification box"));
//			}
//
//		}
//
//		outBoxAdmin = this.messageBoxService.findByName(principal.getId(),
//				"Out box");
//		Assert.notNull(outBoxAdmin);
//
//		final Message message = new Message();
//		message.setSubject(subject);
//		message.setBody(body);
//		message.setSendMoment(sentMoment);
//		message.setPriority(priority);
//		message.setTag(tags);
//		message.setIsSpam(isSpam);
//		message.setReceiver(principal);
//		message.setSender(principal);
//
//		boxes.add(outBoxAdmin);
//		boxes.addAll(notificationBoxes);
//
//		message.setMessageBoxes(boxes);
//
//		saved = this.messageRepository.save(message);
//
//		for (MessageBox notBox : notificationBoxes) {
//			notBox.getMessages().add(saved);
//		}
//		
//		outBoxAdmin.getMessages().add(saved);
//
//	}
//
//	
//
//	

	
	
	public Collection<Message> getAllByOwner(int id){
		Collection<Message> result = this.messageRepository.getMessagesByOwner(id);
		
		return result;
	}
}
