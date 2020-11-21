package com.bold.sing.music.file.control;

import java.io.InputStream;

public interface FileWrapper {

	InputStream getInputStream();

	String getOriginalFilename();

	String getContentType();
}
