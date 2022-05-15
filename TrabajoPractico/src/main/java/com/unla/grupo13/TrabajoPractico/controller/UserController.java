package com.unla.grupo13.TrabajoPractico.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo13.TrabajoPractico.entities.User;
import com.unla.grupo13.TrabajoPractico.entities.UserRole;
import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.UserModel;
import com.unla.grupo13.TrabajoPractico.services.IRolService;
import com.unla.grupo13.TrabajoPractico.services.IUserService;



	@Controller
	@RequestMapping("/user")
	public class UserController {
		
		@Autowired
		@Qualifier("userService")
		private IUserService userService;
		
		@Autowired
		@Qualifier("rolService")
		private IRolService rolService;
		
		private ModelMapper modelMapper= new ModelMapper();
		
		
		@GetMapping("")
		public ModelAndView index() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_INDEX);
			return mAV;
		}
		
		@GetMapping("/registro")
		public ModelAndView create() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
			mAV.addObject("user", new UserModel());
			return mAV;
		}
		
		@GetMapping("/exito")
		public ModelAndView exito() {
			
			ModelAndView mAV= new ModelAndView (ViewRouteHelper.EXITO);
			return mAV;
		}
		
		@PostMapping("/create")
		public RedirectView create(@ModelAttribute("user") UserModel userModel) {
			UserRole role=rolService.findById(1);
			userModel.setRole(role);
			userService.save(modelMapper.map(userModel, User.class));
			return new RedirectView(ViewRouteHelper.USER_ROOT);
		}
	
		@GetMapping("/asistente")
		public ModelAndView asistente() {
			
			ModelAndView mAV= new ModelAndView (ViewRouteHelper.USER_ASISTENTE);
			return mAV;
		}
		
		
		
		@PostMapping ("/asistente/create")
		public RedirectView createAsistente(@ModelAttribute ("user")UserModel userModel) {
			
			UserRole role=rolService.findByRole("USER_ASISTENTE");
			userModel.setRole(role);
			userService.save(modelMapper.map(userModel, User.class));
			return new RedirectView(ViewRouteHelper.USER_ASISTENTE);
			
			
		}
		
		
}
