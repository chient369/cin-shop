package com.cinshop;

public class AccessTrackStorage {
	private static Integer totalOfUserAccessing = 0;
	private static Integer guest = 0;
	private static Integer shopMember = 0;

	private static AccessTrackStorage storage = new AccessTrackStorage();

	private AccessTrackStorage() {
	}

	public static AccessTrackStorage getInstance() {
		return storage;
	}

	public void increaseGuest() {
		guest++;
		totalOfUserAccessing++;
	}

	public void increaseMember() {
		shopMember++;
		totalOfUserAccessing++;
	}

	public void decreaseGuest() {
		guest--;
		if (totalOfUserAccessing < 0) {
			totalOfUserAccessing = 0;
		} else {
			totalOfUserAccessing--;
		}
	}

	public void decreaseMember() {
		shopMember--;
		if (totalOfUserAccessing < 0) {
			totalOfUserAccessing = 0;
		} else {
			totalOfUserAccessing--;
		}
	}

	public static Integer getTotalOfUserAccessing() {
		return totalOfUserAccessing;
	}

	public static Integer getGuest() {
		return guest;
	}

	public static Integer getShopMember() {
		return shopMember;
	}
	

}
