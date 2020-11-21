package com.bold.sing.music.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
public class StoragePage {
	@Getter
	private String nextPageToken;
	private List<StorageResponse> elements;

	public boolean hasNextPage() {
		return nextPageToken != null;
	}

	public Iterable<StorageResponse> getValues() {
		return () -> elements.iterator();
	}
}
