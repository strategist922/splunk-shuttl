package com.splunk.shep.mapreduce.lib.rest;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class MapRedRestTestConstants {

    public static final String TEST_RESOURCES_PATH = "test/java/com/splunk/shep/mapreduce/lib/rest";

    @Test(groups = { "fast-unit" })
    public void testResourcesPathConstant_should_equal_theFolderOfThePackageWhereThisClassLives() {
	assertEquals(TEST_RESOURCES_PATH,
		"test/java/com/splunk/shep/mapreduce/lib/rest");
    }
}