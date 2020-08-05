package com.bold.sing.music.storage;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Builder
@Value
@ToString
public class StorageResponse {
	private String storageId;
	private String selfLink;
	private String contentType;
	private String md5Checksum;
	// the size in bytes
	private Long fileSize;
}
