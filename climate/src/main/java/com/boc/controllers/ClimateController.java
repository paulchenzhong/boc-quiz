package com.boc.controllers;

import com.boc.entities.Climate;
import com.boc.entities.DateRange;
import com.boc.repositories.ClimateRepository;
import com.boc.services.ClimateService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClimateController {

    @Autowired
    ClimateRepository climateRepository;
    
    @Autowired
	private ClimateService climateService;

    /*@GetMapping("/")
    public String showPage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("data",
                climateRepository.findAll(PageRequest.of(page, 6)));
        model.addAttribute("currentPage", page);
        return "index";
    }*/
    
    
    
    // display list of employees
 	@GetMapping("/")
 	public String viewHomePage(Model model, String keyword) {
 		
 		System.out.println("****************"+keyword);
 		if (keyword == null) {
 			return findPaginated(1, "stationName", "asc", model);
 		}
 		else {
 			return findPaginated(1, "stationName", "asc", model, keyword);
 		}
 				
 	}

    @GetMapping("/findOne")
    @ResponseBody
    public Climate findOne(long id) {
        return climateRepository.findById(id).get();
    }
    
    @GetMapping("/view")
    public String view(@PathVariable(name = "id") long id, Model model) {
    	model.addAttribute("data",
    			climateRepository.findById(id));
        return "view";
    }
    
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
    	model.addAttribute("data",
    			climateRepository.findById(id));
        return "view_climate";
    }
    
    @GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get climate record from the service
		Climate climate = climateService.getClimateById(id);
		
		// set climate as a model attribute to pre-populate the form
		model.addAttribute("climate", climate);
		return "view";
	}
    
    @PostMapping("/save")
    public String save(Climate climate) {
        climateRepository.save(climate);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteClimate(long id) {
        climateRepository.deleteById(id);
        return "redirect:/";
    }
    
    @GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 8;
		Page<Climate> page = climateService.findPaginated(pageNo, pageSize, sortField, sortDir);;
		
		
		List<Climate> listClimates = page.getContent();
        
		System.out.println("Total pages: "+page.getTotalPages());

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listClimates", listClimates);
		return "index";
	}
    
    @GetMapping("/page/{pageNo}/{keyword}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model,
			String keyword) {
		int pageSize = 8;
		Page<Climate> page;
		
		if (keyword != null) {
			page = climateService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);
		}
		else {
			page = climateService.findPaginated(pageNo, pageSize, sortField, sortDir);
		}
		
		List<Climate> listClimates = page.getContent();
		
		// filter out the date range
		List<Climate> result = new ArrayList();
		
		
		DateRange dateRange = new DateRange();
        dateRange.setDateFrom(new Date());
        dateRange.setDateTo(new Date());
        model.addAttribute("dateRange", dateRange);
        
        for (Climate climate:listClimates) {
        	if (climate.getStationName() == "CHEMAINUS") {
        		result.add(climate);
        	}
        }
        
		System.out.println("Total pages: "+page.getTotalPages());

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listClimates", result);
		return "index";
	}
    
}
