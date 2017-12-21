package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;
import xmu.crms.view.vo.*;

@Controller

@RequestMapping("/seminar")

public class SeminarController {
	
	@RequestMapping(value = "/{seminarId}", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarById(@PathVariable int seminarId) {

		return ResponseEntity.status(200).body(null);
	}
	
	@RequestMapping(value = "/{seminarId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateSeminarById(@PathVariable int seminarId) {


		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{seminarId}", method = DELETE)
	public ResponseEntity deleteSeminarById(@PathVariable int seminarId) {

		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{seminarId}/my", method = GET)
	public ResponseEntity getSeminarWithMe(@PathVariable int seminarId) {


		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@RequestMapping(value = "/{seminarId}/detail", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarDetail(@PathVariable int seminarId) {

		return ResponseEntity.status(200).body(null);
	}

	@RequestMapping(value = "/{seminarId}/topic", method = GET)
	@ResponseBody
	public ResponseEntity getTopicBySeminarId(@PathVariable int seminarId) {

		return ResponseEntity.status(200).body(null);
	}

	@RequestMapping(value = "/{seminarId}/topic", method = POST)
	public ResponseEntity addTopicBySeminarId(@PathVariable int seminarId){
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public int id=257;});
	}
	
	@RequestMapping(value = "/{seminarId}/group", method = GET)
	@ResponseBody
	public ResponseEntity getGroupBySeminarId(@PathVariable int seminarId,
															  @RequestParam(value = "gradeable",required = false)Boolean gradeable,
															  @RequestParam(value = "classId", required = false)Integer classId) {


		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@RequestMapping(value = "/{seminarId}/group/my", method = GET)
	@ResponseBody
	public ResponseEntity getMyGroupBySeminarId(@PathVariable int seminarId){

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}



}
