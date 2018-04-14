package application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MetroSystemController implements MetroSystemActions {
    @GetMapping("/metrosystem")
    public String metrosystem(@RequestParam(name="name", 
    	required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "metrosystem";
    }
    
    @GetMapping("/admin") 
    public String admin() {
    	return "admin";
    }
    
    @GetMapping("/client") 
    public String client() {
    	return "client";
    }

	@Override
	public String getRoute(int startStopID, int destinationStopID) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
    
    
    
}
