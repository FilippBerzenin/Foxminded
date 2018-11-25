package com.berzenin.university.service.group;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GroupsView implements Comparable<GroupsView> {
	
	private long id;
	private String nameOfGroup;
	private String formOfCRUD;
	@Override
	public int compareTo(GroupsView o) {
		return nameOfGroup.compareTo(o.nameOfGroup);
	}
}
