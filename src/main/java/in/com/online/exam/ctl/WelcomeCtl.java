package in.com.online.exam.ctl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WelcomeCtl extends BaseCtl {


	
	 @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	    public String welcome(Model model) {
	        return "welcome";
	    }
	 
	 @RequestMapping(value = "/ctl/time", method = RequestMethod.GET)
	    public String time(Model model) {
	        return "time";
	    }
	
	 
	 
}
