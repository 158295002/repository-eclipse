package com.evada.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class ImageRandW {

	public static void main(String[] args) {
		String fileName = "C:\\Users\\Administrator\\Desktop\\restart.png";
		String savePath = "C:\\Users\\Administrator\\Desktop\\saveImage\\restart.png";
		ImageLoader loader = new ImageLoader();
		ImageData[] imageData = loader.load(fileName);
		if (imageData.length > 0) {
			Image newImage = new Image(null, imageData[0]);
			// 对newImage进行操作
			loader.data[0] = newImage.getImageData();
			loader.save(savePath, SWT.IMAGE_BMP);
		}
	}
}
