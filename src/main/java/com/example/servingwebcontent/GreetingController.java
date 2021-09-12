package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class GreetingController {

		@GetMapping("/authorization")
		String index(Principal principal) {
			return "authorization";
		}

	}
