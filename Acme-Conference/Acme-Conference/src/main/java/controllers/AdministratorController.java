package controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import domain.Actor;
import domain.Administrator;


@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	/* Services */

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private ActorService actorService;
	
	
	
	

	/* Methods */

	/**
	 * 
	 * Display admin
	 * 
	 * @params id (optional)
	 * 
	 * @return ModelAndView
	 * **/
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		Administrator toDisplay;
		String requestURI = "administrator/display.do";
		Boolean found = true;
		Boolean permission;

		try {
			if (id != null) {
				toDisplay = (Administrator) this.actorService.findOne(id);
				requestURI += "?id=" + id;
				if (toDisplay == null)
					found = false;
				permission = (toDisplay.getId() == this.actorService
						.findByPrincipal().getId()) ? true : false;
			} else {
				toDisplay = (Administrator) this.actorService.findByPrincipal();
				permission = true;
			}

			res = new ModelAndView("administrator/display");
			res.addObject("admin", toDisplay);
			res.addObject("found", found);
			res.addObject("requestURI", requestURI);
			res.addObject("permission", permission);
		} catch (final Throwable oops) {
			found = false;
			res = new ModelAndView("administrator/display");
			
		}

		return res;
	}
	
	/**
	 * 
	 * Register administrator GET
	 * 
	 * @return ModelAndView
	 **/

	@RequestMapping(value = "/administrator/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Administrator administrator;

		administrator = this.administratorService.create();
		res = this.createEditModelAndView(administrator);
		res.addObject(administrator);
		return res;
	}
	/**
	 * 
	 * Register administrator POST
	 * 
	 * @return ModelAndView
	 **/
	@RequestMapping(value = "/administrator/register", method = RequestMethod.POST, params = "save")
	public ModelAndView register(
			@Valid final Administrator admin,
			final BindingResult binding) {

		ModelAndView res;

		Administrator administrator = new Administrator();
		administrator = this.administratorService.create();



		if (binding.hasErrors())
			
			res = new ModelAndView("administrator/register");
			
		else
			try {

				this.administratorService.save(administrator);

				res = new ModelAndView("redirect:/");

			} catch (final Throwable oops) {
				res = new ModelAndView("administrator/register");
				res = res.addObject("messageCode", "film.commit.error");

			}
		return res;
	}
	protected ModelAndView createEditModelAndView(Administrator administrator) {
		ModelAndView res;

		res = this.createEditModelAndView(administrator, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator,
			String messageCode) {
		ModelAndView res;

		res = new ModelAndView("administrator/register");
		res.addObject("administrator", administrator);
		res.addObject("message", messageCode);
		return res;
	}


}
