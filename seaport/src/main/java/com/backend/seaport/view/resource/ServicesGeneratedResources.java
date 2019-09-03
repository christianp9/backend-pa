/**
 * 
 */
package com.backend.seaport.view.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.seaport.deal.service.ServicesGeneratedService;
import com.backend.seaport.model.ServicesGenerated;
import com.backend.seaport.view.resource.vo.ServicesGeneratedVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author christian
 *
 */
@RestController
@RequestMapping("/v1/api/servicesGenerated")
@Api(tags = "ServicesGenerated")
public class ServicesGeneratedResources {

	private final ServicesGeneratedService servicesGeneratedService;

	public ServicesGeneratedResources(ServicesGeneratedService servicesGeneratedService) {
		this.servicesGeneratedService = servicesGeneratedService;
	}

	@PostMapping("/create")
	@ApiOperation(value = "Create service generated", notes = "Service to crate a new ervice generated")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "service generated create successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<ServicesGenerated> POSTServicesGenerated(
			@RequestBody ServicesGeneratedVo servicesGeneratedVo) {

		ServicesGenerated servicesGenerated = new ServicesGenerated();

		servicesGenerated.setIdEntityG(UUID.randomUUID().toString());
		servicesGenerated.setIdEntityM(servicesGenerated.getIdEntityG());
		servicesGenerated.setIdService(servicesGeneratedVo.getIdService());
		servicesGenerated.setReference(servicesGeneratedVo.getReference());
		servicesGenerated.setLloyd(servicesGeneratedVo.getLloyd());
		servicesGenerated.setUvi(servicesGeneratedVo.getUvi());
		servicesGenerated.setDock(servicesGeneratedVo.getDock());
		servicesGenerated.setDateAppointment(servicesGeneratedVo.getDateAppointment());
		servicesGenerated.setHourAppointment(servicesGeneratedVo.getHourAppointment());
		servicesGenerated.setHrsOp(servicesGeneratedVo.getHrsOp());
		servicesGenerated.setDateEnd(servicesGeneratedVo.getDateEnd());
		servicesGenerated.setHourEnd(servicesGeneratedVo.getHourEnd());
		servicesGenerated.setState(servicesGeneratedVo.getState());
		servicesGenerated.setTerminal(servicesGeneratedVo.getTerminal());

		if (servicesGenerated.getIdEntityG().isEmpty() || servicesGenerated.getIdEntityM().isEmpty()
				|| servicesGenerated.getIdService().isEmpty() || servicesGenerated.getReference().isEmpty()
				|| servicesGenerated.getLloyd().isEmpty() || servicesGenerated.getUvi().isEmpty()
				|| servicesGenerated.getDock().isEmpty() || servicesGenerated.getDateAppointment().isEmpty()
				|| servicesGenerated.getHourAppointment().isEmpty() || servicesGenerated.getHrsOp().isEmpty()
				|| servicesGenerated.getDateEnd().isEmpty() || servicesGenerated.getHourEnd().isEmpty()
				|| servicesGenerated.getState().isEmpty() || servicesGenerated.getTerminal().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(this.servicesGeneratedService.createServiceGenerated(servicesGenerated),
					HttpStatus.CREATED);
		}
	};

	@PutMapping("/{id}")
	@ApiOperation(value = "update service generated", notes = "Service to update a service generated")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "service generated updated successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<ServicesGenerated> PUTServicesGenerated(@PathVariable("id") String id,
			ServicesGeneratedVo servicesGeneratedVo) {

		ServicesGenerated servicesGenerated = this.servicesGeneratedService.findByIS(id);
		if (servicesGenerated == null) {
			return new ResponseEntity<ServicesGenerated>(HttpStatus.NOT_FOUND);
		} else {
			servicesGenerated.setIdService(servicesGeneratedVo.getIdService());
			servicesGenerated.setReference(servicesGeneratedVo.getReference());
			servicesGenerated.setLloyd(servicesGeneratedVo.getLloyd());
			servicesGenerated.setUvi(servicesGeneratedVo.getUvi());
			servicesGenerated.setDock(servicesGeneratedVo.getDock());
			servicesGenerated.setDateAppointment(servicesGeneratedVo.getDateAppointment());
			servicesGenerated.setHourAppointment(servicesGeneratedVo.getHourAppointment());
			servicesGenerated.setHrsOp(servicesGeneratedVo.getHrsOp());
			servicesGenerated.setDateEnd(servicesGeneratedVo.getDateEnd());
			servicesGenerated.setHourEnd(servicesGeneratedVo.getHourEnd());
			servicesGenerated.setState(servicesGeneratedVo.getState());
			servicesGenerated.setTerminal(servicesGeneratedVo.getTerminal());
		}
		;

		return new ResponseEntity<>(this.servicesGeneratedService.updateServiceGenerated(servicesGenerated),
				HttpStatus.OK);
	};

	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete service generated", notes = "Service to delete a service generated")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "service generated deleted successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public void DELETEServiceGenerated(@PathVariable("id") String id) {
		ServicesGenerated servicesGenerated = this.servicesGeneratedService.findByIS(id);
		if (servicesGenerated != null) {
			this.servicesGeneratedService.elimanateServiceGenerated(servicesGenerated);
		}
		;
	};

	@GetMapping
	@ApiOperation(value = "to list services Generated", notes = "Service to list all services generated")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "services generated found"),
			@ApiResponse(code = 404, message = "services generated not found") })
	public ResponseEntity<List<ServicesGenerated>> GETallServicesGenerated() {
		return ResponseEntity.ok(this.servicesGeneratedService.findallServiceGenerated());
	}
}
