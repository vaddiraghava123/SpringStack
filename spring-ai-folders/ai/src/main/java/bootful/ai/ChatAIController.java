package bootful.ai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatAIController {

	@RequestMapping("/")
	public ModelAndView getMessage() {
		return new ModelAndView("welcome");
		
	}
}
