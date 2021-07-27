package nl.bentels.confserv.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageBlastingController {

	@Value("${message}")
	private String message;

	@GetMapping(path = "/")
	public String getMessage() {
		return message;
	}
}