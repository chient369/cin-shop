package com.cinshop.notification;

import java.util.HashMap;
import java.util.Map;

public class NotifyDTO {
	private NotifyType type;
	private Map<String, Object> info;

	public enum NotifyType {
		ORDER, USER
	}

	public NotifyDTO() {
		this.info = new HashMap<>();
	}

	public NotifyDTO(NotifyType type, Map<String, Object> info) {
		super();
		this.type = type;
		this.info = info;
	}

	public NotifyType getType() {
		return type;
	}

	public void setType(NotifyType type) {
		this.type = type;
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

}
