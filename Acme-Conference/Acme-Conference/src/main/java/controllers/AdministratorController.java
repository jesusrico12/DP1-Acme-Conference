package controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import forms.AdministratorForm;
import services.AdministratorService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService administratorService;

	public AdministratorController() {
		super();
	}

	// Show ---------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;

		result = new ModelAndView("administrator/display");
		result.addObject("administrator", this.administratorService.findByPrincipal());

		return result;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.create();
		result = this.createEditModelAndView(administratorService.construct(administrator));

		return result;
	}

	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();

		result = this.createEditModelAndView(administratorService.construct(administrator));

		return result;
	}

	// Save ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AdministratorForm administratorForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(administratorForm);
			for (final ObjectError e : binding.getAllErrors())
				System.out.println(
						e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
		} else if (!administratorForm.getNewPassword().equals(administratorForm.getConfirmPassword())) {
			result = this.createEditModelAndView(administratorForm, "administrator.validator.passwordConfirmNotMatch");
		}
		else if (!StringUtils.isEmpty(administratorForm.getPhoto())&& !administratorForm.getPhoto().startsWith("https://www.dropbox.com")&&!administratorForm.getPhoto().startsWith("https://www.flickr.com")){
			
			result = this.createEditModelAndView(administratorForm, "administrator.validator.photoURL");
						
		}
		else
			try {
				this.administratorService.verifyAndSave(administratorForm, binding);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administratorForm, "administrator.commit.error");
			}
		return result;
	}
	
	protected ModelAndView createEditModelAndView(final AdministratorForm administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final AdministratorForm administrator, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("administrator/edit");

		result.addObject("administratorForm", administrator);
		result.addObject("message", messageCode);

		return result;
	}

}
