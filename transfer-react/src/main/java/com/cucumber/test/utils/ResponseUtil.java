package com.cucumber.test.utils;



import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ResponseUtil {
    
    public static PrintWriter getPrintWriter(HttpServletResponse response){
        PrintWriter out = null;
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
        } catch (IOException e) {
            
        }
        return out;
    }

    public static void print (PrintWriter out, String data){        
        out.print(data);
        out.flush();
        out.close();
    }    
}
