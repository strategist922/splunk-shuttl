// Copyright (C) 2011 Splunk Inc.
//
// Splunk Inc. licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.splunk.shuttl.archiver.archive;

import static org.testng.Assert.*;

import java.io.File;

import javax.management.InstanceNotFoundException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.splunk.shuttl.server.mbeans.ShuttlArchiver;
import com.splunk.shuttl.server.mbeans.ShuttlArchiverMBean;
import com.splunk.shuttl.server.mbeans.util.MBeanUtils;
import com.splunk.shuttl.server.mbeans.util.RegistersMBeans;
import com.splunk.shuttl.testutil.TUtilsConf;

@Test(groups = { "fast-test" })
public class RegistersArchiverMBeanTest {

	private RegistersMBeans registersMBeans;
	private ShuttlArchiverMBean mBean;
	private RegistersArchiverMBean registersArchiverMBean;

	@BeforeMethod
	public void setUp() {
		File confsDir = TUtilsConf.getNullConfsDir();
		mBean = ShuttlArchiver.createWithConfDirectory(confsDir);
		registersMBeans = RegistersMBeans.create();
		registersArchiverMBean = new RegistersArchiverMBean(registersMBeans, mBean);
	}

	@AfterMethod
	public void tearDown() {
		registersMBeans.unregisterMBean(registersArchiverMBean.getName());
	}

	public void getName_givenNothing_getsTheArchiverMBeanObjectName() {
		assertEquals(ShuttlArchiverMBean.OBJECT_NAME,
				registersArchiverMBean.getName());
	}

	public void register_givenMBean_registersMBean() throws Exception {
		registersArchiverMBean.register();
		ShuttlArchiverMBean mBeanInstance = MBeanUtils.getMBeanInstance(
				registersArchiverMBean.getName(), ShuttlArchiverMBean.class);
		assertNotNull(mBeanInstance);
	}

	@Test(expectedExceptions = { InstanceNotFoundException.class })
	public void unregister_givenRegisteredMBean_getsExceptionIfAttemptedToGetMBeanInstance()
			throws Exception {
		registersArchiverMBean.register();
		registersArchiverMBean.unregister();
		MBeanUtils.getMBeanInstance(registersArchiverMBean.getName(),
				ShuttlArchiverMBean.class);
	}

	public void unregister_beforeRegistering_doesNothing() {
		registersArchiverMBean.unregister();
	}

}
