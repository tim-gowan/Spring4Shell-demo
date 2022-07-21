package net.javaguides.springmvc.helloworld.controller;

import java.time.LocalDateTime;
import net.javaguides.springmvc.helloworld.model.HelloWorld;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


/**
 * @author Ramesh Fadatare
 */
@Controller
public class HelloWorldController {

	@RequestMapping("/helloworld")
	public void handler(HelloWorld model) {

		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setMessage("Hello World Example Using Spring MVC 5!!!");
		//helloWorld.setDateTime(LocalDateTime.now().toString());
		//model.addAttribute("helloWorld", helloWorld);
		//return "helloworld";
	}
}