package org.wikipedia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlphaSearchTest.class,
        AlphaJumpingSectionsTest.class,
        AlphaSearchKeywordsTest.class,
        AlphaGeneralAppSettingsTest.class,
        AlphaManageReadingListTest.class
})
public class EspressoTestSuite {
}
