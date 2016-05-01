package com.cubestacklabs.resterror.classic;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExceptionResolver extends AbstractHandlerExceptionResolver {

	private static final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

	@Autowired
	private ExceptionResolverHandler resolverHandler;

	private List<HttpMessageConverter<?>> allMessageConverters = null;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ServletWebRequest webRequest = new ServletWebRequest(request, response);

		ErrorModel error = resolverHandler.resolveError(webRequest, handler, ex);
		if (error == null) {
			error = new ErrorModel();
			error.setStatus("500");
			error.setCode("500");
			error.setDeveloperMessage(
					"An exception is throws and not found in [errors.json] mapping templates. Please look at stack trace/logs for more details.");
			error.setMessage("Internal server error.");
			error.setMoreInfoUrl("http://localhost:8080/errors/404");
			error.setTimestamp(String.valueOf(Calendar.getInstance().getTime()));
			error.setStackTrace(ExceptionUtils.getStackTrace(ex));
			error.setThrowable(ex);
			// return null;
		} else {
			log.error("Exception thrown for [" + request.getRequestURL() + "] -> Error: " + error + ".", ex);
		}
		
		error.setTimestamp(String.valueOf(Calendar.getInstance().getTime()));
		error.setPath(request.getRequestURI());
		
		ModelAndView mav = null;

		try {
			mav = getModelAndView(webRequest, handler, error);
		} catch (Exception invocationEx) {
			log.error("Acquiring ModelAndView for Exception [" + ex + "] resulted in an exception.", invocationEx);
		}
		return mav;
	}

	protected ModelAndView getModelAndView(ServletWebRequest webRequest, Object handler, ErrorModel error)
			throws Exception {
		applyStatusIfPossible(webRequest, error);
		ServletServerHttpResponse outputMessage = this.createOutputMessage(webRequest);
		outputMessage.getServletResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
		outputMessage.getServletResponse().getWriter().write(new ObjectMapper().writeValueAsString(error));
		return new ModelAndView();
		//return handleResponseBody(error, webRequest);
	}

	protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		return new ServletServerHttpResponse(response);
	}

	private void applyStatusIfPossible(ServletWebRequest webRequest, ErrorModel error) {
		if (!WebUtils.isIncludeRequest(webRequest.getRequest())) {
			webRequest.getResponse().setStatus(Integer.valueOf(error.getStatus()));
		}
		// TODO support response.sendError ?
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	private ModelAndView handleResponseBody(Object body, ServletWebRequest webRequest)
			throws ServletException, IOException {

		HttpInputMessage inputMessage = new ServletServerHttpRequest(webRequest.getRequest());

		List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
		if (acceptedMediaTypes.isEmpty()) {
			acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
		}

		MediaType.sortByQualityValue(acceptedMediaTypes);

		HttpOutputMessage outputMessage = new ServletServerHttpResponse(webRequest.getResponse());

		Class<?> bodyType = body.getClass();

		List<HttpMessageConverter<?>> converters = this.allMessageConverters;

		if (converters != null) {
			for (MediaType acceptedMediaType : acceptedMediaTypes) {
				for (HttpMessageConverter messageConverter : converters) {
					if (messageConverter.canWrite(bodyType, acceptedMediaType)) {
						messageConverter.write(body, acceptedMediaType, outputMessage);
						// return empty model and view to short circuit the
						// iteration and to let
						// Spring know that we've rendered the view ourselves:
						return new ModelAndView();
					}
				}
			}
		}

		if (logger.isWarnEnabled()) {
			logger.warn("Could not find HttpMessageConverter that supports return type [" + bodyType + "] and "
					+ acceptedMediaTypes);
		}
		return null;
	}
}
