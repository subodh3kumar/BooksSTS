package workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import workshop.model.License;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseController {

	@GetMapping("/{licenseId}")
	public License getLicenses(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		return new License().withId(licenseId).withOrganizationId("Test Org").withProductName("Teleco")
				.withLicenseType("Seat");
	}

	@PutMapping("{licenseId}")
	public String updateLicense(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the put");
	}
	
	@PostMapping("{licenseId}")
	public String postLicense(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the post");
	}
	
	@DeleteMapping("{licenseId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String deleteLicense(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the delete");
	}
}
