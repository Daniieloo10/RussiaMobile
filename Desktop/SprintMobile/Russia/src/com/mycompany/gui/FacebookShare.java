/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
/**
 *
 * @author boussandel
 */
public class FacebookShare {
     private static String token = "EAACEdEose0cBAHruXEuxcEjGM6ZCJsYy4pJDbYeiqBwlDsutjCGXfPBZCAo7Y9SmBZBB9MP8EdM1ZB7zyxHQWBEQmdbeQuOc3wZBzx5nRAZBq3IY5u2kPI23ZBTqCuwvNUKdCNRKh0eVGHdKyZATzVKqPzRO7BIlLYl3sFoe7iDGhZAaYfHt3Hi4W7aL5E7tXUsAOJZAwvcjcZBKwZDZD";

    public FacebookShare(String token) {
        FaceBookAccess.setToken(token);
    }
    
    public void share(String text) throws IOException {
	FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent evt) {
		NetworkEvent ne = (NetworkEvent) evt;
		int code = ne.getResponseCode();
		FaceBookAccess.getInstance().removeResponseCodeListener(this);
	    }
/*
            @Override
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }*/
	});
	FaceBookAccess.getInstance().postOnWall("me", text);
    }
}
