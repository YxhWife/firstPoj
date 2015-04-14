package com.zl.service;

public interface ActiveStrategy {

	public Long[] generate(long total, int count);
}
