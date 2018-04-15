package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MetroSystemController implements MetroSystemActions {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(path="/metrosystem",  method = RequestMethod.GET)
    public String metrosystem(@RequestParam(name="name", 
    	required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "metrosystem";
    }
    
    @RequestMapping(path="/admin", method = RequestMethod.GET) 
    public String admin() {
    	return "admin";
    }
    
    @RequestMapping(path="/client", method = RequestMethod.GET) 
    public String client() {
    	return "client";
    }
    
    @RequestMapping(path="/fetchAdminData", method = RequestMethod.GET) 
    @ResponseBody
    public Iterable<UserData> getAdminUsers() {
    	return userRepository.findAll();
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
