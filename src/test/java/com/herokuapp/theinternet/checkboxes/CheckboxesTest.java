package com.herokuapp.theinternet.checkboxes;

import com.herokuapp.data.DataEntity;
import com.herokuapp.data.DataEntityDescription;
import com.herokuapp.data.DataEntityProvider;
import com.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class CheckboxesTest extends GauntletTest {

    @DataProvider
    public static Object[][] scenarios() {
        return new Object[][]{
                {DataEntityDescription.getInstance().withCheckbox1Selections(2).withCheckbox2Selections(0)},
                {DataEntityDescription.getInstance().withCheckbox1Selections(3).withCheckbox2Selections(0)},
                {DataEntityDescription.getInstance().withCheckbox1Selections(8).withCheckbox2Selections(0)},
                {DataEntityDescription.getInstance().withCheckbox1Selections(0).withCheckbox2Selections(1)},
                {DataEntityDescription.getInstance().withCheckbox1Selections(3).withCheckbox2Selections(2)},
                {DataEntityDescription.getInstance().withCheckbox1Selections(6).withCheckbox2Selections(6)}
        };
    }

    @Test(groups = TestType.EVT)
    public void smoke() {
        DataEntityDescription dataEntityDescription = DataEntityDescription.getInstance().withCheckbox1Selections(0).withCheckbox2Selections(0);
        DataEntity testData = DataEntityProvider.getInstance().get(dataEntityDescription);
        CheckboxViewExpected expected = CheckboxViewExpected.getInstance(testData.getCheckbox1Selections(), testData.getCheckbox1Selections());
        CheckboxView actual = CheckboxView.directNav();
        confirm(CheckboxValidator.getInstance(expected, actual).validate());
    }

    @Test(groups = TestType.SPRINT, dependsOnMethods = "smoke", dataProvider = "scenarios")
    public void selectCheckboxes(DataEntityDescription dataScenario) {
        DataEntity testData = DataEntityProvider.getInstance().get(dataScenario);
        Integer checkbox1Selections = testData.getCheckbox1Selections();
        Integer checkbox2Selections = testData.getCheckbox2Selections();
        CheckboxViewExpected expected = CheckboxViewExpected.getInstance(checkbox1Selections, checkbox2Selections);
        CheckboxView actual = CheckboxView.directNav().selectCheckbox(1, checkbox1Selections).selectCheckbox(2, checkbox2Selections);
        confirm(CheckboxValidator.getInstance(expected, actual).validate());
    }
}