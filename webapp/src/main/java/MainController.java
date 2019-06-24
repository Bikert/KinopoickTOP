import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bikert.test_task.connections.DataBaseMovieService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/main")
public class MainController {

    //protected static Logger logger = Logger.getLogger(MovieDataController.class);

    @Resource(name="ru.bikert.test_task.connections.DataBaseMovieService")
    private DataBaseMovieService dataBaseMovieService;


}
