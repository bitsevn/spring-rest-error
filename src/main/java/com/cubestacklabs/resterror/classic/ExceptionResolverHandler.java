package com.cubestacklabs.resterror.classic;

import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class ExceptionResolverHandler {

	private static final Logger log = LoggerFactory.getLogger(ExceptionResolverHandler.class);

	private Map<String, ErrorModel> errors = Collections.emptyMap();
	
	public ErrorModel resolveError(ServletWebRequest request, Object handler, Exception ex) {

		ErrorModel template = errors.get(ex.getClass().getName());// with
																	// package
		if (template == null) {
			return null;
		}

		ErrorModel error = new ErrorModel();
		error.setStatus(template.getStatus());
		error.setCode(template.getCode());
		error.setMoreInfoUrl(template.getMoreInfoUrl());
		error.setDeveloperMessage(template.getDeveloperMessage());
		error.setThrowable(ex);

		return error;
	}

	@PostConstruct
	public void parseAndCacheErrorTemplates() throws Exception {
		/*if (errors.isEmpty()) {
			errors = new LinkedHashMap<String, ErrorModel>();
			ObjectMapper mapper = new ObjectMapper();
			Resource resource = new ClassPathResource("errors.json");
			FileReader fr = new FileReader(resource.getFile());
			ErrorTemplate[] templates = mapper.readValue(fr, ErrorTemplate[].class);
			if (templates != null && templates.length > 0) {
				String[] exceptions;
				for (ErrorTemplate template : templates) {
					if (template.valid()) {
						exceptions = template.getExceptions().split(",");
						for(String ex: exceptions) {
							errors.put(ex, template.getError());							
						}
					} else {
						log.warn("Invalid error template configuration found -> " + template
								+ ". Please check that you have entered valid entries in [errors.json].");
					}
				}
			}
		}*/
	}
}
