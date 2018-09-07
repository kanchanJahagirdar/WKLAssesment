package businessComponent;

import org.openqa.selenium.By;

public class elementSelector {
	protected By selectBy;
	protected String selectMe;
	protected String changedSelectMe;
	protected String desc;

	protected elementSelector(By selectBy,String selectMe, String desc) {
		this.selectBy = selectBy;
		this.selectMe=selectMe;
		//this.changedSelectMe=selectMe;
		this.desc = desc;
	}

	public static elementSelector byId(String selectMe) {
		return elementSelector.byId(selectMe,null);

	
	}
	public static elementSelector byId(String selectMe, String desc) {
		return new elementSelector(By.id(selectMe),selectMe, desc);
	}
	public static elementSelector byName(String selectMe) {
		return elementSelector.byName(selectMe,null);
	}
	public static elementSelector byName(String selectMe, String desc) {
		return new elementSelector(By.name(selectMe),selectMe, desc);
	}
	public static elementSelector byXPath(String selectMe) {
		return elementSelector.byXPath(selectMe,null);
	}
	
	public static elementSelector byXPath(String selectMe, String desc) {
		return new elementSelector(By.xpath(selectMe),selectMe, desc);
	}
	public static elementSelector byTagName(String selectMe) {
		return elementSelector.byTagName(selectMe);
	}
	public static elementSelector byTagName(String selectMe, String desc) {
		return new elementSelector(By.tagName(selectMe),selectMe, desc);
	}
	public static elementSelector byClassName(String selectMe) {
		return elementSelector.byClassName(selectMe);
	}
	public static elementSelector byClassName(String selectMe, String desc) {
		return new elementSelector(By.className(selectMe), selectMe, desc);
	}
}
