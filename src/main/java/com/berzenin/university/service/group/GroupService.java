package com.berzenin.university.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berzenin.university.dao.GroupsRepository;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class GroupService {
	
	@Autowired
	private GroupsRepository groupsRepository;

}
