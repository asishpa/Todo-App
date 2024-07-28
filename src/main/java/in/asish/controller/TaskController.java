package in.asish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.asish.entity.Task;
import in.asish.entity.User;
import in.asish.repository.TaskRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/dashboard")
	public String viewTask(Model model,HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return "redirect:/login";
		}
		model.addAttribute("tasks", taskRepository.findByUserUid(user.getUid()));
		return "dashboard";
	}
	
	@GetMapping("/add")
	public String addTask(Task task) {
		return "addTask";
	}
	@PostMapping("/save")
	public String saveTask(Model model,Task task,HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return "redirect:/login";
		}
		System.out.println(task);
		task.setUser(user);
		taskRepository.save(task);
		model.addAttribute("msg", "task saved successfully");
		return "addTask";
	}
	@PostMapping("/task/status")
	public String status(@RequestParam Integer id,@RequestParam String status,HttpSession session,Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		Task task = (Task)taskRepository.findByUserUidAndId(user.getUid(), id);
		if(task!=null) {
			task.setStatus("completed");
			taskRepository.save(task);
			model.addAttribute("tasks",taskRepository.findByUserUid(user.getUid()));
		}
		return "dashboard :: taskTable";
	}
}
