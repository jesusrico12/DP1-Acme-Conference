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

import domain.Reviewer;

import forms.ReviewerForm;

import services.ReviewerService;


	@Controller
	@RequestMapping("/reviewer")
	public class ReviewerController extends AbstractController {
		
		@Autowired
		private ReviewerService reviewerService;
		
		


			// Show ---------------------------------------------------------------

			@RequestMapping(value = "/display", method = RequestMethod.GET)
			public ModelAndView show(@RequestParam(required = false) final Integer reviewerId) {
				ModelAndView result;
				Reviewer reviewer = new Reviewer();
				boolean isMe = false;
				result = new ModelAndView("reviewer/display");
				if (reviewerId == null) {
					reviewer = this.reviewerService.findByPrincipal();
					isMe = true;
				} else {
					reviewer = this.reviewerService.findOne(reviewerId);
				}

				result.addObject("requestURI", "reviewer/display.do?reviewerId=" + reviewerId);
				result.addObject("reviewer", reviewer);
				result.addObject("isMe", isMe);

				return result;
			}

			// Create ---------------------------------------------------------------

			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create() {
				ModelAndView result;
				Reviewer reviewer;

				reviewer = this.reviewerService.create();
				result = this.createEditModelAndView(reviewerService.construct(reviewer));

				return result;
			}

			// Edit ---------------------------------------------------------------

			@RequestMapping(value = "/edit", method = RequestMethod.GET)
			public ModelAndView edit() {
				ModelAndView result;
				Reviewer reviewer;

				reviewer = this.reviewerService.findByPrincipal();

				result = this.createEditModelAndView(reviewerService.construct(reviewer));

				return result;
			}

			// Save ---------------------------------------------------------------

			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid final ReviewerForm reviewerForm, final BindingResult binding) {
				ModelAndView result;

				if (binding.hasErrors()) {
					result = this.createEditModelAndView(reviewerForm);
					for (final ObjectError e : binding.getAllErrors())
						System.out.println(
								e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
				} else if (!reviewerForm.getNewPassword().equals(reviewerForm.getConfirmPassword())) {
					result = this.createEditModelAndView(reviewerForm, "reviewer.validator.passwordConfirmNotMatch");
				}else if (!StringUtils.isEmpty(reviewerForm.getPhoto())&& !reviewerForm.getPhoto().startsWith("https://www.dropbox.com")&&!reviewerForm.getPhoto().startsWith("https://www.flickr.com")){
					
					result = this.createEditModelAndView(reviewerForm, "reviewer.validator.photoURL");
								
				} 
				else
					try {
						this.reviewerService.verifyAndSave(reviewerForm, binding);
						result = new ModelAndView("redirect:/welcome/index.do");
					} catch (final Throwable oops) {
						result = this.createEditModelAndView(reviewerForm, "reviewer.commit.error");
					}
				return result;
			}

			protected ModelAndView createEditModelAndView(final ReviewerForm reviewer) {
				ModelAndView result;

				result = this.createEditModelAndView(reviewer, null);
				return result;
			}

			protected ModelAndView createEditModelAndView(final ReviewerForm reviewer, final String messageCode) {
				ModelAndView result;

				result = new ModelAndView("reviewer/edit");

				result.addObject("reviewerForm", reviewer);
				result.addObject("message", messageCode);

				return result;
			}

		}

		

