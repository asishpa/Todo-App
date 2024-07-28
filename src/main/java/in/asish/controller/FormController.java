package in.asish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.asish.entity.Task;
import in.asish.entity.User;
import in.asish.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class FormController {
	
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/")
	public String loadHomePage() {
		return "index";
	}
	@GetMapping("/register")
	public String loadRegPage() {
		return "register";
	}
	@GetMapping("/login")
	public String loadLoginPage() {
		return "login";
	}
	@PostMapping("/register")
	public String processRegPage(Model model,User user) {
		System.out.println(user);
		userRepository.save(user);
		model.addAttribute("msg", "Registration Successful");
		return "register";
	}
	@PostMapping("/login")
	public String processLoginPage(@ModelAttribute User user,Model model,HttpSession session) {
		System.out.println(user);
		User existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser == null || !existingUser.getPwd().equals(user.getPwd()))
		{
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
			session.setAttribute("user", existingUser);
			return "redirect:/dashboard";
	
	}
	@PostMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}

}
