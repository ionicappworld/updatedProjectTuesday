package com.rabobank.controller;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rabobank.factory.StatementFactoryInterface;
import com.rabobank.processor.StatementProcessor;

@RunWith(MockitoJUnitRunner.class)
public class StatementProcessorControllerTest {

	private InputStream statementStream;
	private MockMvc mockMvc;
	private static final String TEST_FILE_NAME = "records.csv";

	@Mock
	StatementProcessor statementProcessor;

	@Mock
	StatementFactoryInterface statementFactory;

	@Spy
	@InjectMocks
	private StatementProcessorController controller = new StatementProcessorController();

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		statementStream = controller.getClass().getClassLoader().getResourceAsStream(TEST_FILE_NAME);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getValidatedStatementTest() throws Exception {
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", TEST_FILE_NAME, "multipart/form-data",
				statementStream);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.fileUpload("/rabo/getValidatedStatement").file(mockMultipartFile)
						.contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
		Assert.assertNotNull(result.getResponse().getContentAsString());
		Assert.assertEquals(TEST_FILE_NAME, result.getResponse().getContentAsString());
	}
}
