package com.sabulous.contractRateManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.sabulous.contractRateManager.config.MainConfig;
import com.sabulous.contractRateManager.config.ThymeleafConfig;
import com.sabulous.contractRateManager.config.WebSecurityConfig;
import com.sabulous.contractRateManager.controllers.CRUDController;
import com.sabulous.contractRateManager.controllers.MainController;
import com.sabulous.contractRateManager.controllers.UserController;
import com.sabulous.contractRateManager.repositories.ContractRepository;
import com.sabulous.contractRateManager.repositories.RoleRepository;
import com.sabulous.contractRateManager.repositories.UserRepository;
import com.sabulous.contractRateManager.services.ContractService;
import com.sabulous.contractRateManager.services.EmailService;
import com.sabulous.contractRateManager.services.EncryptionService;
import com.sabulous.contractRateManager.services.IUserDetailsService;
import com.sabulous.contractRateManager.services.RoleService;
import com.sabulous.contractRateManager.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractRateManagerApplicationTests {

	@Autowired
	MainController mainController;

	@Autowired
	CRUDController crudController;

	@Autowired
	UserController userController;

	@Autowired
	MainConfig mainConfig;

	@Autowired
	ThymeleafConfig thymeleafConfig;

	@Autowired
	WebSecurityConfig webSecurityConfig;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ContractService contractService;

	@Autowired
	EmailService emailService;

	@Autowired
	EncryptionService encryptionService;

	@Autowired
	RoleService roleService;

	@Autowired
	IUserDetailsService userDetailsService;

	@Autowired
	UserService userService;

	@Test
	public void contextLoadTest() throws Exception {
		//using AssertJ
		assertThat(mainController).isNotNull();
		assertThat(crudController).isNotNull();
		assertThat(userController).isNotNull();

		assertThat(mainConfig).isNotNull();
		assertThat(thymeleafConfig).isNotNull();
		assertThat(webSecurityConfig).isNotNull();

		assertThat(contractRepository).isNotNull();
		assertThat(roleRepository).isNotNull();
		assertThat(userRepository).isNotNull();

		assertThat(contractService).isNotNull();
		assertThat(emailService).isNotNull();
		assertThat(encryptionService).isNotNull();
		assertThat(roleService).isNotNull();
		assertThat(userDetailsService).isNotNull();
		assertThat(userService).isNotNull();

		//using JUnit
		assertNotNull(mainController);
		assertNotNull(crudController);
		assertNotNull(userController);
		
		assertNotNull(mainConfig);
		assertNotNull(thymeleafConfig);
		assertNotNull(webSecurityConfig);

		assertNotNull(contractRepository);
		assertNotNull(roleRepository);
		assertNotNull(userRepository);

		assertNotNull(contractService);
		assertNotNull(emailService);
		assertNotNull(encryptionService);
		assertNotNull(roleService);
		assertNotNull(userDetailsService);
		assertNotNull(userService);
	}

}
