package com.morendi.Oola;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OolaImageLoader {

	private String imagePath;
	public OolaImageLoader(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public Image load(String name) throws IOException {
		Image img = ImageIO.read(new File(imagePath + "/" + name));
		return img;
	}
}
