/**
 * @author:Helen
 * @date：2018年4月20日 
 */
package jinengxia_WebUI.websiteTest;

import org.testng.annotations.Test;
import common.BaseData;
import common.BaseWindows;
import common.mysql_conn;
import jinengxia_WebUI.website_pages.TClass_page;
import jinengxia_WebUI.website_pages.TIndex_page;
import jinengxia_WebUI.website_pages.index_page;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
/**
 * 描述：教务工作台－课时管理：设置整个学习阶段的阅读课、录播课、作业、直播课
 */
@Test(groups="TClassManagerTest",dependsOnGroups="BCourseManagerTest")
public class TClassManagerTest {
	loginTest loginTest = new loginTest();
	WebDriver driver = loginTest.get_driver("helen_student01", "123456");
	BaseData bdata = new BaseData();
	mysql_conn mConn = new mysql_conn();
	BaseWindows windows = new BaseWindows();
	index_page index_page = PageFactory.initElements(driver, index_page.class);
	TIndex_page tIndex_page = PageFactory.initElements(driver, TIndex_page.class);
	TClass_page class_page = PageFactory.initElements(driver, TClass_page.class);
	String stageId;

	@BeforeClass
	public void beforeClass() {
		index_page.click_teacherSYSLink(driver);// 点击“教务工作台”
		windows.changeWindow(driver);// 窗口切换到教务工作台
		tIndex_page.click_courseAtLast();// 点击最后一个技能班
		tIndex_page.click_firstSchedule();// 点击第一个班期（列表最后）
		tIndex_page.click_classLink();// 第一个阶段的课时管理链接
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test(description = "导入课时列表",groups="firstStageClassManager")
	public void inputClassList() throws IOException, InterruptedException {
		class_page = PageFactory.initElements(driver, TClass_page.class);
		String firePath = bdata.getFilePath("testFile/classList.xlsx");
		System.out.println(firePath);
		class_page.sendkeys_inputFile(firePath);// 不知何故，这里不能用相对路径，必须用绝对路径，所以要转换一下取得绝对路径才能上传上功
		this.stageId = bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);
		Thread.sleep(2000);
		String periodCount = mConn
				.getData("SELECT COUNT(*)as periodCount FROM stage_period where stage_id=" + stageId, "periodCount")
				.get(0);
		assertEquals(Integer.parseInt(periodCount), 8);
		//Thread.sleep(2000);
	}

	@Test(description = "编辑阅读课时的内容", dependsOnMethods = "inputClassList",groups="firstStageClassManager")
	public void readMassageEdit() throws IOException {
		//String stageID = bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);// 获取当前阶段名称
		List<String> classList = mConn.getData("SELECT id from stage_period where type=1 and stage_id=" + this.stageId,
				"id");
		for (int i = 0; i < classList.size(); i++) {
			this.editorDo(driver, "阅读课导读内容：" , classList.get(i));//插入导读内容、上传附件
			class_page.click_submitBTN();// 保存
			String periodCount = (mConn
					.getData("SELECT COUNT(*) as periodCount from period_reading where period_id="+classList.get(i), "periodCount"))
							.get(0);
			assertTrue(Integer.parseInt(periodCount) == 1);
		}
	}
	
	@Test(description="编辑录播课时的内容", dependsOnMethods = "inputClassList",groups="firstStageClassManager")
	public void videoMassage() throws IOException {
		//String stageID = bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);// 获取当前阶段名称
		List<String> classList = mConn.getData("SELECT id from stage_period where type=2 and stage_id=" + this.stageId,
				"id");
		for (int i = 0; i < classList.size(); i++) {
			this.editorDo(driver, "录播课导读内容：" , classList.get(i));//插入导读内容、上传附件
			class_page.sendkeys_videoURL("http://player.polyv.net/videos/player.swf?vid=bbbc24e93bd2a5a2def4f6adf6cce4ba_b");//输入视频链接
			class_page.click_submitBTN();// 保存
			String periodCount = (mConn
					.getData("SELECT COUNT(*) as periodCount from period_video where period_id="+classList.get(i), "periodCount"))
							.get(0);
			assertTrue(Integer.parseInt(periodCount) == 1);
		}
	}
	
