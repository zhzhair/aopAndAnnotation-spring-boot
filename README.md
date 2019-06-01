# aopAndAnnotation-spring-boot
spring boot的面向切面编程AOP，注解，自定义注解，xss过滤，拦截器，异步调用，跨域配置，swagger等。
  1.集成swagger: 对于做前后端分离的项目，后端只需要提供接口访问，swagger提供了接口调用测试和
	各种注释的可视化web界面。配置swagger的扫描包路径，api信息等，见配置类SwaggerConfig。项目中遇到的
	下列注解都由swagger提供：@Api、@ApiOperation、@ApiModel、@ApiModelProperty访问http://localhost/swagger-ui.html#/就可以看到swagger文档，输入入参，点击try it out调接口运行；
	2.自定义注解@TokenValidate实现了用户是否登录的校验，必须登录的接口加此注解；
	3.拦截器RequestTimeConsumingInterceptor实现了接口耗时统计；
	4.拦截器RepeatSubmitInterceptor实现了重复提交的校验；
	5.跨域请求的注解@CrossOrigin（当然也可以写一个全局的过滤器）实现前后端分离后的POST请求，详情见类UserController.class。
	6.类GlobalExceptionHandler实现了全局异常处理；
	7.异步调用：UserController.class中登录和注册接口提供了异步操作的功能。只需要在异步调用的方法添加注解
	@Async和在启动类加注解@EnableAsync，启动项目并调接口查看执行时间可以看到异步调用已经生效；
