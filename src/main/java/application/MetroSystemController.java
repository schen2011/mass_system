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
    public void start() {
    	//trigger metro system 
    }

	@Override
	public String getRoute(int startStopID, int destinationStopID) {
		// TODO Auto-generated method stub
		return "";
	}
    
    
    
}
