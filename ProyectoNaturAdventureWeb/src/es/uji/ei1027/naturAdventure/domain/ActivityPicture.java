package es.uji.ei1027.naturAdventure.domain;

import org.springframework.web.multipart.MultipartFile;

public class ActivityPicture {
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
