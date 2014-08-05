package com.jonathan.startup.services.Impl;
import com.jonathan.startup.services.GradeEnum;
import com.jonathan.startup.services.GradingServiceHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Kok
 */
@Service
public class GradingServiceHandlerC extends GradingServiceHandler{

    @Override
    public String handleRequest(int request) {
        if(request < 100 || request > 300){
            if(successor != null){
                successor.handleRequest(request);
            }
        }

       return "C";
    }
    
}
