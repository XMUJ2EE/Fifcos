package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.service.SeminarService;
import xmu.crms.service.TopicService;
import xmu.crms.service.impl.SeminarServiceImpl;
import xmu.crms.view.vo.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mads
 */

@Controller
@RequestMapping("/seminar")
public class SeminarController {
	@Autowired
	SeminarServiceImpl seminarService;

	@Autowired
	TopicService topicService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarById(@PathVariable BigInteger seminarId) {
		try {
			Seminar seminar = seminarService.getSeminarBySeminarId(seminarId);
			List<Topic> topics = topicService.listTopicBySeminarId(seminarId);
			SeminarVO seminarVO = new SeminarVO(seminar, topics);
			return ResponseEntity.status(200).body(seminarVO);
		} catch (SeminarNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateSeminarById(@PathVariable int seminarId) {

		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}", method = DELETE)
	public ResponseEntity deleteSeminarById(@PathVariable int seminarId) {
		try {
			seminarService.deleteSeminarBySeminarId(BigInteger.valueOf(seminarId));
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}/my", method = GET)
	public ResponseEntity getSeminarWithMe(@PathVariable int seminarId) {


		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDNET')")
	@RequestMapping(value = "/{seminarId}/detail", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarDetail(@PathVariable int seminarId) {
		Seminar seminar = null;
		SeminarDetailVO seminarDetailVO = null;
		try {
			seminar = seminarService.getSeminarBySeminarId(BigInteger.valueOf(seminarId));
			seminarDetailVO = new SeminarDetailVO(seminar.getId().intValue(), seminar.getName(), null, seminar.getStartTime().toString(),
					seminar.getEndTime().toString(), seminar.getCourse().getTeacher().getName(), seminar.getCourse().getTeacher().getEmail());
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminar);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}/topic", method = GET)
	@ResponseBody
	public ResponseEntity getTopicBySeminarId(@PathVariable int seminarId) {
		List<Topic> listTopic = new ArrayList<Topic>();
		try{
			listTopic = topicService.listTopicBySeminarId(BigInteger.valueOf(seminarId));
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listTopic);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}/topic", method = POST)
	public ResponseEntity addTopicBySeminarId(@PathVariable int seminarId){

		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public int id=257;});
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}/group", method = GET)
	@ResponseBody
	public ResponseEntity getGroupBySeminarId(@PathVariable int seminarId,
															  @RequestParam(value = "gradeable",required = false)Boolean gradeable,
															  @RequestParam(value = "classId", required = false)Integer classId) {


		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}/group/my", method = GET)
	@ResponseBody
	public ResponseEntity getMyGroupBySeminarId(@PathVariable int seminarId){

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}



}
