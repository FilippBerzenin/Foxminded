package com.berzenin.university.web;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupControllerOld.class)
public class GroupControllerIntegrationTest {
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private GroupService groupService;
//	
//	@MockBean
//	private GroupsRepository groupsRepository;
//	
//	private List<Group> allGroups = Arrays.asList(new Group(1, "first"), new Group(2, "second"));
//	
//	@Before
//	public void init() {
//		when(groupsRepository.findAll()).thenReturn(allGroups);
//		when(groupService.getGroupsRepository().findAll()).thenReturn(groupsRepository.findAll());
//		groupService.getGroupsRepository().findAll().forEach(System.out::println);
////		webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
////				.useMockMvcForHosts("univer.com")
////				.build();
//	}
//	
//	@SneakyThrows
//	@Test
//	public void groupControllerFindAllGroup () {
//		this.mockMvc.perform(get("/groups").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
//		.andExpect(status().isOk())
//		.andExpect(content().contentType("text/html;charset=UTF-8"))
//		.andExpect(content().string(allOf(
//				containsString("first"),
//				containsString("second")
//				)));
//	}

}
