package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubmissionRepository;
import domain.Author;
import domain.Conference;
import domain.Reviewer;
import domain.Submission;

@Transactional
@Service

public class SubmissionService {

	//Repository ------------------

	@Autowired
	private SubmissionRepository submissionRepository;


	//Services

	@Autowired
	private ActorService actorService;

	@Autowired
	private PaperService paperService;

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private ReviewerService reviewerService;

	//CRUD Methods ----------------

	public Submission create(){

		Submission result = new Submission();

		Author principal = (Author)this.actorService.findByPrincipal();

		result.setMadeMoment(new Date(System.currentTimeMillis()-1));
		result.setStatus("UNDER-REVIEW");
		result.setToReview(true);
		result.setAuthor(principal);
		result.setConference(new Conference());
		result.setTicker(this.utilityService.getTicker());
		result.setToAuthor(false);

		return result;
	}

	public Submission save(Submission submission){

		Author principal = (Author) this.actorService.findByPrincipal();
		Submission result;

		if(submission.getId() != 0){

			Assert.isTrue(submission.getMadeMoment().before(submission.getConference().getSubmissionDeadline()), "submission.deadline");
			Assert.isTrue(submission.getStatus().equals("ACCEPTED"), "submission.notAccepted");
			Assert.isTrue(new Date(System.currentTimeMillis()-1).before(submission.getConference().getCameraReadyDeadline()), "submission.Camera");



		}else{
			Assert.isTrue(submission.getAuthor().equals(principal), "no.permission");
			Assert.isTrue(submission.getStatus().equals("UNDER-REVIEW"), "commit.error");


		}


		result=this.submissionRepository.saveAndFlush(submission);

		return result;

	}

	public void autoAssignReviewers(Collection<Submission> submissions){
		Collection<Reviewer> reviewers = this.reviewerService.findAll();


		for(Submission s : submissions){


			for(Reviewer r : reviewers){


				if(s.getReviewers().size() < 3){

					Collection<String> keyWords = new ArrayList<String>();

					keyWords = r.getKeywords();

					for(String key : keyWords){
						if((s.getConference().getTitle().contains(key) || 
								s.getConference().getSummary().contains(key)) &&
								(!s.getReviewers().contains(r))) {
							s.getReviewers().add(r);

						}
					}
				}else{
					break;
				}



			}
		}
	}



	public Collection<Submission> findAll(){
		Collection<Submission> result = this.submissionRepository.findAll();

		return result;
	}


	public Collection<Submission> getSubmissionByOwner(int id){
		Collection<Submission> result = this.submissionRepository.getSubmissionByOwner(id);

		return result;

	}

	public Collection<Submission> getSubmissionToUpload(int id){
		Collection<Submission> result = this.submissionRepository.getSubmissionToUpload(id);

		return result;
	}

	public Collection<Submission> getAcceptedByConference(int id){
		Collection<Submission> result = this.submissionRepository.getAcceptedSubmissionByConference(id);

		return result;
	}

	public Collection<Submission> getRejectedByConference(int id){
		Collection<Submission> result = this.submissionRepository.getRejectedSubmissionByConference(id);

		return result;
	}

	public Collection<Submission> getUnderByConference(int id){
		Collection<Submission> result = this.submissionRepository.getUnderSubmissionByConference(id);

		return result;
	}

	public Collection<Submission> getBorderByConference(int id){
		Collection<Submission> result = this.submissionRepository.getBorderSubmissionByConference(id);

		return result;
	}






	public Collection<Submission> submissionsOfReviewer(int reviewerId){
		return this.submissionRepository.submissionsOfReviewer(reviewerId);
	}
	public Submission findOne(int id){
		return this.submissionRepository.findOne(id);
	}

	public void  saveForce(Submission submission){
		this.submissionRepository.save(submission);
	}

	public Collection<Submission> submissionsPerConferenceDecisionMaking(int conferenceId){
		return this.submissionRepository.submissionsPerConferenceDecisionMaking(conferenceId);

	}
	public Submission reportTimeToComment(int reportId){
		return this.submissionRepository.reportTimeToComment(reportId);
	}


	public Submission isSubUnique(String res){

		return this.submissionRepository.isSubUnique(res);
	}

	public Collection<Submission> getSubmissionToAssign(int conferenceId){
		Collection<Submission> result = this.submissionRepository.getSubmissionToAssign(conferenceId);

		return result;
	}
	public 	Collection<Submission> submissionsToAuthor(int authorId){
		return this.submissionRepository.submissionsToAuthor(authorId);
	}


	public Submission SubDisplayReportAuthor(int  reportId,int authorId){
		return this.submissionRepository.SubDisplayReportAuthor(reportId, authorId);
	}

	public Collection<Submission> getSubmissionByConference(int id){
		Collection<Submission> result = this.submissionRepository.getSubmissionsByConference(id);

		return result;
	}
	
	public Submission getSubmissionByPaper(int id){
		Submission result = this.submissionRepository.getSubmissionByPaper(id);
		
		return result;
	}
}
