package com.pk.utils;

import java.util.UUID;

public final class CodecUtil {
	private CodecUtil() {}
	
	public static String createUUID() {
		return UUID.randomUUID().toString();
	}
}