	@Test(description="编辑作业课时的内容", dependsOnMethods = "inputClassList",groups="firstStageClassManager")
	public void homeWordMassageEdit() throws IOException {
		//String stageID= bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);// 获取当前阶段名称
		List<String> classList = mConn.getData("SELECT id from stage_period where type=3 and stage_id=" + this.stageId, "id");
		for (int i = 0; i < classList.size(); i++) {
			this.editorDo(driver, "作业要求导读内容：" , classList.get(i));//插入导读内容、上传附件
			class_page.click_submitBTN();// 保存
			String periodCount = (mConn
					.getData("SELECT COUNT(*) as periodCount from period_task where period_id="+classList.get(i), "periodCount"))
							.get(0);
			assertTrue(Integer.parseInt(periodCount) == 1);
		}
	}
	
	@Test(description="编辑直播课时的内容", dependsOnMethods = "inputClassList",groups="firstStageClassManager")
	public void liveMassageEdit() throws IOException {
		//String stageID= bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);// 获取当前阶段名称
		List<String> classList = mConn.getData("SELECT id from stage_period where type=4 and stage_id=" + this.stageId, "id");
		for (int i = 0; i < classList.size(); i++) {
			this.editorDo(driver, "直播课导读内容：" , classList.get(i));//插入导读内容、上传附件
			class_page.sendkeys_videoURL("http://player.polyv.net/videos/player.swf?vid=bbbc24e93bd2a5a2def4f6adf6cce4ba_b");//输入视频链接
			class_page.click_submitBTN();// 保存
			String periodCount = (mConn
					.getData("SELECT COUNT(*) as periodCount from period_live where period_id="+classList.get(i), "periodCount"))
							.get(0);
			assertTrue(Integer.parseInt(periodCount) == 1);
		}
	}
	
	@Test(description="第二个阶段的所有课时操作",dependsOnGroups="firstStageClassManager")
	public void secondStageClassManager() throws IOException, InterruptedException {
		tIndex_page.click_firstSchedule();// 点击面包屑导航：班期
		tIndex_page.click_secondClassLink();//第二个阶段的课时管理链接
		this.inputClassList();//导入课时列表
		this.readMassageEdit();//阅读课内容编辑
		this.liveMassageEdit();//直播课内容
		this.videoMassage();//录播课内容
		this.homeWordMassageEdit();//作业内容
	}
	
	@Test(description="第二个班期，一键复制课时",dependsOnMethods="secondStageClassManager")
	public void secondScheduleManager() throws InterruptedException {
		tIndex_page.click_secondSchedule();//点击第二个班期
		tIndex_page.click_classLink();//第一个课时管理链接
		class_page.click_copyClassBTN();//一键复制按钮
		class_page.click_copyBTN();//复制按钮
		this.stageId = bdata.getTargetList(driver.getCurrentUrl(), "\\d+").get(0);
		System.out.println(stageId);
		Thread.sleep(2000);
		String periodCount = mConn
				.getData("SELECT COUNT(*)as periodCount FROM stage_period where stage_id=" + stageId, "periodCount")
				.get(0);
		assertEquals(Integer.parseInt(periodCount), 8);
	}
	
	/*内容管理页面的公共控件操作：导读内容、附件上传*/
	private void editorDo(WebDriver driver,String massageTile,String classID) throws IOException {
		class_page.sendkeys_searchWords(classID);
		class_page.click_searchBTN();// 查询按钮
		class_page.click_editBTN();// 内容管理按钮
		class_page.sendkeys_editor(driver, massageTile+classID);// 输入内容
		class_page.sendkeys_inputMassageFile(bdata.getFilePath("testFile/测试用导入.rar"));// 导入rar文件
		class_page.sendkeys_inputMassageFile(bdata.getFilePath("testFile/env.tar"));// 导入tar文件
		class_page.sendkeys_inputMassageFile(bdata.getFilePath("testFile/interface_demo.zip"));// 导入zip文件
	}
}
