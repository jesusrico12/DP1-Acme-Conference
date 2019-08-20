package controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;



@Controller
@RequestMapping(value = "statistics/administrator")
public class DashboardAdministratorController extends AbstractController{

	// Display

	@Autowired
	private ConferenceService conferenceService;


	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;

		Double[] numberOfDaysPerConference=this.conferenceService.numberOfDaysPerConference();
		Double[] feesPerConference=this.conferenceService.feesPerConference();
		Double[] registrationsPerConference=this.conferenceService.registrationsPerConference();
		Double[] submissionsPerConference=this.conferenceService.submissionsPerConference();
		result = new ModelAndView("administrator/statistics");


		result.addObject("requestURI", "statistics/administrator/display.do");
		result.addObject("submissionsPerConference",submissionsPerConference);
		result.addObject("registrationsPerConference",registrationsPerConference);
		result.addObject("feesPerConference",feesPerConference);
		result.addObject("numberOfDaysPerConference",numberOfDaysPerConference);
		return result;
	}
}
