package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.MessageService;
import services.SystemConfigurationService;
import domain.Actor;
import domain.Message;
import domain.SystemConfiguration;

@Controller
@RequestMapping("message/actor")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;
					
	@Autowired
	private SystemConfigurationService systemConfigurationService;

	// Create
	// ----------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final Locale locale) {
		final ModelAndView result;
		Message mensaje;
		
		String language;
		String español;
		String english;
		español = "es";
		english = "en";
		
		language = locale.getLanguage();
		
		mensaje = this.messageService.create();

		result = this.createEditModelAndView(mensaje);
		
		result.addObject("language", language);
		result.addObject("español", español);
		result.addObject("english", english);

		return result;
	}
	
	@RequestMapping(value="/list" , method = RequestMethod.GET)
	public ModelAndView list(final Locale locale){
		ModelAndView result;
		String language;
		String español;
		String english;
		español = "es";
		english = "en";
		
		language = locale.getLanguage();
		
		Actor principal = this.actorService.findByPrincipal();
		
		boolean isAll = false;
		
		boolean isSenderAdmin = false;
		boolean isSenderAuthor = false;
		boolean isSenderReviewer = false;
		
		boolean isReceiverAdmin = false;
		boolean isReceiverAuthor = false;
		boolean isReceiverReviewer = false;
		
		
		result = new ModelAndView("message/list");
		
		Collection<Message> all = this.messageService.findAll();
		
		Collection<Message> senderAdmin = new ArrayList<Message>();
		Collection<Message> senderAuthor = new ArrayList<Message>();
		Collection<Message> senderReviewer = new ArrayList<Message>();
		
		Collection<Message> receiverAdmin = new ArrayList<Message>();
		Collection<Message> receiverAuthor = new ArrayList<Message>();
		Collection<Message> receiverReviewer = new ArrayList<Message>();
		
		
		for(Message m: all){
			
			Authority senderAuthority = m.getSender().getUserAccount().getAuthorities().iterator().next();
			Authority receiverAuthority = m.getReceiver().getUserAccount().getAuthorities().iterator().next();
			
			//List by sender admin
			if(senderAuthority.getAuthority().equals("ADMIN")){
				senderAdmin.add(m);
			}
			

			//List by sender author
			if(senderAuthority.getAuthority().equals("AUTHOR")){
				senderAuthor.add(m);
			}
			
			//List by sender reviewer
			if(senderAuthority.getAuthority().equals("REVIEWER")){
				senderReviewer.add(m);
			}
			
			//List by receiver admin
			if(receiverAuthority.getAuthority().equals("ADMIN")){
				receiverAdmin.add(m);
			}
			
			//List by receiver author
			if(receiverAuthority.getAuthority().equals("AUTHOR")){
				receiverAuthor.add(m);
			}
			
			//List by receiver reviewer
			if(receiverAuthority.getAuthority().equals("REVIEWER")){
				receiverReviewer.add(m);
			}
			
			
		}
		
		
		if(!all.isEmpty()){
			result.addObject("all", all);
			
			isAll = true;
		}
		
		if(!senderAdmin.isEmpty()){
			result.addObject("senderAdmin", senderAdmin);
			
			isSenderAdmin = true;
		}
		
		if(!senderAuthor.isEmpty()){
			result.addObject("senderAuthor", senderAuthor);
			
			isSenderAuthor = true;
		}
		
		if(!senderReviewer.isEmpty()){
			result.addObject("senderReviewer", senderReviewer);
			
			isSenderReviewer = true;
		}
		
		if(!receiverAdmin.isEmpty()){
			result.addObject("receiverAdmin", receiverAdmin);
			
			isReceiverAdmin= true;
		}
		
		if(!receiverAuthor.isEmpty()){
			result.addObject("receiverAuthor", receiverAuthor);
			
			isReceiverAuthor= true;
		}
		
		if(!receiverReviewer.isEmpty()){
			result.addObject("receiverReviewer", receiverReviewer);
			
			isReceiverReviewer= true;
		}
		
		
		result.addObject("language", language);
		result.addObject("español", español);
		result.addObject("english", english);
		
		result.addObject("isAll", isAll);
		
		result.addObject("isSenderAdmin", isSenderAdmin);
		result.addObject("isSenderAuthor", isSenderAuthor);
		result.addObject("isSenderReviewer", isSenderReviewer);
		
		result.addObject("isReceiverAdmin", isReceiverAdmin);
		result.addObject("isReceiverAuthor", isReceiverAuthor);
		result.addObject("isReceiverReviewer", isReceiverReviewer);
		
		return result;
	}

	// Edition

	@RequestMapping(value = "display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int messageId,
			final Locale locale) {
		ModelAndView result;
		Message message;
		
		String language;
		String español;
		String english;
		español = "es";
		english = "en";
		
		message = this.messageService.findOne(messageId);
		
		language = locale.getLanguage();
		
		result = new ModelAndView("message/display");
		result.addObject("message0", message);
		result.addObject("language", language);
		result.addObject("español", español);
		result.addObject("english", english);

		return result;
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int messageId,
			final Locale locale) {

		ModelAndView result;
		Message message;
		
		String language;
		String español;
		String english;
		español = "es";
		english = "en";
		
		language = locale.getLanguage();

		message = this.messageService.findOne(messageId);

		result = this.createEditModelAndView(message);
		
		result.addObject("language", language);
		result.addObject("español", español);
		result.addObject("english", english);
		
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam(required=false) String topic,@Valid Message mensaje, final BindingResult binding) {
		ModelAndView result;
		
		this.messageService.topicsFound(topic, mensaje);
		

		if (binding.hasErrors()&& mensaje.getTopic()==null)
			result = this.createEditModelAndView(mensaje,null);
		else
			try {
				this.messageService.save(mensaje);
				result = new ModelAndView("redirect:/message/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(mensaje,
						"message.commit.error");
			}
		return result;

	}


	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Message mensaje,
			final BindingResult binding) {
		ModelAndView result;

	

		

		if (binding.hasErrors())
			result = this.createEditModelAndView(mensaje);
		else
			try {
				this.messageService.delete(mensaje);
				result = new ModelAndView("redirect:/message/list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(mensaje,
						"message.commit.error");

			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Message mensaje) {
		ModelAndView result;

		result = this.createEditModelAndView(mensaje, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Message mensaje,
			final String messageError) {
		ModelAndView result;

		Collection<Message> messages;
		Actor sender;
		boolean possible = false;
		Actor principal;
		Date sentMoment;
		Collection<Actor> recipients;

		SystemConfiguration sysConfg = this.systemConfigurationService.findMySystemConfiguration();
		
		

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		messages = new ArrayList<Message>();
		recipients = new ArrayList<Actor>();







		if (mensaje.getSender().equals(principal)){
			possible = true;



	}

	sentMoment = mensaje.getSendMoment();
	
	sender = mensaje.getSender();

	recipients = this.actorService.findAllExceptPrincipal();

	
	Map<String,String> topics = sysConfg.getTopics();
	
	String topicsEs = topics.get("Español");

	String[] topicEsParsed = topicsEs.split(",");
	
	Collection<String> allEsp = new ArrayList<String>();
	
	for(String s : topicEsParsed){
		allEsp.add(s);
	}
	Collection<String> allEn = new ArrayList<String>();
	
	for(String s : topics.get("English").split(",")){
		allEn.add(s);
	}
	result = new ModelAndView("message/edit");
	result.addObject("sentMoment", sentMoment);
	
	result.addObject("sender", sender);
	result.addObject("mensaje", mensaje);
	
	result.addObject("requestURI", "message/actor/edit.do");
	result.addObject("possible", possible);
	result.addObject("broadcast", false);
	result.addObject("message", messageError);
	result.addObject("recipients", recipients);
	result.addObject("allEsp",allEsp);
	result.addObject("allEn",allEn);
	result.addObject("topics", topics);

	return result;
}
}