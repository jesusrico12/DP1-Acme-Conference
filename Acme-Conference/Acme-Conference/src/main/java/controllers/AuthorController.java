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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Author;

import forms.AuthorForm;

import services.AuthorService;


	@Controller
	@RequestMapping("/author")
	public class AuthorController extends AbstractController {
		
		@Autowired
		private AuthorService authorService;
		
		


			// Show ---------------------------------------------------------------

			@RequestMapping(value = "/display", method = RequestMethod.GET)
			public ModelAndView show(@RequestParam(required = false) final Integer authorId) {
				ModelAndView result;
				Author author = new Author();
				boolean isMe = false;
				result = new ModelAndView("author/display");
				if (authorId == null) {
					author = this.authorService.findByPrincipal();
					isMe = true;
				} else {
					author = this.authorService.findOne(authorId);
				}

				result.addObject("requestURI", "author/display.do?authorId=" + authorId);
				result.addObject("author", author);
				result.addObject("isMe", isMe);

				return result;
			}

			// Create ---------------------------------------------------------------

			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create() {
				ModelAndView result;
				Author author;

				author = this.authorService.create();
				result = this.createEditModelAndView(authorService.construct(author));

				return result;
			}

			// Edit ---------------------------------------------------------------

			@RequestMapping(value = "/edit", method = RequestMethod.GET)
			public ModelAndView edit() {
				ModelAndView result;
				Author author;

				author = this.authorService.findByPrincipal();

				result = this.createEditModelAndView(authorService.construct(author));

				return result;
			}

			// Save ---------------------------------------------------------------

			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid final AuthorForm authorForm, final BindingResult binding) {
				ModelAndView result;

				if (binding.hasErrors()) {
					result = this.createEditModelAndView(authorForm);
					for (final ObjectError e : binding.getAllErrors())
						System.out.println(
								e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
				} else if (!authorForm.getNewPassword().equals(authorForm.getConfirmPassword())) {
					result = this.createEditModelAndView(authorForm, "author.validator.passwordConfirmNotMatch");
				}else if (!StringUtils.isEmpty(authorForm.getPhoto())&& !authorForm.getPhoto().startsWith("https://www.dropbox.com")&&!authorForm.getPhoto().startsWith("https://www.flickr.com")){
					
					result = this.createEditModelAndView(authorForm, "author.validator.photoURL");
								
				} 
				else
					try {
						this.authorService.verifyAndSave(authorForm, binding);
						result = new ModelAndView("redirect:/welcome/index.do");
					} catch (final Throwable oops) {
						result = this.createEditModelAndView(authorForm, "author.commit.error");
					}
				return result;
			}

			protected ModelAndView createEditModelAndView(final AuthorForm author) {
				ModelAndView result;

				result = this.createEditModelAndView(author, null);
				return result;
			}

			protected ModelAndView createEditModelAndView(final AuthorForm author, final String messageCode) {
				ModelAndView result;

				result = new ModelAndView("author/edit");

				result.addObject("authorForm", author);
				result.addObject("message", messageCode);

				return result;
			}

		}

		

