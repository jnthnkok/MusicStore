package com.jonathan.startup.services.Impl;
import com.jonathan.startup.services.GradeEnum;
import com.jonathan.startup.services.GradingServiceHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Kok
 */
@Service
public class GradingServiceHandlerB extends GradingServiceHandler{

    @Override
    public String handleRequest(int request) {
        if(request < 200 || request > 300){
            if(successor != null){
                successor.handleRequest(request);
            }
        }

       return "B";
    }
    
}
