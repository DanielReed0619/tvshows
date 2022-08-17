package com.dr.beltexam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dr.beltexam.models.LoginUser;
import com.dr.beltexam.models.TvShow;
import com.dr.beltexam.models.User;
import com.dr.beltexam.services.TvShowService;
import com.dr.beltexam.services.UserService;
    
@Controller
public class MainController {
    
    @Autowired
	private final TvShowService tvServ;
	private final UserService userServ;
	public MainController(UserService userServ,
			TvShowService tvServ
			) {
		this.tvServ = tvServ;
		this.userServ = userServ;
	}
    
	
	
	
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    
    
    @RequestMapping("/home")
    public String home(@ModelAttribute("tvShow") TvShow tvShow,
    		Model model,
    		HttpSession session) {
    	Long userId = (Long) session.getAttribute("user_id");
    	if(userId == null) {
    		return "redirect:/";
    	}
    	User user = userServ.findUser(userId);
    	model.addAttribute(user);
    	model.addAttribute("tvShows", tvServ.allTvShows());
    	return "home.jsp";
    }
    
    
    @RequestMapping("/shows/new")
    public String add(@ModelAttribute("tvShow") TvShow tvShow,
    		HttpSession session
    		) {
    	Long userId = (Long) session.getAttribute("user_id");
    	if(userId == null) {
    		return "redirect:/";
    	}
    	return "add.jsp";
    }
    
    //======================================
    
    @RequestMapping(value="/create/show", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("tvShow") TvShow tvShow, BindingResult result) {
    	if(result.hasErrors()) {
    		return "add.jsp";
    	}
    	else {
    		tvServ.createTvShow(tvShow);
    		return "redirect:/home";
    	}
    }
    
    
	@RequestMapping("/edit/{id}")
	public String edit(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
    	if(userId == null) {
    		return "redirect:/";
    	}
		TvShow tvShow = tvServ.findShow(id);
		model.addAttribute("tvShow", tvShow);
		return "edit.jsp";
	}
	
	
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("tvShow") TvShow tvShow, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}
		else {
			tvServ.createTvShow(tvShow);
			return "redirect:/home";
		}
	}
	
	@RequestMapping("/show/{id}")
    public String showOne(@ModelAttribute("tvShow") TvShow tvShow,
    		Model model,
    		@PathVariable("id") Long id,
    		HttpSession session) {
    	Long userId = (Long) session.getAttribute("user_id");
     	if(userId == null) {
    		return "redirect:/";
    	}
     	TvShow Show = tvServ.findShow(id);
    	model.addAttribute("user", Show.getUser());
    	model.addAttribute("tvShows", Show);
    	return "show.jsp";
    }
    


	@RequestMapping("/logout")
	public String logOut(
			HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		tvServ.deleteShow(id);
		return "redirect:/home";
	}

}
