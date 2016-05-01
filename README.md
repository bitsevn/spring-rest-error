# spring-rest-error
Better exception handling for Spring REST/MVC application. Eliminates boilerplate code for catching/logging exceptions, uses AOP approach to address the problem.

## Exception mappings ``[errors.json]``
``json
[
	{
		"exceptions": "java.lang.Throwable",
		"error": {
			"status": "500",
			"code": "500",
			"message": "Internal server error.",
			"developerMessage": "An exception is throws and not found in [errors.json] mapping templates. Please look at stack trace/logs for more details.",
			"moreInfoUrl": "http://localhost:8080/errors/404",
			"stackTrace": null,
			"throwable": null
		}
	},
	{
		"exceptions": "com.cubestacklabs.exception.NotFoundException,org.hibernate.ObjectNotFoundException",
		"exceptions": {
			"status": "404",
			"code": "404",
			"message": "Item not found.",
			"developerMessage": "Request resource not found in the system.",
			"moreInfoUrl": "http://localhost:8080/errors/404",
			"stackTrace": null,
			"throwable": null
		}
	}	
]
``

## REST json error send to clients

``json
{
  "status": "500",
  "code": "500",
  "message": "Internal server error.",
  "developerMessage": "An exception is throws and not found in [errors.json] mapping templates. Please look at stack trace/logs for more details.",
  "moreInfoUrl": "http://localhost:8080/errors/404",
  "stackTrace": "java.lang.IllegalArgumentException: You screwed up!\n\tat com.cubestacklabs.resterror.RestExceptionController.ex(RestExceptionController.java:16)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:606)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:221)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:110)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:817)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:731)\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:959)\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:893)\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:968)\n\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:859)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:622)\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:844)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:729)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:292)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.springframework.boot.actuate.autoconfigure.EndpointWebMvcAutoConfiguration$ApplicationContextHeaderFilter.doFilterInternal(EndpointWebMvcAutoConfiguration.java:237)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.springframework.boot.actuate.trace.WebRequestTraceFilter.doFilterInternal(WebRequestTraceFilter.java:112)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:87)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:77)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:121)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.springframework.boot.actuate.autoconfigure.MetricsFilter.doFilterInternal(MetricsFilter.java:103)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:212)\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:106)\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:502)\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:141)\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79)\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:88)\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:522)\n\tat org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1095)\n\tat org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:672)\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1500)\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.run(NioEndpoint.java:1456)\n\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)\n\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n\tat java.lang.Thread.run(Thread.java:745)\n",
  "throwable": {
    "cause": null,
    "stackTrace": [
      {
        "methodName": "ex",
        "fileName": "RestExceptionController.java",
        "lineNumber": 16,
        "className": "com.cubestacklabs.resterror.RestExceptionController",
        "nativeMethod": false
      },
      {
        "methodName": "invoke0",
        "fileName": "NativeMethodAccessorImpl.java",
        "lineNumber": -2,
        "className": "sun.reflect.NativeMethodAccessorImpl",
        "nativeMethod": true
      },
      {
        "methodName": "invoke",
        "fileName": "NativeMethodAccessorImpl.java",
        "lineNumber": 57,
        "className": "sun.reflect.NativeMethodAccessorImpl",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "DelegatingMethodAccessorImpl.java",
        "lineNumber": 43,
        "className": "sun.reflect.DelegatingMethodAccessorImpl",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "Method.java",
        "lineNumber": 606,
        "className": "java.lang.reflect.Method",
        "nativeMethod": false
      },
      {
        "methodName": "doInvoke",
        "fileName": "InvocableHandlerMethod.java",
        "lineNumber": 221,
        "className": "org.springframework.web.method.support.InvocableHandlerMethod",
        "nativeMethod": false
      },
      {
        "methodName": "invokeForRequest",
        "fileName": "InvocableHandlerMethod.java",
        "lineNumber": 136,
        "className": "org.springframework.web.method.support.InvocableHandlerMethod",
        "nativeMethod": false
      },
      {
        "methodName": "invokeAndHandle",
        "fileName": "ServletInvocableHandlerMethod.java",
        "lineNumber": 110,
        "className": "org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod",
        "nativeMethod": false
      },
      {
        "methodName": "invokeHandlerMethod",
        "fileName": "RequestMappingHandlerAdapter.java",
        "lineNumber": 817,
        "className": "org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter",
        "nativeMethod": false
      },
      {
        "methodName": "handleInternal",
        "fileName": "RequestMappingHandlerAdapter.java",
        "lineNumber": 731,
        "className": "org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter",
        "nativeMethod": false
      },
      {
        "methodName": "handle",
        "fileName": "AbstractHandlerMethodAdapter.java",
        "lineNumber": 85,
        "className": "org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter",
        "nativeMethod": false
      },
      {
        "methodName": "doDispatch",
        "fileName": "DispatcherServlet.java",
        "lineNumber": 959,
        "className": "org.springframework.web.servlet.DispatcherServlet",
        "nativeMethod": false
      },
      {
        "methodName": "doService",
        "fileName": "DispatcherServlet.java",
        "lineNumber": 893,
        "className": "org.springframework.web.servlet.DispatcherServlet",
        "nativeMethod": false
      },
      {
        "methodName": "processRequest",
        "fileName": "FrameworkServlet.java",
        "lineNumber": 968,
        "className": "org.springframework.web.servlet.FrameworkServlet",
        "nativeMethod": false
      },
      {
        "methodName": "doGet",
        "fileName": "FrameworkServlet.java",
        "lineNumber": 859,
        "className": "org.springframework.web.servlet.FrameworkServlet",
        "nativeMethod": false
      },
      {
        "methodName": "service",
        "fileName": "HttpServlet.java",
        "lineNumber": 622,
        "className": "javax.servlet.http.HttpServlet",
        "nativeMethod": false
      },
      {
        "methodName": "service",
        "fileName": "FrameworkServlet.java",
        "lineNumber": 844,
        "className": "org.springframework.web.servlet.FrameworkServlet",
        "nativeMethod": false
      },
      {
        "methodName": "service",
        "fileName": "HttpServlet.java",
        "lineNumber": 729,
        "className": "javax.servlet.http.HttpServlet",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 292,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "WsFilter.java",
        "lineNumber": 52,
        "className": "org.apache.tomcat.websocket.server.WsFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilterInternal",
        "fileName": "EndpointWebMvcAutoConfiguration.java",
        "lineNumber": 237,
        "className": "org.springframework.boot.actuate.autoconfigure.EndpointWebMvcAutoConfiguration$ApplicationContextHeaderFilter",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "OncePerRequestFilter.java",
        "lineNumber": 107,
        "className": "org.springframework.web.filter.OncePerRequestFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilterInternal",
        "fileName": "WebRequestTraceFilter.java",
        "lineNumber": 112,
        "className": "org.springframework.boot.actuate.trace.WebRequestTraceFilter",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "OncePerRequestFilter.java",
        "lineNumber": 107,
        "className": "org.springframework.web.filter.OncePerRequestFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilterInternal",
        "fileName": "RequestContextFilter.java",
        "lineNumber": 99,
        "className": "org.springframework.web.filter.RequestContextFilter",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "OncePerRequestFilter.java",
        "lineNumber": 107,
        "className": "org.springframework.web.filter.OncePerRequestFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilterInternal",
        "fileName": "HttpPutFormContentFilter.java",
        "lineNumber": 87,
        "className": "org.springframework.web.filter.HttpPutFormContentFilter",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "OncePerRequestFilter.java",
        "lineNumber": 107,
        "className": "org.springframework.web.filter.OncePerRequestFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilterInternal",
        "fileName": "HiddenHttpMethodFilter.java",
        "lineNumber": 77,
        "className": "org.springframework.web.filter.HiddenHttpMethodFilter",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "OncePerRequestFilter.java",
        "lineNumber": 107,
        "className": "org.springframework.web.filter.OncePerRequestFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilterInternal",
        "fileName": "CharacterEncodingFilter.java",
        "lineNumber": 121,
        "className": "org.springframework.web.filter.CharacterEncodingFilter",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "OncePerRequestFilter.java",
        "lineNumber": 107,
        "className": "org.springframework.web.filter.OncePerRequestFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilterInternal",
        "fileName": "MetricsFilter.java",
        "lineNumber": 103,
        "className": "org.springframework.boot.actuate.autoconfigure.MetricsFilter",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "OncePerRequestFilter.java",
        "lineNumber": 107,
        "className": "org.springframework.web.filter.OncePerRequestFilter",
        "nativeMethod": false
      },
      {
        "methodName": "internalDoFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 240,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "doFilter",
        "fileName": "ApplicationFilterChain.java",
        "lineNumber": 207,
        "className": "org.apache.catalina.core.ApplicationFilterChain",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "StandardWrapperValve.java",
        "lineNumber": 212,
        "className": "org.apache.catalina.core.StandardWrapperValve",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "StandardContextValve.java",
        "lineNumber": 106,
        "className": "org.apache.catalina.core.StandardContextValve",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "AuthenticatorBase.java",
        "lineNumber": 502,
        "className": "org.apache.catalina.authenticator.AuthenticatorBase",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "StandardHostValve.java",
        "lineNumber": 141,
        "className": "org.apache.catalina.core.StandardHostValve",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "ErrorReportValve.java",
        "lineNumber": 79,
        "className": "org.apache.catalina.valves.ErrorReportValve",
        "nativeMethod": false
      },
      {
        "methodName": "invoke",
        "fileName": "StandardEngineValve.java",
        "lineNumber": 88,
        "className": "org.apache.catalina.core.StandardEngineValve",
        "nativeMethod": false
      },
      {
        "methodName": "service",
        "fileName": "CoyoteAdapter.java",
        "lineNumber": 522,
        "className": "org.apache.catalina.connector.CoyoteAdapter",
        "nativeMethod": false
      },
      {
        "methodName": "process",
        "fileName": "AbstractHttp11Processor.java",
        "lineNumber": 1095,
        "className": "org.apache.coyote.http11.AbstractHttp11Processor",
        "nativeMethod": false
      },
      {
        "methodName": "process",
        "fileName": "AbstractProtocol.java",
        "lineNumber": 672,
        "className": "org.apache.coyote.AbstractProtocol$AbstractConnectionHandler",
        "nativeMethod": false
      },
      {
        "methodName": "doRun",
        "fileName": "NioEndpoint.java",
        "lineNumber": 1500,
        "className": "org.apache.tomcat.util.net.NioEndpoint$SocketProcessor",
        "nativeMethod": false
      },
      {
        "methodName": "run",
        "fileName": "NioEndpoint.java",
        "lineNumber": 1456,
        "className": "org.apache.tomcat.util.net.NioEndpoint$SocketProcessor",
        "nativeMethod": false
      },
      {
        "methodName": "runWorker",
        "fileName": "ThreadPoolExecutor.java",
        "lineNumber": 1145,
        "className": "java.util.concurrent.ThreadPoolExecutor",
        "nativeMethod": false
      },
      {
        "methodName": "run",
        "fileName": "ThreadPoolExecutor.java",
        "lineNumber": 615,
        "className": "java.util.concurrent.ThreadPoolExecutor$Worker",
        "nativeMethod": false
      },
      {
        "methodName": "run",
        "fileName": "TaskThread.java",
        "lineNumber": 61,
        "className": "org.apache.tomcat.util.threads.TaskThread$WrappingRunnable",
        "nativeMethod": false
      },
      {
        "methodName": "run",
        "fileName": "Thread.java",
        "lineNumber": 745,
        "className": "java.lang.Thread",
        "nativeMethod": false
      }
    ],
    "message": "You screwed up!",
    "localizedMessage": "You screwed up!",
    "suppressed": []
  },
  "timestamp": "Sun May 01 20:09:02 IST 2016",
  "path": "/rest/throw"
}
``
